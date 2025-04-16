/**
 * 模块三：面向对象进阶&异常处理
 * 示例2：抽象类与接口
 * 
 * 本示例展示了Java中抽象类和接口的定义与使用，
 * 以及JDK 8中接口的默认方法与静态方法特性。
 */
public class AbstractAndInterface {
    public static void main(String[] args) {
        System.out.println("===== 抽象类与接口示例 =====");
        
        // 创建抽象类的子类对象
        System.out.println("\n===== 抽象类示例 =====");
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        
        System.out.println("圆的面积: " + circle.calculateArea());
        System.out.println("圆的周长: " + circle.calculatePerimeter());
        System.out.println("矩形的面积: " + rectangle.calculateArea());
        System.out.println("矩形的周长: " + rectangle.calculatePerimeter());
        
        // 使用接口
        System.out.println("\n===== 接口示例 =====");
        Flyable bird = new Bird();
        Flyable airplane = new Airplane();
        
        bird.fly();
        airplane.fly();
        
        // 调用接口的默认方法
        System.out.println("\n===== 接口默认方法 =====");
        bird.land();
        airplane.land();
        
        // 调用接口的静态方法
        System.out.println("\n===== 接口静态方法 =====");
        Flyable.printInfo();
        
        // 实现多个接口
        System.out.println("\n===== 实现多个接口 =====");
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        
        // 接口作为类型
        System.out.println("\n===== 接口作为类型 =====");
        Swimmer[] swimmers = new Swimmer[2];
        swimmers[0] = new Duck();
        swimmers[1] = new Fish();
        
        for (Swimmer swimmer : swimmers) {
            swimmer.swim();
        }
    }
}

/**
 * 抽象类：Shape
 * 抽象类可以包含抽象方法和具体方法
 */
abstract class Shape {
    // 抽象方法（没有方法体）
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // 具体方法
    public void display() {
        System.out.println("这是一个形状");
    }
}

/**
 * Shape的具体子类：Circle
 */
class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // 实现抽象方法
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    // 重写具体方法
    @Override
    public void display() {
        System.out.println("这是一个圆，半径为: " + radius);
    }
}

/**
 * Shape的具体子类：Rectangle
 */
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    // 实现抽象方法
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    // 重写具体方法
    @Override
    public void display() {
        System.out.println("这是一个矩形，长为: " + length + "，宽为: " + width);
    }
}

/**
 * 接口：Flyable
 * JDK 8中接口可以包含默认方法和静态方法
 */
interface Flyable {
    // 抽象方法（接口中的方法默认是public abstract）
    void fly();
    
    // 默认方法（JDK 8新特性）
    default void land() {
        System.out.println("正在降落...");
    }
    
    // 静态方法（JDK 8新特性）
    static void printInfo() {
        System.out.println("这是可飞行物体的接口");
    }
}

/**
 * 接口：Swimmer
 */
interface Swimmer {
    void swim();
}

/**
 * 实现Flyable接口的类：Bird
 */
class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("鸟儿在天空中飞翔");
    }
    
    // 可以选择性地重写默认方法
    @Override
    public void land() {
        System.out.println("鸟儿轻盈地落在树枝上");
    }
}

/**
 * 实现Flyable接口的类：Airplane
 */
class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("飞机在高空中飞行");
    }
}

/**
 * 实现多个接口的类：Duck
 */
class Duck implements Flyable, Swimmer {
    @Override
    public void fly() {
        System.out.println("鸭子在低空飞行");
    }
    
    @Override
    public void swim() {
        System.out.println("鸭子在水面上游泳");
    }
}

/**
 * 实现Swimmer接口的类：Fish
 */
class Fish implements Swimmer {
    @Override
    public void swim() {
        System.out.println("鱼儿在水中游动");
    }
}
