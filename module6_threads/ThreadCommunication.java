/**
 * 模块六：多线程&并发编程
 * 示例3：线程通信
 * 
 * 本示例展示了Java中线程通信的方式，
 * 包括wait/notify机制、生产者消费者模式，
 * 以及CountDownLatch和CyclicBarrier的使用。
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadCommunication {
    public static void main(String[] args) {
        System.out.println("===== 线程通信示例 =====");
        
        // 1. wait/notify机制
        System.out.println("\n===== wait/notify机制 =====");
        
        // 创建共享对象
        final Object lock = new Object();
        
        // 创建等待线程
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("等待线程: 获取到锁");
                System.out.println("等待线程: 开始等待...");
                
                try {
                    lock.wait();  // 释放锁并等待
                    System.out.println("等待线程: 被唤醒，继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // 创建通知线程
        Thread notifyingThread = new Thread(() -> {
            try {
                Thread.sleep(2000);  // 等待2秒后再获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            synchronized (lock) {
                System.out.println("通知线程: 获取到锁");
                System.out.println("通知线程: 唤醒等待的线程");
                lock.notify();  // 唤醒一个等待的线程
                // lock.notifyAll();  // 唤醒所有等待的线程
                
                System.out.println("通知线程: 完成工作，即将释放锁");
            }
        });
        
        // 启动线程
        waitingThread.start();
        notifyingThread.start();
        
        try {
            waitingThread.join();
            notifyingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 2. 生产者消费者模式
        System.out.println("\n===== 生产者消费者模式 =====");
        
        // 创建共享缓冲区
        Buffer buffer = new Buffer(5);  // 缓冲区容量为5
        
        // 创建生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i);
                    System.out.println("生产者: 生产数据 " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int data = buffer.get();
                    System.out.println("消费者: 消费数据 " + data);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // 启动线程
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 3. CountDownLatch
        System.out.println("\n===== CountDownLatch示例 =====");
        
        // 创建CountDownLatch，计数为3
        CountDownLatch latch = new CountDownLatch(3);
        
        // 创建多个工作线程
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            new Thread(() -> {
                try {
                    System.out.println("任务" + taskId + ": 开始执行");
                    Thread.sleep(1000 * taskId);  // 模拟任务执行时间
                    System.out.println("任务" + taskId + ": 执行完毕");
                    latch.countDown();  // 计数减1
                    System.out.println("任务" + taskId + ": 计数器减1，当前值: " + latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        
        // 主线程等待所有任务完成
        try {
            System.out.println("主线程: 等待所有任务完成...");
            latch.await();  // 等待计数器为0
            System.out.println("主线程: 所有任务已完成，继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 4. CyclicBarrier
        System.out.println("\n===== CyclicBarrier示例 =====");
        
        // 创建CyclicBarrier，等待3个线程到达，并指定到达后执行的操作
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("所有线程都到达了屏障点，执行屏障操作");
        });
        
        // 创建多个线程
        for (int i = 1; i <= 3; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("线程" + threadId + ": 开始执行");
                    Thread.sleep(1000 * threadId);  // 模拟工作时间
                    System.out.println("线程" + threadId + ": 到达屏障点，等待其他线程");
                    
                    barrier.await();  // 等待所有线程到达屏障点
                    
                    System.out.println("线程" + threadId + ": 屏障解除，继续执行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

/**
 * 线程安全的缓冲区，用于生产者消费者模式
 */
class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    
    public Buffer(int capacity) {
        this.capacity = capacity;
    }
    
    // 生产者调用，向缓冲区放入数据
    public synchronized void put(int data) throws InterruptedException {
        // 如果缓冲区已满，等待
        while (queue.size() == capacity) {
            System.out.println("缓冲区已满，生产者等待...");
            wait();
        }
        
        // 放入数据
        queue.offer(data);
        System.out.println("放入数据: " + data + "，当前缓冲区大小: " + queue.size());
        
        // 唤醒可能在等待的消费者
        notifyAll();
    }
    
    // 消费者调用，从缓冲区获取数据
    public synchronized int get() throws InterruptedException {
        // 如果缓冲区为空，等待
        while (queue.isEmpty()) {
            System.out.println("缓冲区为空，消费者等待...");
            wait();
        }
        
        // 获取数据
        int data = queue.poll();
        System.out.println("获取数据: " + data + "，当前缓冲区大小: " + queue.size());
        
        // 唤醒可能在等待的生产者
        notifyAll();
        
        return data;
    }
}
