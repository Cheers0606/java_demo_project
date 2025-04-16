/**
 * 模块六：多线程&并发编程
 * 示例4：并发工具
 * 
 * 本示例展示了Java中的并发工具类的使用，
 * 包括线程池、Callable与Future、并发集合以及原子类。
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;

public class ConcurrentTools {
    public static void main(String[] args) {
        System.out.println("===== 并发工具示例 =====");
        
        // 1. 线程池
        System.out.println("\n===== 线程池示例 =====");
        
        // 创建固定大小的线程池
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        System.out.println("创建了固定大小为3的线程池");
        
        // 提交任务到线程池
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedPool.execute(() -> {
                System.out.println("任务" + taskId + "开始执行，线程名: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);  // 模拟任务执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务" + taskId + "执行完毕");
            });
        }
        
        // 关闭线程池
        System.out.println("提交完所有任务，关闭线程池");
        fixedPool.shutdown();
        
        try {
            // 等待线程池中的任务执行完毕
            boolean terminated = fixedPool.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("线程池是否已终止: " + terminated);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 2. Callable与Future
        System.out.println("\n===== Callable与Future示例 =====");
        
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        // 创建Callable任务
        Callable<Integer> task = () -> {
            System.out.println("计算任务开始执行，线程名: " + Thread.currentThread().getName());
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            Thread.sleep(2000);  // 模拟耗时计算
            return sum;
        };
        
        // 提交任务并获取Future对象
        System.out.println("提交计算任务到线程池");
        Future<Integer> future = executorService.submit(task);
        
        // 检查任务是否完成
        System.out.println("任务是否完成: " + future.isDone());
        
        try {
            // 获取任务结果（如果任务未完成，会阻塞等待）
            System.out.println("等待获取任务结果...");
            Integer result = future.get();
            System.out.println("计算结果: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        // 关闭线程池
        executorService.shutdown();
        
        // 3. 并发集合
        System.out.println("\n===== 并发集合示例 =====");
        
        // ConcurrentHashMap示例
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // 创建多个线程操作ConcurrentHashMap
        ExecutorService mapService = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            mapService.execute(() -> {
                for (int j = 1; j <= 5; j++) {
                    String key = "thread" + threadId + "-" + j;
                    concurrentMap.put(key, j);
                    System.out.println("线程" + threadId + "添加键值对: " + key + " -> " + j);
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        mapService.shutdown();
        try {
            mapService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("ConcurrentHashMap大小: " + concurrentMap.size());
        System.out.println("ConcurrentHashMap内容: " + concurrentMap);
        
        // CopyOnWriteArrayList示例
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        
        // 创建多个线程操作CopyOnWriteArrayList
        ExecutorService listService = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            listService.execute(() -> {
                for (int j = 1; j <= 3; j++) {
                    String item = "item" + threadId + "-" + j;
                    copyOnWriteList.add(item);
                    System.out.println("线程" + threadId + "添加元素: " + item);
                    
                    // 遍历列表（不会抛出ConcurrentModificationException）
                    for (String element : copyOnWriteList) {
                        System.out.println("线程" + threadId + "读取元素: " + element);
                    }
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        listService.shutdown();
        try {
            listService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("CopyOnWriteArrayList大小: " + copyOnWriteList.size());
        System.out.println("CopyOnWriteArrayList内容: " + copyOnWriteList);
        
        // 4. 原子类
        System.out.println("\n===== 原子类示例 =====");
        
        // AtomicInteger示例
        AtomicInteger atomicInt = new AtomicInteger(0);
        
        // 创建多个线程操作AtomicInteger
        ExecutorService atomicService = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 5; i++) {
            atomicService.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicInt.incrementAndGet();  // 原子自增操作
                }
            });
        }
        
        atomicService.shutdown();
        try {
            atomicService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("AtomicInteger最终值: " + atomicInt.get());
        System.out.println("预期值: 5000");
        
        // AtomicReference示例
        AtomicReference<String> atomicRef = new AtomicReference<>("初始值");
        
        boolean success = atomicRef.compareAndSet("初始值", "新值");
        System.out.println("CAS操作结果: " + success);
        System.out.println("AtomicReference当前值: " + atomicRef.get());
        
        success = atomicRef.compareAndSet("初始值", "另一个新值");
        System.out.println("CAS操作结果: " + success);
        System.out.println("AtomicReference当前值: " + atomicRef.get());
        
        // 5. ScheduledExecutorService示例
        System.out.println("\n===== ScheduledExecutorService示例 =====");
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // 延迟执行任务
        scheduler.schedule(() -> {
            System.out.println("延迟任务执行，时间: " + new Date());
        }, 2, TimeUnit.SECONDS);
        
        // 固定速率执行任务
        final int[] count = {0};
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
            System.out.println("固定速率任务执行，时间: " + new Date() + ", 计数: " + (++count[0]));
            if (count[0] >= 3) {
                scheduler.shutdown();
            }
        }, 1, 1, TimeUnit.SECONDS);
        
        try {
            // 等待调度任务完成
            scheduler.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
