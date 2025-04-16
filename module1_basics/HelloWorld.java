/**
 * 模块一：基础语法与环境搭建
 * 示例1：第一个Java程序 - HelloWorld
 * 
 * 这是Java编程的入门程序，展示了Java程序的基本结构。
 * 每个Java程序都必须有一个类，且类名必须与文件名相同。
 * public static void main(String[] args)是程序的入口点。
 */
public class HelloWorld {
    // main方法是Java程序的入口点
    public static void main(String[] args) {
        // 使用System.out.println()方法在控制台输出信息
        System.out.println("Hello, World! 欢迎学习Java SE课程！");
        
        // 输出JDK版本信息
        System.out.println("当前Java版本: " + System.getProperty("java.version"));
    }
}
