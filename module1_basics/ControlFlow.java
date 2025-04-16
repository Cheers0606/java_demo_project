/**
 * 模块一：基础语法与环境搭建
 * 示例3：流程控制
 * 
 * 本示例展示了Java中的各种流程控制语句，
 * 包括条件语句（if-else, switch-case）和
 * 循环语句（for, while, do-while）以及
 * 跳转语句（break, continue, return）。
 */
public class ControlFlow {
    public static void main(String[] args) {
        // 1. 条件语句 - if-else
        System.out.println("===== if-else语句 =====");
        int score = 85;
        
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 80) {
            System.out.println("良好");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }
        
        // 三元运算符 - 条件表达式的简写形式
        String result = (score >= 60) ? "通过" : "未通过";
        System.out.println("考试结果: " + result);
        
        // 2. 条件语句 - switch-case
        System.out.println("\n===== switch-case语句 =====");
        int day = 3;
        
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                System.out.println("无效的日期");
                break;
        }
        
        // JDK 7引入的String类型switch
        String month = "February";
        switch (month) {
            case "January":
                System.out.println("1月");
                break;
            case "February":
                System.out.println("2月");
                break;
            case "March":
                System.out.println("3月");
                break;
            default:
                System.out.println("其他月份");
                break;
        }
        
        // 3. 循环语句 - for循环
        System.out.println("\n===== for循环 =====");
        System.out.print("普通for循环: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 增强型for循环（for-each），JDK 5引入
        System.out.print("增强型for循环: ");
        int[] numbers = {10, 20, 30, 40, 50};
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // 4. 循环语句 - while循环
        System.out.println("\n===== while循环 =====");
        int count = 1;
        System.out.print("while循环: ");
        while (count <= 5) {
            System.out.print(count + " ");
            count++;
        }
        System.out.println();
        
        // 5. 循环语句 - do-while循环
        System.out.println("\n===== do-while循环 =====");
        int j = 1;
        System.out.print("do-while循环: ");
        do {
            System.out.print(j + " ");
            j++;
        } while (j <= 5);
        System.out.println();
        
        // 6. 跳转语句 - break
        System.out.println("\n===== break语句 =====");
        System.out.print("带break的for循环: ");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                break; // 当i等于6时，跳出循环
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 7. 跳转语句 - continue
        System.out.println("\n===== continue语句 =====");
        System.out.print("带continue的for循环: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue; // 当i是偶数时，跳过当前迭代
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 8. 嵌套循环
        System.out.println("\n===== 嵌套循环 =====");
        System.out.println("9x9乘法表:");
        for (int i = 1; i <= 9; i++) {
            for (int k = 1; k <= i; k++) {
                System.out.print(k + "x" + i + "=" + (k * i) + "\t");
            }
            System.out.println();
        }
        
        // 9. 带标签的break和continue
        System.out.println("\n===== 带标签的break和continue =====");
        outerLoop: 
        for (int i = 1; i <= 3; i++) {
            for (int k = 1; k <= 3; k++) {
                if (i == 2 && k == 2) {
                    System.out.println("遇到i=2, k=2，跳出外层循环");
                    break outerLoop; // 跳出外层循环
                }
                System.out.println("i = " + i + ", k = " + k);
            }
        }
    }
}
