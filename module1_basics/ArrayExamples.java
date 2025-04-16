/**
 * 模块一：基础语法与环境搭建
 * 示例4：数组
 * 
 * 本示例展示了Java中数组的声明、初始化和操作，
 * 包括一维数组和多维数组的使用方法，以及Arrays工具类的常用方法。
 */
import java.util.Arrays;

public class ArrayExamples {
    public static void main(String[] args) {
        // 1. 一维数组的声明和初始化
        System.out.println("===== 一维数组的声明和初始化 =====");
        
        // 方式1：先声明，后分配空间
        int[] array1;
        array1 = new int[5]; // 创建长度为5的int数组，默认值都是0
        
        // 方式2：声明并分配空间
        int[] array2 = new int[5];
        
        // 方式3：声明并初始化（静态初始化）
        int[] array3 = {10, 20, 30, 40, 50};
        
        // 方式4：声明并初始化（动态初始化）
        int[] array4 = new int[]{10, 20, 30, 40, 50};
        
        // 输出数组内容
        System.out.println("array1: " + Arrays.toString(array1));
        System.out.println("array3: " + Arrays.toString(array3));
        
        // 2. 访问数组元素
        System.out.println("\n===== 访问数组元素 =====");
        
        // 通过索引访问数组元素（索引从0开始）
        array2[0] = 100;
        array2[1] = 200;
        array2[2] = 300;
        array2[3] = 400;
        array2[4] = 500;
        
        System.out.println("array2[0] = " + array2[0]);
        System.out.println("array2[2] = " + array2[2]);
        
        // 获取数组长度
        System.out.println("array2的长度: " + array2.length);
        
        // 3. 遍历数组
        System.out.println("\n===== 遍历数组 =====");
        
        // 方式1：使用for循环
        System.out.print("使用for循环遍历: ");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i] + " ");
        }
        System.out.println();
        
        // 方式2：使用增强for循环（for-each）
        System.out.print("使用增强for循环遍历: ");
        for (int element : array3) {
            System.out.print(element + " ");
        }
        System.out.println();
        
        // 4. 多维数组
        System.out.println("\n===== 多维数组 =====");
        
        // 二维数组的声明和初始化
        int[][] matrix1 = new int[3][4]; // 3行4列的二维数组
        
        // 静态初始化二维数组
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        // 访问二维数组元素
        System.out.println("matrix2[1][2] = " + matrix2[1][2]); // 输出第2行第3列的元素
        
        // 遍历二维数组
        System.out.println("遍历二维数组:");
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        
        // 使用增强for循环遍历二维数组
        System.out.println("使用增强for循环遍历二维数组:");
        for (int[] row : matrix2) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
        
        // 5. Arrays工具类
        System.out.println("\n===== Arrays工具类 =====");
        
        int[] numbers = {5, 2, 9, 1, 7, 6, 3, 8, 4};
        
        // 排序
        Arrays.sort(numbers);
        System.out.println("排序后的数组: " + Arrays.toString(numbers));
        
        // 二分查找（要求数组已排序）
        int index = Arrays.binarySearch(numbers, 7);
        System.out.println("数字7在排序后数组中的索引: " + index);
        
        // 数组填充
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 10);
        System.out.println("填充后的数组: " + Arrays.toString(filledArray));
        
        // 数组复制
        int[] sourceArray = {1, 2, 3};
        int[] destArray = Arrays.copyOf(sourceArray, 5); // 复制并扩展长度
        System.out.println("复制后的数组: " + Arrays.toString(destArray));
        
        // 数组比较
        int[] array5 = {1, 2, 3, 4, 5};
        int[] array6 = {1, 2, 3, 4, 5};
        int[] array7 = {1, 2, 3, 5, 4};
        
        System.out.println("array5和array6相等: " + Arrays.equals(array5, array6));
        System.out.println("array5和array7相等: " + Arrays.equals(array5, array7));
        
        // 6. 数组常见异常
        System.out.println("\n===== 数组常见异常 =====");
        
        try {
            // 数组越界异常 (ArrayIndexOutOfBoundsException)
            System.out.println(array3[10]); // array3只有5个元素，访问索引10会抛出异常
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到数组越界异常: " + e.getMessage());
        }
    }
}
