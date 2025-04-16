/**
 * 模块六：多线程&并发编程
 * 示例1：多线程基础
 * 
 * 本示例展示了Java中创建和启动线程的基本方法，
 * 包括继承Thread类、实现Runnable接口，
 * 以及线程的生命周期和线程优先级的设置。
 */
public class ThreadBasics {
    public static void main(String[] args) {
        System.out.println("===== 多线程基础示例 =====");
        
        // 1. 继承Thread类创建线程
        System.out.println("\n===== 方式1：继承Thread类 =====");
        
        // 创建线程对象
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");
        
        // 启动线程
        thread1.start();
        thread2.start();
        
        // 2. 实现Runnable接口创建线程
        System.out.println("\n===== 方式2：实现Runnable接口 =====");
        
        // 创建Runnable对象
        MyRunnable runnable1 = new MyRunnable("Runnable-1");
        MyRunnable runnable2 = new MyRunnable("Runnable-2");
        
        // 创建Thread对象，并传入Runnable对象
        Thread thread3 = new Thread(runnable1);
        Thread thread4 = new Thread(runnable2);
        
        // 启动线程
        thread3.start();
        thread4.start();
        
        // 3. 使用Lambda表达式创建线程（Java 8特性）
        System.out.println("\n===== 方式3：使用Lambda表达式 =====");
        
        // 创建并启动线程
        Thread thread5 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda线程: 计数 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Lambda线程被中断");
                }
            }
        });
        
        thread5.start();
        
        // 4. 线程的基本操作
        System.out.println("\n===== 线程的基本操作 =====");
        
        // 创建线程
        Thread thread6 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("操作演示线程: 计数 " + i);
                
                // 检查线程是否被中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，退出循环");
                    break;
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("线程在睡眠时被中断");
                    // 重新设置中断状态
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // 设置线程名称
        thread6.setName("OperationThread");
        
        // 设置线程优先级（1-10，默认是5）
        thread6.setPriority(Thread.MAX_PRIORITY);  // 10
        
        // 设置为守护线程（当只剩下守护线程时，JVM会退出）
        // thread6.setDaemon(true);
        
        // 启动线程
        thread6.start();
        
        try {
            // 主线程休眠3秒
            Thread.sleep(3000);
            
            // 获取线程信息
            System.out.println("\n线程信息:");
            System.out.println("线程名称: " + thread6.getName());
            System.out.println("线程ID: " + thread6.getId());
            System.out.println("线程优先级: " + thread6.getPriority());
            System.out.println("线程是否活动: " + thread6.isAlive());
            System.out.println("线程是否为守护线程: " + thread6.isDaemon());
            System.out.println("线程状态: " + thread6.getState());
            
            // 中断线程
            System.out.println("\n中断线程...");
            thread6.interrupt();
            
        } catch (InterruptedException e) {
            System.out.println("主线程被中断");
        }
        
        // 5. 线程的join方法
        System.out.println("\n===== 线程的join方法 =====");
        
        Thread thread7 = new Thread(() -> {
            System.out.println("线程7开始执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程7执行完毕");
        });
        
        Thread thread8 = new Thread(() -> {
            System.out.println("线程8开始执行");
            try {
                // 等待thread7执行完毕
                System.out.println("线程8等待线程7完成...");
                thread7.join();
                System.out.println("线程7已完成，线程8继续执行");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程8执行完毕");
        });
        
        thread7.start();
        thread8.start();
        
        try {
            // 主线程等待thread8执行完毕
            thread8.join();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 方式1：继承Thread类
 */
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);  // 设置线程名称
    }
    
    @Override
    public void run() {
        // 线程执行的代码
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + ": 计数 " + i);
            try {
                Thread.sleep(500);  // 线程休眠500毫秒
            } catch (InterruptedException e) {
                System.out.println(getName() + " 被中断");
            }
        }
        System.out.println(getName() + " 执行完毕");
    }
}

/**
 * 方式2：实现Runnable接口
 */
class MyRunnable implements Runnable {
    private String name;
    
    public MyRunnable(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        // 线程执行的代码
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + ": 计数 " + i);
            try {
                Thread.sleep(500);  // 线程休眠500毫秒
            } catch (InterruptedException e) {
                System.out.println(name + " 被中断");
            }
        }
        System.out.println(name + " 执行完毕");
    }
}
