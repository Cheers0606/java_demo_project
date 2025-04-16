/**
 * 模块六：多线程&并发编程
 * 示例2：线程同步
 * 
 * 本示例展示了Java中线程同步的各种方式，
 * 包括synchronized关键字、Lock接口的使用，
 * 以及死锁的产生和避免。
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronization {
    public static void main(String[] args) {
        System.out.println("===== 线程同步示例 =====");
        
        // 1. 线程安全问题演示
        System.out.println("\n===== 线程安全问题演示 =====");
        
        // 创建共享资源
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        
        // 创建并启动多个线程操作共享资源
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("不安全计数器最终值: " + unsafeCounter.getCount());
        System.out.println("预期值应为: 2000");
        
        // 2. 使用synchronized关键字解决线程安全问题
        System.out.println("\n===== 使用synchronized关键字 =====");
        
        // 创建线程安全的共享资源
        SynchronizedCounter syncCounter = new SynchronizedCounter();
        
        // 创建并启动多个线程操作共享资源
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncCounter.increment();
            }
        });
        
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncCounter.increment();
            }
        });
        
        thread3.start();
        thread4.start();
        
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("同步计数器最终值: " + syncCounter.getCount());
        System.out.println("预期值应为: 2000");
        
        // 3. 使用synchronized代码块
        System.out.println("\n===== 使用synchronized代码块 =====");
        
        // 创建共享资源
        final StringBuilder builder = new StringBuilder();
        
        // 创建并启动多个线程操作共享资源
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                // synchronized代码块，指定同步对象
                synchronized (builder) {
                    builder.append("A");
                }
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread thread6 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (builder) {
                    builder.append("B");
                }
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        thread5.start();
        thread6.start();
        
        try {
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("StringBuilder长度: " + builder.length());
        System.out.println("预期长度应为: 200");
        
        // 4. 使用Lock接口
        System.out.println("\n===== 使用Lock接口 =====");
        
        // 创建使用Lock的共享资源
        LockCounter lockCounter = new LockCounter();
        
        // 创建并启动多个线程操作共享资源
        Thread thread7 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lockCounter.increment();
            }
        });
        
        Thread thread8 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lockCounter.increment();
            }
        });
        
        thread7.start();
        thread8.start();
        
        try {
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Lock计数器最终值: " + lockCounter.getCount());
        System.out.println("预期值应为: 2000");
        
        // 5. 死锁演示
        System.out.println("\n===== 死锁演示 =====");
        
        // 创建两个锁对象
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        
        // 线程1：先获取lock1，再获取lock2
        Thread deadlockThread1 = new Thread(() -> {
            System.out.println("线程1: 尝试获取lock1");
            synchronized (lock1) {
                System.out.println("线程1: 获取到lock1");
                
                try {
                    Thread.sleep(100);  // 休眠一段时间，确保另一个线程有机会获取lock2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("线程1: 尝试获取lock2");
                synchronized (lock2) {
                    System.out.println("线程1: 获取到lock2");
                    System.out.println("线程1: 执行临界区代码");
                }
            }
        });
        
        // 线程2：先获取lock2，再获取lock1
        Thread deadlockThread2 = new Thread(() -> {
            System.out.println("线程2: 尝试获取lock2");
            synchronized (lock2) {
                System.out.println("线程2: 获取到lock2");
                
                try {
                    Thread.sleep(100);  // 休眠一段时间，确保另一个线程有机会获取lock1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("线程2: 尝试获取lock1");
                synchronized (lock1) {
                    System.out.println("线程2: 获取到lock1");
                    System.out.println("线程2: 执行临界区代码");
                }
            }
        });
        
        // 启动线程
        deadlockThread1.start();
        deadlockThread2.start();
        
        // 等待一段时间，让死锁发生
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("注意：上面的线程可能已经进入死锁状态，程序不会自动退出");
        System.out.println("在实际应用中，应该避免这种情况的发生");
        
        // 6. 避免死锁的方法
        System.out.println("\n===== 避免死锁的方法 =====");
        
        // 创建两个锁对象
        final Object resourceA = new Object();
        final Object resourceB = new Object();
        
        // 线程1和线程2都按照相同的顺序获取锁
        Thread safeThread1 = new Thread(() -> {
            System.out.println("安全线程1: 尝试获取resourceA");
            synchronized (resourceA) {
                System.out.println("安全线程1: 获取到resourceA");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("安全线程1: 尝试获取resourceB");
                synchronized (resourceB) {
                    System.out.println("安全线程1: 获取到resourceB");
                    System.out.println("安全线程1: 执行临界区代码");
                }
            }
        });
        
        Thread safeThread2 = new Thread(() -> {
            System.out.println("安全线程2: 尝试获取resourceA");
            synchronized (resourceA) {
                System.out.println("安全线程2: 获取到resourceA");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("安全线程2: 尝试获取resourceB");
                synchronized (resourceB) {
                    System.out.println("安全线程2: 获取到resourceB");
                    System.out.println("安全线程2: 执行临界区代码");
                }
            }
        });
        
        // 启动线程
        safeThread1.start();
        safeThread2.start();
        
        try {
            safeThread1.join();
            safeThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("安全线程执行完毕，没有发生死锁");
    }
}

/**
 * 不安全的计数器类（没有同步机制）
 */
class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++;  // 非原子操作
    }
    
    public int getCount() {
        return count;
    }
}

/**
 * 使用synchronized方法的线程安全计数器类
 */
class SynchronizedCounter {
    private int count = 0;
    
    // 使用synchronized关键字修饰方法
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}

/**
 * 使用Lock接口的线程安全计数器类
 */
class LockCounter {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();  // 获取锁
        try {
            count++;
        } finally {
            lock.unlock();  // 在finally块中释放锁
        }
    }
    
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
