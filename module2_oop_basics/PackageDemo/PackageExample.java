/**
 * 模块二：面向对象基础
 * 示例2：包的概念与使用
 * 
 * 本示例展示了Java中包的定义和使用，
 * 以及如何通过import导入其他包中的类。
 */
package PackageDemo;

// 导入Java标准库中的类
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

// 导入静态成员（JDK 5引入的静态导入）
import static java.lang.Math.PI;
import static java.lang.System.out;

public class PackageExample {
    public static void main(String[] args) {
        // 使用导入的Date类
        Date currentDate = new Date();
        out.println("===== 包的使用示例 =====");
        out.println("当前日期时间: " + currentDate);
        
        // 使用SimpleDateFormat格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        out.println("格式化后的日期时间: " + sdf.format(currentDate));
        
        // 使用静态导入的PI常量
        out.println("圆周率PI值: " + PI);

        // 使用完整类名（不使用import）
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        out.println("\n使用完整类名进行引用: " + calendar.getTime());

        // 使用Scanner类获取用户输入
        out.println("\n请输入您的姓名: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        out.println("您好, " + name + "!");

        // 创建自定义包中的类的实例
        PackageUser user = new PackageUser(name);
        user.displayInfo();
        
        scanner.close();
    }
}
