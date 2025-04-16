/**
 * 模块四：集合框架&泛型
 * 示例3：Map集合
 * 
 * 本示例展示了Java集合框架中Map接口的常用实现类，
 * 包括HashMap、TreeMap的使用，以及Properties类的应用。
 */
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Properties;
import java.util.Set;
import java.util.Collection;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MapExample {
    public static void main(String[] args) {
        System.out.println("===== Map集合示例 =====");
        
        // 1. HashMap示例
        System.out.println("\n===== HashMap示例 =====");
        
        // 创建HashMap
        Map<String, Integer> scores = new HashMap<>();
        
        // 添加键值对
        scores.put("张三", 95);
        scores.put("李四", 88);
        scores.put("王五", 75);
        scores.put("赵六", 90);
        
        // 获取值
        System.out.println("张三的分数: " + scores.get("张三"));
        
        // 修改值
        scores.put("王五", 80);  // 相同的键，值会被覆盖
        System.out.println("修改后王五的分数: " + scores.get("王五"));
        
        // 检查键是否存在
        System.out.println("是否包含'李四'这个键: " + scores.containsKey("李四"));
        System.out.println("是否包含90这个值: " + scores.containsValue(90));
        
        // 获取所有键
        System.out.println("所有学生: " + scores.keySet());
        
        // 获取所有值
        System.out.println("所有分数: " + scores.values());
        
        // 获取Map大小
        System.out.println("学生人数: " + scores.size());
        
        // 删除键值对
        scores.remove("赵六");
        System.out.println("删除后的Map: " + scores);
        
        // 2. 遍历Map的方式
        System.out.println("\n===== 遍历Map的方式 =====");
        
        Map<String, String> capitals = new HashMap<>();
        capitals.put("中国", "北京");
        capitals.put("日本", "东京");
        capitals.put("法国", "巴黎");
        capitals.put("英国", "伦敦");
        
        // 方式1：使用keySet()遍历键，然后获取值
        System.out.println("使用keySet()遍历:");
        Set<String> countries = capitals.keySet();
        for (String country : countries) {
            System.out.println(country + "的首都是" + capitals.get(country));
        }
        
        // 方式2：使用entrySet()遍历键值对
        System.out.println("\n使用entrySet()遍历:");
        Set<Map.Entry<String, String>> entries = capitals.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + "的首都是" + entry.getValue());
        }
        
        // 方式3：使用Java 8的forEach方法
        System.out.println("\n使用forEach方法遍历:");
        capitals.forEach((country, capital) -> 
            System.out.println(country + "的首都是" + capital));
        
        // 3. TreeMap示例（有序的Map）
        System.out.println("\n===== TreeMap示例 =====");
        
        // 创建TreeMap
        TreeMap<String, Double> salaries = new TreeMap<>();
        
        // 添加键值对
        salaries.put("张经理", 15000.0);
        salaries.put("王主管", 12000.0);
        salaries.put("李工程师", 10000.0);
        salaries.put("赵助理", 8000.0);
        
        // TreeMap会按键的自然顺序排序
        System.out.println("按键排序后的TreeMap: " + salaries);
        
        // TreeMap特有的方法
        System.out.println("第一个键: " + salaries.firstKey());
        System.out.println("最后一个键: " + salaries.lastKey());
        System.out.println("小于'王主管'的条目: " + salaries.headMap("王主管"));
        System.out.println("大于等于'王主管'的条目: " + salaries.tailMap("王主管"));
        
        // 4. LinkedHashMap示例（保持插入顺序）
        System.out.println("\n===== LinkedHashMap示例 =====");
        
        // 创建LinkedHashMap
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        
        // 添加键值对
        linkedHashMap.put("first", "第一");
        linkedHashMap.put("second", "第二");
        linkedHashMap.put("third", "第三");
        linkedHashMap.put("fourth", "第四");
        
        // LinkedHashMap保持插入顺序
        System.out.println("LinkedHashMap内容: " + linkedHashMap);
        
        // 5. Properties类（特殊的Map，用于处理属性文件）
        System.out.println("\n===== Properties类示例 =====");
        
        // 创建Properties对象
        Properties properties = new Properties();
        
        // 设置属性
        properties.setProperty("username", "admin");
        properties.setProperty("password", "123456");
        properties.setProperty("database", "mysql");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        
        // 获取属性
        System.out.println("用户名: " + properties.getProperty("username"));
        System.out.println("密码: " + properties.getProperty("password"));
        
        // 获取所有属性名
        System.out.println("所有属性名: " + properties.stringPropertyNames());
        
        try {
            // 将属性保存到文件
            String filePath = "database.properties";
            properties.store(new FileOutputStream(filePath), "Database Configuration");
            System.out.println("属性已保存到文件: " + filePath);
            
            // 从文件加载属性
            Properties loadedProps = new Properties();
            loadedProps.load(new FileInputStream(filePath));
            System.out.println("从文件加载的属性: " + loadedProps);
            System.out.println("从文件加载的URL: " + loadedProps.getProperty("url"));
        } catch (IOException e) {
            System.out.println("文件操作异常: " + e.getMessage());
        }
    }
}
