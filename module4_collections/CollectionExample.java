/**
 * 模块四：集合框架&泛型
 * 示例1：Collection集合
 * 
 * 本示例展示了Java集合框架中Collection接口的常用实现类，
 * 包括List接口（ArrayList, LinkedList）和Set接口（HashSet, TreeSet）的使用。
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;

public class CollectionExample {
    public static void main(String[] args) {
        System.out.println("===== Java集合框架示例 =====");
        
        // 1. ArrayList示例
        System.out.println("\n===== ArrayList示例 =====");
        
        // 创建ArrayList
        List<String> arrayList = new ArrayList<>();
        
        // 添加元素
        arrayList.add("苹果");
        arrayList.add("香蕉");
        arrayList.add("橙子");
        arrayList.add("西瓜");
        arrayList.add("苹果");  // 允许重复元素
        
        System.out.println("ArrayList内容: " + arrayList);
        System.out.println("ArrayList大小: " + arrayList.size());
        
        // 访问元素
        System.out.println("索引1的元素: " + arrayList.get(1));
        
        // 修改元素
        arrayList.set(1, "葡萄");
        System.out.println("修改后的ArrayList: " + arrayList);
        
        // 删除元素
        arrayList.remove(0);  // 按索引删除
        arrayList.remove("西瓜");  // 按对象删除
        System.out.println("删除后的ArrayList: " + arrayList);
        
        // 检查元素是否存在
        System.out.println("是否包含'葡萄': " + arrayList.contains("葡萄"));
        
        // 2. LinkedList示例
        System.out.println("\n===== LinkedList示例 =====");
        
        // 创建LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        
        // 添加元素
        linkedList.add("红色");
        linkedList.add("绿色");
        linkedList.add("蓝色");
        
        // LinkedList特有的方法
        linkedList.addFirst("黑色");  // 在开头添加
        linkedList.addLast("白色");   // 在末尾添加
        
        System.out.println("LinkedList内容: " + linkedList);
        
        // 获取元素
        System.out.println("第一个元素: " + linkedList.getFirst());
        System.out.println("最后一个元素: " + linkedList.getLast());
        
        // 删除元素
        linkedList.removeFirst();  // 删除第一个
        linkedList.removeLast();   // 删除最后一个
        
        System.out.println("删除后的LinkedList: " + linkedList);
        
        // 3. HashSet示例
        System.out.println("\n===== HashSet示例 =====");
        
        // 创建HashSet
        Set<String> hashSet = new HashSet<>();
        
        // 添加元素
        hashSet.add("Java");
        hashSet.add("Python");
        hashSet.add("C++");
        hashSet.add("JavaScript");
        hashSet.add("Java");  // 重复元素不会被添加
        
        System.out.println("HashSet内容: " + hashSet);
        System.out.println("HashSet大小: " + hashSet.size());
        
        // 检查元素是否存在
        System.out.println("是否包含'Python': " + hashSet.contains("Python"));
        
        // 删除元素
        hashSet.remove("C++");
        System.out.println("删除后的HashSet: " + hashSet);
        
        // 4. TreeSet示例
        System.out.println("\n===== TreeSet示例 =====");
        
        // 创建TreeSet
        TreeSet<Integer> treeSet = new TreeSet<>();
        
        // 添加元素
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(10);
        treeSet.add(3);
        treeSet.add(7);
        treeSet.add(5);  // 重复元素不会被添加
        
        System.out.println("TreeSet内容: " + treeSet);  // 元素会自动排序
        
        // TreeSet特有的方法
        System.out.println("第一个元素: " + treeSet.first());
        System.out.println("最后一个元素: " + treeSet.last());
        System.out.println("小于等于4的最大元素: " + treeSet.floor(4));
        System.out.println("大于等于4的最小元素: " + treeSet.ceiling(4));
        
        // 获取子集
        System.out.println("大于3的元素: " + treeSet.tailSet(3, false));
        System.out.println("1到7之间的元素: " + treeSet.subSet(1, true, 7, true));
        
        // 5. 遍历集合的方式
        System.out.println("\n===== 遍历集合的方式 =====");
        
        List<String> fruits = new ArrayList<>();
        fruits.add("苹果");
        fruits.add("香蕉");
        fruits.add("橙子");
        
        // 方式1：使用for循环
        System.out.println("使用for循环遍历:");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }
        
        // 方式2：使用增强for循环（for-each）
        System.out.println("\n使用增强for循环遍历:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // 方式3：使用Iterator
        System.out.println("\n使用Iterator遍历:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);
        }
        
        // 方式4：使用Java 8的forEach方法和Lambda表达式
        System.out.println("\n使用forEach和Lambda表达式遍历:");
        fruits.forEach(fruit -> System.out.println(fruit));
        
        // 方式5：使用Java 8的Stream API
        System.out.println("\n使用Stream API遍历:");
        fruits.stream().forEach(System.out::println);
    }
}
