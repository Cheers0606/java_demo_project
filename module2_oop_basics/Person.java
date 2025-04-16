import java.io.Serializable;

/**
 * 模块二：面向对象基础
 * 示例1：类与对象基础
 * 
 * 本示例展示了Java中类的定义、对象的创建与使用、
 * 属性与方法、构造方法以及this关键字的使用。
 */
public class Person implements Serializable {
    // 类的属性（成员变量）
    private String name;    // 姓名
    private int age;        // 年龄
    private char gender;    // 性别
    
    // 构造方法（无参构造器）
    public Person() {
        System.out.println("调用无参构造方法");
    }
    
    // 构造方法（带参构造器）- 重载
    public Person(String name, int age, char gender) {
        System.out.println("调用带参构造方法");
        this.name = name;    // this关键字指代当前对象
        this.age = age;
        this.gender = gender;
    }
    
    // 构造方法重载 - 部分参数
    public Person(String name, int age) {
        this(name, age, '未'); // 调用另一个构造方法
    }
    
    // 方法（成员方法）
    public void introduce() {
        System.out.println("大家好，我是" + this.name + 
                          "，今年" + this.age + "岁，性别" + 
                          (this.gender == '男' ? "男" : 
                           this.gender == '女' ? "女" : "未知"));
    }
    
    // 带参数和返回值的方法
    public boolean isAdult() {
        return this.age >= 18;
    }
    
    // getter和setter方法（封装）
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        // 可以在setter中添加数据验证
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("年龄设置无效，请输入0-150之间的数值");
        }
    }
    
    public char getGender() {
        return gender;
    }
    
    public void setGender(char gender) {
        if (gender == '男' || gender == '女') {
            this.gender = gender;
        } else {
            this.gender = '未';
            System.out.println("性别设置无效，已设为'未知'");
        }
    }
    
    // 主方法，程序入口
    public static void main(String[] args) {
        // 创建对象（实例化）
        System.out.println("===== 创建对象 =====");
        Person person1 = new Person();  // 使用无参构造器
        
        // 使用setter方法设置属性
        person1.setName("张三");
        person1.setAge(25);
        person1.setGender('男');
        
        // 调用对象的方法
        System.out.println("\n===== 调用对象方法 =====");
        person1.introduce();
        
        // 使用带参构造器创建对象
        System.out.println("\n===== 使用带参构造器 =====");
        Person person2 = new Person("李四", 17, '女');
        person2.introduce();
        
        // 使用部分参数的构造器
        Person person3 = new Person("王五", 30);
        person3.introduce();
        
        // 使用getter方法获取属性
        System.out.println("\n===== 使用getter方法 =====");
        System.out.println("person1的姓名: " + person1.getName());
        System.out.println("person2的年龄: " + person2.getAge());
        
        // 调用带返回值的方法
        System.out.println("\n===== 带返回值的方法 =====");
        System.out.println(person1.getName() + "是成年人吗？" + person1.isAdult());
        System.out.println(person2.getName() + "是成年人吗？" + person2.isAdult());
        
        // 验证setter中的数据验证
        System.out.println("\n===== 数据验证 =====");
        person1.setAge(200); // 超出有效范围
        person3.setGender('X'); // 无效性别
    }
}
