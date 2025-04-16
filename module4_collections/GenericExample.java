/**
 * 模块四：集合框架&泛型
 * 示例2：泛型
 * 
 * 本示例展示了Java中泛型的概念与使用，
 * 包括泛型类、泛型方法、泛型接口、泛型通配符以及类型擦除。
 */
import java.util.ArrayList;
import java.util.List;

public class GenericExample {
    public static void main(String[] args) {
        System.out.println("===== 泛型示例 =====");
        
        // 1. 不使用泛型的情况
        System.out.println("\n===== 不使用泛型 =====");
        List listWithoutGeneric = new ArrayList();
        listWithoutGeneric.add("字符串");
        listWithoutGeneric.add(100);
        listWithoutGeneric.add(3.14);
        
        // 需要强制类型转换，容易出现ClassCastException
        try {
            String item = (String) listWithoutGeneric.get(0); // 正确
            System.out.println("第一个元素: " + item);
            
            String number = (String) listWithoutGeneric.get(1); // 错误，会抛出异常
            System.out.println("第二个元素: " + number);
        } catch (ClassCastException e) {
            System.out.println("捕获到类型转换异常: " + e.getMessage());
        }
        
        // 2. 使用泛型的情况
        System.out.println("\n===== 使用泛型 =====");
        List<String> listWithGeneric = new ArrayList<>();
        listWithGeneric.add("Java");
        listWithGeneric.add("Python");
        // listWithGeneric.add(100); // 编译错误，只能添加String类型
        
        // 不需要强制类型转换
        String firstItem = listWithGeneric.get(0);
        System.out.println("第一个元素: " + firstItem);
        
        // 3. 泛型类
        System.out.println("\n===== 泛型类 =====");
        
        // 创建Integer类型的Box
        Box<Integer> intBox = new Box<>();
        intBox.setItem(10);
        System.out.println("整数盒子的值: " + intBox.getItem());
        
        // 创建String类型的Box
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello泛型");
        System.out.println("字符串盒子的值: " + stringBox.getItem());
        
        // 4. 泛型方法
        System.out.println("\n===== 泛型方法 =====");
        
        // 调用泛型方法
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"A", "B", "C"};
        
        System.out.println("打印整数数组:");
        printArray(intArray);
        
        System.out.println("打印字符串数组:");
        printArray(stringArray);
        
        // 5. 多类型参数
        System.out.println("\n===== 多类型参数 =====");
        
        Pair<String, Integer> pair = new Pair<>("年龄", 25);
        System.out.println("键: " + pair.getKey() + ", 值: " + pair.getValue());
        
        // 6. 泛型接口
        System.out.println("\n===== 泛型接口 =====");
        
        // 实现泛型接口的类
        Calculator<Integer> intCalculator = new IntegerCalculator();
        System.out.println("整数计算器加法: " + intCalculator.add(5, 3));
        
        Calculator<Double> doubleCalculator = new DoubleCalculator();
        System.out.println("浮点数计算器加法: " + doubleCalculator.add(5.5, 3.3));
        
        // 7. 泛型通配符
        System.out.println("\n===== 泛型通配符 =====");
        
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(3.14);
        doubleList.add(6.28);
        
        // 使用通配符
        System.out.print("整数列表的和: ");
        System.out.println(sumOfList(intList));
        
        System.out.print("浮点数列表的和: ");
        System.out.println(sumOfList(doubleList));
        
        // 8. 有界类型参数
        System.out.println("\n===== 有界类型参数 =====");
        
        System.out.println("比较整数: " + compareObjects(10, 20));
        System.out.println("比较浮点数: " + compareObjects(3.14, 2.71));
        System.out.println("比较字符串: " + compareObjects("Apple", "Banana"));
        
        // 9. 类型擦除
        System.out.println("\n===== 类型擦除 =====");
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox2 = new Box<>();
        
        System.out.println("整数盒子的类: " + integerBox.getClass().getName());
        System.out.println("字符串盒子的类: " + stringBox2.getClass().getName());
        System.out.println("两个盒子类型是否相同: " + (integerBox.getClass() == stringBox2.getClass()));
    }
    
    // 泛型方法
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.println(element);
        }
    }
    
    // 使用有界通配符的方法
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    // 有界类型参数
    public static <T extends Comparable<T>> int compareObjects(T a, T b) {
        return a.compareTo(b);
    }
}

/**
 * 泛型类示例
 */
class Box<T> {
    private T item;
    
    public void setItem(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
}

/**
 * 多类型参数的泛型类
 */
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
}

/**
 * 泛型接口
 */
interface Calculator<T> {
    T add(T a, T b);
    T subtract(T a, T b);
}

/**
 * 实现泛型接口的类（指定具体类型）
 */
class IntegerCalculator implements Calculator<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
    
    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }
}

/**
 * 实现泛型接口的另一个类
 */
class DoubleCalculator implements Calculator<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }
    
    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }
}
