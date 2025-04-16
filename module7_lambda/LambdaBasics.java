/**
 * 模块七：函数式编程&Lambda表达式
 * 示例1：Lambda表达式基础
 * 
 * 本示例展示了Java 8中Lambda表达式的基本语法和使用方法，
 * 包括函数式接口、Lambda表达式语法、方法引用以及变量作用域。
 */
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaBasics {
    public static void main(String[] args) {
        System.out.println("===== Lambda表达式基础示例 =====");
        
        // 1. 传统方式实现接口
        System.out.println("\n===== 传统方式实现接口 =====");
        
        // 使用匿名内部类
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式：使用匿名内部类实现Runnable接口");
            }
        };
        
        // 执行run方法
        traditionalRunnable.run();
        
        // 2. 使用Lambda表达式
        System.out.println("\n===== 使用Lambda表达式 =====");
        
        // Lambda表达式实现Runnable接口
        Runnable lambdaRunnable = () -> {
            System.out.println("Lambda方式：实现Runnable接口");
        };
        
        // 执行run方法
        lambdaRunnable.run();
        
        // 简化的Lambda表达式（单行语句可以省略花括号）
        Runnable simpleLambda = () -> System.out.println("简化的Lambda表达式");
        simpleLambda.run();
        
        // 3. Lambda表达式语法
        System.out.println("\n===== Lambda表达式语法 =====");
        
        // 无参数，无返回值
        Runnable noParamLambda = () -> System.out.println("无参数的Lambda表达式");
        noParamLambda.run();
        
        // 一个参数，无返回值（参数括号可以省略）
        Consumer<String> oneParamLambda = message -> System.out.println("消费消息: " + message);
        oneParamLambda.accept("Hello Lambda");
        
        // 多个参数，有返回值
        BiFunction<Integer, Integer, Integer> addFunction = (a, b) -> a + b;
        System.out.println("5 + 3 = " + addFunction.apply(5, 3));
        
        // 多行语句的Lambda表达式
        BiFunction<Integer, Integer, Integer> multiLineFunction = (a, b) -> {
            System.out.println("计算 " + a + " + " + b);
            return a + b;
        };
        System.out.println("10 + 20 = " + multiLineFunction.apply(10, 20));
        
        // 4. 函数式接口
        System.out.println("\n===== 函数式接口 =====");
        
        // Predicate<T>：接收T类型参数，返回boolean
        Predicate<Integer> isPositive = n -> n > 0;
        System.out.println("10是正数吗？ " + isPositive.test(10));
        System.out.println("-5是正数吗？ " + isPositive.test(-5));
        
        // Consumer<T>：接收T类型参数，无返回值
        Consumer<String> printer = message -> System.out.println("打印: " + message);
        printer.accept("Consumer接口示例");
        
        // Function<T, R>：接收T类型参数，返回R类型结果
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("'Hello'的长度: " + stringLength.apply("Hello"));
        
        // Supplier<T>：无参数，返回T类型结果
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("随机数: " + randomSupplier.get());
        
        // BiFunction<T, U, R>：接收T和U类型参数，返回R类型结果
        BiFunction<String, String, String> concatFunction = (s1, s2) -> s1 + s2;
        System.out.println("拼接结果: " + concatFunction.apply("Hello, ", "Lambda!"));
        
        // 5. 自定义函数式接口
        System.out.println("\n===== 自定义函数式接口 =====");
        
        // 使用自定义的函数式接口
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        
        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
        
        // 6. 方法引用
        System.out.println("\n===== 方法引用 =====");
        
        // 静态方法引用
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("字符串'123'转整数: " + parseInt.apply("123"));
        
        // 实例方法引用
        String text = "Hello, World";
        Supplier<Integer> getLength = text::length;
        System.out.println("字符串长度: " + getLength.get());
        
        // 对象方法引用
        Consumer<String> systemOut = System.out::println;
        systemOut.accept("使用System.out::println打印消息");
        
        // 构造方法引用
        Supplier<StringBuilder> newStringBuilder = StringBuilder::new;
        StringBuilder sb = newStringBuilder.get();
        sb.append("使用构造方法引用创建的StringBuilder");
        System.out.println(sb.toString());
        
        // 7. 变量作用域
        System.out.println("\n===== 变量作用域 =====");
        
        final int multiplier = 2;
        
        // Lambda表达式可以访问外部的final或effectively final变量
        Function<Integer, Integer> multiply = n -> n * multiplier;
        System.out.println("5 * 2 = " + multiply.apply(5));
        
        // 8. 在集合操作中使用Lambda
        System.out.println("\n===== 在集合操作中使用Lambda =====");
        
        List<String> names = Arrays.asList("张三", "李四", "王五", "赵六");
        
        // 使用Lambda表达式遍历集合
        System.out.println("使用forEach和Lambda遍历集合:");
        names.forEach(name -> System.out.println(name));
        
        // 使用方法引用遍历集合
        System.out.println("\n使用forEach和方法引用遍历集合:");
        names.forEach(System.out::println);
    }
    
    // 使用自定义函数式接口的方法
    private static int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }
}

/**
 * 自定义函数式接口
 * 使用@FunctionalInterface注解标记函数式接口
 */
@FunctionalInterface
interface MathOperation {
    // 函数式接口只能有一个抽象方法
    int operate(int a, int b);
    
    // 可以有默认方法
    default MathOperation andThen(MathOperation after) {
        return (a, b) -> after.operate(this.operate(a, b), 0);
    }
    
    // 可以有静态方法
    static MathOperation add() {
        return (a, b) -> a + b;
    }
}
