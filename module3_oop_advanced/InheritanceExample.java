/**
 * 模块三：面向对象进阶&异常处理
 * 示例1：继承
 * 
 * 本示例展示了Java中继承的概念与使用，
 * 包括方法重写、super关键字的使用以及Object类的常用方法。
 */
public class InheritanceExample {
    public static void main(String[] args) {
        System.out.println("===== 继承示例 =====");
        
        // 创建父类对象
        Animal animal = new Animal("动物", 1);
        animal.eat();
        animal.sleep();
        
        // 创建子类对象
        System.out.println("\n===== 创建Dog对象 =====");
        Dog dog = new Dog("旺财", 3, "金毛");
        dog.eat();      // 调用重写后的方法
        dog.sleep();    // 调用继承的方法
        dog.bark();     // 调用子类特有的方法
        
        // 创建另一个子类对象
        System.out.println("\n===== 创建Cat对象 =====");
        Cat cat = new Cat("咪咪", 2, true);
        cat.eat();
        cat.sleep();
        cat.catchMouse();
        
        // 多态
        System.out.println("\n===== 多态示例 =====");
        Animal animal1 = new Dog("小黑", 2, "拉布拉多");  // 向上转型
        Animal animal2 = new Cat("小花", 1, false);
        
        animal1.eat();  // 调用Dog类的eat方法
        animal2.eat();  // 调用Cat类的eat方法
        
        // 向下转型
        if (animal1 instanceof Dog) {
            Dog dog2 = (Dog) animal1;  // 向下转型
            dog2.bark();
        }
        
        // Object类的方法
        System.out.println("\n===== Object类方法 =====");
        System.out.println("dog的toString(): " + dog.toString());
        System.out.println("cat的toString(): " + cat.toString());
        
        Dog anotherDog = new Dog("旺财", 3, "金毛");
        System.out.println("dog和anotherDog是否相等: " + dog.equals(anotherDog));
        System.out.println("dog的hashCode: " + dog.hashCode());
        System.out.println("anotherDog的hashCode: " + anotherDog.hashCode());
    }
}

/**
 * 父类：Animal
 */
class Animal {
    protected String name;  // protected成员可以被子类访问
    protected int age;
    
    // 构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Animal构造方法被调用");
    }
    
    // 方法
    public void eat() {
        System.out.println(name + "正在吃东西");
    }
    
    public void sleep() {
        System.out.println(name + "正在睡觉");
    }
    
    // getter和setter
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
        this.age = age;
    }
    
    // 重写Object类的toString方法
    @Override
    public String toString() {
        return "Animal [name=" + name + ", age=" + age + "]";
    }
}

/**
 * 子类：Dog
 */
class Dog extends Animal {
    private String breed;  // 狗的品种
    
    // 子类的构造方法
    public Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类的构造方法
        this.breed = breed;
        System.out.println("Dog构造方法被调用");
    }
    
    // 重写父类的方法
    @Override
    public void eat() {
        System.out.println(breed + "犬" + name + "正在吃骨头");
    }
    
    // 子类特有的方法
    public void bark() {
        System.out.println(name + "汪汪叫");
    }
    
    // getter和setter
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    // 重写toString方法
    @Override
    public String toString() {
        return "Dog [name=" + name + ", age=" + age + ", breed=" + breed + "]";
    }
    
    // 重写equals方法
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Dog dog = (Dog) obj;
        return age == dog.age && 
               name.equals(dog.name) && 
               breed.equals(dog.breed);
    }
    
    // 重写hashCode方法
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + breed.hashCode();
        return result;
    }
}

/**
 * 子类：Cat
 */
class Cat extends Animal {
    private boolean likeFish;  // 是否喜欢鱼
    
    // 子类的构造方法
    public Cat(String name, int age, boolean likeFish) {
        super(name, age);  // 调用父类的构造方法
        this.likeFish = likeFish;
        System.out.println("Cat构造方法被调用");
    }
    
    // 重写父类的方法
    @Override
    public void eat() {
        if (likeFish) {
            System.out.println(name + "正在吃鱼");
        } else {
            System.out.println(name + "正在吃猫粮");
        }
    }
    
    // 子类特有的方法
    public void catchMouse() {
        System.out.println(name + "正在抓老鼠");
    }
    
    // getter和setter
    public boolean isLikeFish() {
        return likeFish;
    }
    
    public void setLikeFish(boolean likeFish) {
        this.likeFish = likeFish;
    }
    
    // 重写toString方法
    @Override
    public String toString() {
        return "Cat [name=" + name + ", age=" + age + ", likeFish=" + likeFish + "]";
    }
}
