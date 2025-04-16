/**
 * 模块二：面向对象基础
 * 示例3：静态成员
 * 
 * 本示例展示了Java中静态变量、静态方法、静态代码块的使用，
 * 以及单例模式的实现方式。
 */
public class StaticDemo {
    // 1. 静态变量（类变量）
    private static int instanceCount = 0;  // 记录创建的实例数量
    
    // 非静态变量（实例变量）
    private String name;
    
    // 2. 静态代码块 - 类加载时执行，只执行一次
    static {
        System.out.println("静态代码块执行 - 类加载时");
        System.out.println("可以在此初始化静态变量或进行其他一次性设置");
    }
    
    // 3. 非静态代码块 - 每次创建实例时执行
    {
        System.out.println("非静态代码块执行 - 实例创建时");
        instanceCount++;  // 每创建一个实例，计数器加1
    }
    
    // 构造方法
    public StaticDemo(String name) {
        this.name = name;
        System.out.println("创建了一个新实例: " + this.name);
    }
    
    // 4. 静态方法 - 属于类，不依赖于实例
    public static int getInstanceCount() {
        return instanceCount;
    }
    
    // 静态方法中不能直接访问非静态成员
    public static void staticMethod() {
        System.out.println("这是一个静态方法");
        System.out.println("当前实例数量: " + instanceCount);
        // 错误：System.out.println(this.name); // 静态方法中不能使用this关键字
        // 错误：nonStaticMethod(); // 静态方法中不能直接调用非静态方法
    }
    
    // 非静态方法 - 属于实例，依赖于特定实例
    public void nonStaticMethod() {
        System.out.println("这是一个非静态方法");
        System.out.println("当前实例名称: " + this.name);
        // 非静态方法可以访问静态成员
        System.out.println("当前实例数量: " + instanceCount);
        staticMethod(); // 非静态方法可以调用静态方法
    }
    
    public static void main(String[] args) {
        System.out.println("===== 静态成员示例 =====");
        
        // 访问静态变量和方法（无需创建实例）
        System.out.println("程序开始时的实例数量: " + StaticDemo.getInstanceCount());
        
        // 调用静态方法
        StaticDemo.staticMethod();
        
        // 创建实例
        System.out.println("\n===== 创建第一个实例 =====");
        StaticDemo instance1 = new StaticDemo("实例1");
        
        System.out.println("\n===== 创建第二个实例 =====");
        StaticDemo instance2 = new StaticDemo("实例2");
        
        // 再次访问静态变量
        System.out.println("\n当前实例数量: " + StaticDemo.getInstanceCount());
        
        // 调用非静态方法
        System.out.println("\n===== 调用非静态方法 =====");
        instance1.nonStaticMethod();
        
        // 通过实例访问静态成员（不推荐，但语法上允许）
        System.out.println("\n===== 通过实例访问静态成员 =====");
        System.out.println("通过实例访问静态变量: " + instance1.getInstanceCount());
        
        // 单例模式示例
        System.out.println("\n===== 单例模式示例 =====");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        
        System.out.println("singleton1和singleton2是同一个实例: " + (singleton1 == singleton2));
        
        // 使用单例
        singleton1.setData("测试数据");
        System.out.println("从singleton2获取数据: " + singleton2.getData());
    }
}

/**
 * 单例模式示例 - 懒汉式（延迟加载）
 */
class Singleton {
    // 私有静态变量，用于保存唯一实例
    private static Singleton instance;
    
    // 实例数据
    private String data;
    
    // 私有构造方法，防止外部直接创建实例
    private Singleton() {
        System.out.println("Singleton构造方法被调用");
    }
    
    // 公共静态方法，提供获取唯一实例的接口
    public static Singleton getInstance() {
        // 懒汉式：第一次调用时才创建实例
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    // getter和setter方法
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
