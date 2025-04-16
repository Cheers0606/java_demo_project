/**
 * 模块八：Java8新特性&实战项目
 * 示例1：Optional类
 * 
 * 本示例展示了Java 8中Optional类的使用，
 * 包括Optional的创建、方法使用以及如何避免空指针异常。
 */
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class OptionalExample {
    public static void main(String[] args) {
        System.out.println("===== Optional类示例 =====");
        
        // 1. 创建Optional对象
        System.out.println("\n===== 创建Optional对象 =====");
        
        // 创建一个空的Optional
        Optional<String> empty = Optional.empty();
        System.out.println("空Optional: " + empty);
        
        // 创建一个包含非空值的Optional
        Optional<String> nonEmpty = Optional.of("Hello Optional");
        System.out.println("非空Optional: " + nonEmpty);
        
        // 创建一个可能包含空值的Optional
        String nullableValue = null;
        Optional<String> nullable = Optional.ofNullable(nullableValue);
        System.out.println("可能为空的Optional: " + nullable);
        
        String nonNullValue = "Non-null value";
        Optional<String> nonNullable = Optional.ofNullable(nonNullValue);
        System.out.println("非空值的Optional: " + nonNullable);
        
        // 2. 检查值是否存在
        System.out.println("\n===== 检查值是否存在 =====");
        
        System.out.println("empty.isPresent(): " + empty.isPresent());
        System.out.println("nonEmpty.isPresent(): " + nonEmpty.isPresent());
        
        // Java 11引入的isEmpty方法（如果使用Java 8，请注释掉这两行）
        // System.out.println("empty.isEmpty(): " + empty.isEmpty());
        // System.out.println("nonEmpty.isEmpty(): " + nonEmpty.isEmpty());
        
        // 3. 获取Optional中的值
        System.out.println("\n===== 获取Optional中的值 =====");
        
        // 使用get()方法（如果值不存在会抛出NoSuchElementException）
        try {
            System.out.println("nonEmpty.get(): " + nonEmpty.get());
            System.out.println("empty.get(): " + empty.get());
        } catch (Exception e) {
            System.out.println("捕获异常: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // 4. 安全地获取值
        System.out.println("\n===== 安全地获取值 =====");
        
        // 使用orElse - 提供默认值
        String valueFromEmpty = empty.orElse("默认值");
        String valueFromNonEmpty = nonEmpty.orElse("默认值");
        
        System.out.println("empty.orElse(\"默认值\"): " + valueFromEmpty);
        System.out.println("nonEmpty.orElse(\"默认值\"): " + valueFromNonEmpty);
        
        // 使用orElseGet - 提供默认值的Supplier
        String valueFromEmptySupplier = empty.orElseGet(() -> "通过Supplier提供的默认值");
        System.out.println("empty.orElseGet(() -> ...): " + valueFromEmptySupplier);
        
        // 使用orElseThrow - 在值不存在时抛出异常
        try {
            String valueOrThrow = empty.orElseThrow(() -> new IllegalStateException("没有值"));
        } catch (Exception e) {
            System.out.println("捕获异常: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // 5. 条件操作
        System.out.println("\n===== 条件操作 =====");
        
        // ifPresent - 当值存在时执行操作
        System.out.print("empty.ifPresent(): ");
        empty.ifPresent(value -> System.out.println("值存在: " + value));
        
        System.out.print("nonEmpty.ifPresent(): ");
        nonEmpty.ifPresent(value -> System.out.println("值存在: " + value));
        
        // 6. 转换和过滤
        System.out.println("\n===== 转换和过滤 =====");
        
        // map - 转换值
        Optional<Integer> lengthOpt = nonEmpty.map(String::length);
        System.out.println("nonEmpty.map(String::length): " + lengthOpt.orElse(0));
        
        // flatMap - 转换为另一个Optional
        Optional<String> upperOpt = nonEmpty.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println("nonEmpty.flatMap(value -> Optional.of(value.toUpperCase())): " + upperOpt.orElse(""));
        
        // filter - 过滤值
        Optional<String> filteredOpt = nonEmpty.filter(value -> value.contains("Optional"));
        System.out.println("nonEmpty.filter(value -> value.contains(\"Optional\")): " + filteredOpt.orElse("不匹配"));
        
        Optional<String> emptyAfterFilter = nonEmpty.filter(value -> value.contains("Java"));
        System.out.println("nonEmpty.filter(value -> value.contains(\"Java\")): " + emptyAfterFilter.orElse("不匹配"));
        
        // 7. 实际应用示例
        System.out.println("\n===== 实际应用示例 =====");
        
        // 创建一个用户映射
        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User(1, "张三", "zhangsan@example.com"));
        users.put(2, new User(2, "李四", "lisi@example.com"));
        // 用户3不存在
        users.put(4, new User(4, "赵六", null)); // 邮箱为null
        
        // 传统方式获取用户邮箱（容易出现NullPointerException）
        System.out.println("\n传统方式获取用户邮箱:");
        try {
            String email = getUserEmailTraditional(users, 1);
            System.out.println("用户1的邮箱: " + email);
            
            email = getUserEmailTraditional(users, 3); // 用户不存在
            System.out.println("用户3的邮箱: " + email);
        } catch (Exception e) {
            System.out.println("捕获异常: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // 使用Optional获取用户邮箱（避免NullPointerException）
        System.out.println("\n使用Optional获取用户邮箱:");
        String email = getUserEmailWithOptional(users, 1);
        System.out.println("用户1的邮箱: " + email);
        
        email = getUserEmailWithOptional(users, 3); // 用户不存在
        System.out.println("用户3的邮箱: " + email);
        
        email = getUserEmailWithOptional(users, 4); // 邮箱为null
        System.out.println("用户4的邮箱: " + email);
        
        // 8. 链式调用
        System.out.println("\n===== 链式调用 =====");
        
        String result = Optional.ofNullable(users.get(1))
                               .map(User::getEmail)
                               .filter(e -> e.contains("@"))
                               .map(String::toUpperCase)
                               .orElse("无效邮箱");
        
        System.out.println("链式调用结果: " + result);
        
        result = Optional.ofNullable(users.get(3)) // 用户不存在
                        .map(User::getEmail)
                        .filter(e -> e.contains("@"))
                        .map(String::toUpperCase)
                        .orElse("无效邮箱");
        
        System.out.println("链式调用结果(用户不存在): " + result);
        
        result = Optional.ofNullable(users.get(4)) // 邮箱为null
                        .map(User::getEmail)
                        .filter(e -> e != null && e.contains("@"))
                        .map(String::toUpperCase)
                        .orElse("无效邮箱");
        
        System.out.println("链式调用结果(邮箱为null): " + result);
    }
    
    // 传统方式获取用户邮箱（容易出现NullPointerException）
    private static String getUserEmailTraditional(Map<Integer, User> users, int userId) {
        User user = users.get(userId);
        return user.getEmail(); // 如果user为null，这里会抛出NullPointerException
    }
    
    // 使用Optional获取用户邮箱（避免NullPointerException）
    private static String getUserEmailWithOptional(Map<Integer, User> users, int userId) {
        return Optional.ofNullable(users.get(userId))
                      .map(User::getEmail)
                      .orElse("用户不存在或邮箱未设置");
    }
}

/**
 * 用户类
 */
class User {
    private int id;
    private String name;
    private String email;
    
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}
