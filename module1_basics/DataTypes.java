/**
 * 模块一：基础语法与环境搭建
 * 示例2：Java数据类型
 * 
 * 本示例展示了Java中的基本数据类型和引用数据类型，
 * 以及变量的声明、初始化和使用方法。
 */
public class DataTypes {
    public static void main(String[] args) {
        // 1. 基本数据类型（Primitive Data Types）
        
        // 整数类型
        byte byteVar = 127;                  // 8位，范围：-128 到 127
        short shortVar = 32767;              // 16位，范围：-32768 到 32767
        int intVar = 2147483647;             // 32位，范围：-2147483648 到 2147483647
        long longVar = 9223372036854775807L; // 64位，注意后缀L
        
        // 浮点类型
        float floatVar = 3.14159F;           // 32位，注意后缀F
        double doubleVar = 3.141592653589793D; // 64位，D可省略
        
        // 字符类型
        char charVar = 'A';                  // 16位Unicode字符
        
        // 布尔类型
        boolean booleanVar = true;           // true或false
        
        // 2. 输出所有基本数据类型
        System.out.println("===== 基本数据类型 =====");
        System.out.println("byte类型: " + byteVar);
        System.out.println("short类型: " + shortVar);
        System.out.println("int类型: " + intVar);
        System.out.println("long类型: " + longVar);
        System.out.println("float类型: " + floatVar);
        System.out.println("double类型: " + doubleVar);
        System.out.println("char类型: " + charVar);
        System.out.println("boolean类型: " + booleanVar);
        
        // 3. 引用数据类型（Reference Data Types）
        System.out.println("\n===== 引用数据类型 =====");
        
        // 字符串（String是Java中最常用的引用类型之一）
        String stringVar = "Hello Java";
        System.out.println("String类型: " + stringVar);
        
        // 数组
        int[] arrayVar = {1, 2, 3, 4, 5};
        System.out.print("Array类型: ");
        for (int i = 0; i < arrayVar.length; i++) {
            System.out.print(arrayVar[i] + " ");
        }
        System.out.println();
        
        // 4. 类型转换
        System.out.println("\n===== 类型转换 =====");
        
        // 自动类型转换（隐式转换）- 小类型到大类型
        int smallerType = 100;
        long largerType = smallerType; // 自动从int转为long
        System.out.println("自动类型转换 (int -> long): " + largerType);
        
        // 强制类型转换（显式转换）- 大类型到小类型
        double doubleValue = 9.99;
        int intValue = (int) doubleValue; // 强制从double转为int，小数部分被截断
        System.out.println("强制类型转换 (double -> int): " + intValue);
        
        // 5. 常量（使用final关键字）
        System.out.println("\n===== 常量 =====");
        final double PI = 3.14159;
        // PI = 3.14; // 这行会导致编译错误，因为常量不能被修改
        System.out.println("常量PI: " + PI);
    }
}
