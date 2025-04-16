/**
 * 模块三：面向对象进阶&异常处理
 * 示例3：异常处理
 * 
 * 本示例展示了Java中异常处理的各种方式，
 * 包括try-catch-finally, throws, throw以及自定义异常，
 * 还有JDK 7引入的try-with-resources特性。
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        System.out.println("===== 异常处理示例 =====");
        
        // 1. 基本的try-catch结构
        System.out.println("\n===== 基本try-catch示例 =====");
        try {
            int result = divide(10, 0);
            System.out.println("结果: " + result); // 这行不会执行
        } catch (ArithmeticException e) {
            System.out.println("捕获到算术异常: " + e.getMessage());
        }
        
        // 2. 多重catch块
        System.out.println("\n===== 多重catch示例 =====");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // 数组越界
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到数组越界异常: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("捕获到其他异常: " + e.getMessage());
        }
        
        // 3. try-catch-finally结构
        System.out.println("\n===== try-catch-finally示例 =====");
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Scanner已创建");
            // 可能抛出异常的代码
        } catch (Exception e) {
            System.out.println("捕获到异常: " + e.getMessage());
        } finally {
            System.out.println("finally块总是执行");
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner已关闭");
            }
        }
        
        // 4. JDK 7引入的try-with-resources
        System.out.println("\n===== try-with-resources示例 =====");
        try {
            readFirstLineFromFile("example.txt");
        } catch (IOException e) {
            System.out.println("捕获到IO异常: " + e.getMessage());
        }
        
        // 5. throws关键字
        System.out.println("\n===== throws关键字示例 =====");
        try {
            methodWithThrows();
        } catch (IOException e) {
            System.out.println("捕获到IO异常: " + e.getMessage());
        }
        
        // 6. throw关键字
        System.out.println("\n===== throw关键字示例 =====");
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到非法参数异常: " + e.getMessage());
        }
        
        try {
            validateAge(25);
            System.out.println("年龄验证通过");
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到非法参数异常: " + e.getMessage());
        }
        
        // 7. 自定义异常
        System.out.println("\n===== 自定义异常示例 =====");
        try {
            checkBalance(100, 200);
        } catch (InsufficientFundsException e) {
            System.out.println("捕获到自定义异常: " + e.getMessage());
            System.out.println("缺少金额: " + e.getAmount());
        }
        
        // 8. 异常链
        System.out.println("\n===== 异常链示例 =====");
        try {
            methodWithExceptionChaining();
        } catch (Exception e) {
            System.out.println("捕获到异常: " + e.getMessage());
            System.out.println("原因: " + e.getCause().getMessage());
        }
    }
    
    // 可能抛出异常的方法
    public static int divide(int a, int b) {
        return a / b; // 如果b为0，将抛出ArithmeticException
    }
    
    // 使用try-with-resources读取文件第一行
    public static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
        // 不需要finally块来关闭资源，BufferedReader会自动关闭
    }
    
    // 使用throws声明可能抛出的异常
    public static void methodWithThrows() throws IOException {
        throw new IOException("这是一个IO异常示例");
    }
    
    // 使用throw抛出异常
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("年龄必须大于或等于18");
        }
    }
    
    // 异常链示例
    public static void methodWithExceptionChaining() throws Exception {
        try {
            throw new IOException("IO操作失败");
        } catch (IOException e) {
            // 创建一个新异常，并将原始异常设置为其原因
            Exception newException = new Exception("处理数据时出错");
            newException.initCause(e);
            throw newException;
        }
    }
    
    // 使用自定义异常检查余额
    public static void checkBalance(double balance, double withdrawAmount) 
            throws InsufficientFundsException {
        if (withdrawAmount > balance) {
            double needs = withdrawAmount - balance;
            throw new InsufficientFundsException(needs);
        }
    }
}

/**
 * 自定义异常类
 */
class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("余额不足，还需要" + amount + "元");
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}
