/**
 * 模块七：函数式编程&Lambda表达式
 * 示例2：Stream API
 * 
 * 本示例展示了Java 8中Stream API的使用，
 * 包括流的创建、中间操作、终止操作和并行流。
 */
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;

public class StreamAPI {
    public static void main(String[] args) {
        System.out.println("===== Stream API示例 =====");
        
        // 1. 创建流
        System.out.println("\n===== 创建流 =====");
        
        // 从集合创建流
        List<String> list = Arrays.asList("Java", "Python", "C++", "JavaScript", "Go");
        Stream<String> listStream = list.stream();
        System.out.println("从List创建流");
        
        // 从数组创建流
        String[] array = {"苹果", "香蕉", "橙子", "葡萄"};
        Stream<String> arrayStream = Arrays.stream(array);
        System.out.println("从数组创建流");
        
        // 使用Stream.of创建流
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
        System.out.println("使用Stream.of创建流");
        
        // 创建无限流
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        System.out.println("创建无限流（偶数序列）");
        
        // 使用generate创建流
        Stream<Double> randomStream = Stream.generate(Math::random);
        System.out.println("使用generate创建随机数流");
        
        // 2. 中间操作
        System.out.println("\n===== 中间操作 =====");
        
        // filter：过滤元素
        System.out.println("filter示例：过滤长度大于4的字符串");
        list.stream()
            .filter(s -> s.length() > 4)
            .forEach(System.out::println);
        
        // map：映射元素
        System.out.println("\nmap示例：将字符串转换为大写");
        list.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
        
        // flatMap：扁平化映射
        System.out.println("\nflatMap示例：扁平化嵌套集合");
        List<List<Integer>> nestedList = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        
        nestedList.stream()
            .flatMap(Collection::stream)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // distinct：去重
        System.out.println("\ndistinct示例：去除重复元素");
        Stream.of(1, 2, 2, 3, 3, 3, 4, 4, 5)
            .distinct()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // sorted：排序
        System.out.println("\nsorted示例：对元素进行排序");
        Stream.of(5, 3, 8, 1, 4)
            .sorted()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 自定义排序
        System.out.println("\n自定义排序示例：按字符串长度排序");
        list.stream()
            .sorted(Comparator.comparing(String::length))
            .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // limit：限制元素数量
        System.out.println("\nlimit示例：限制元素数量为3");
        list.stream()
            .limit(3)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // skip：跳过元素
        System.out.println("\nskip示例：跳过前2个元素");
        list.stream()
            .skip(2)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // peek：查看元素（通常用于调试）
        System.out.println("\npeek示例：在处理过程中查看元素");
        list.stream()
            .peek(s -> System.out.print("处理元素: " + s + " -> "))
            .map(String::toUpperCase)
            .forEach(System.out::println);
        
        // 3. 终止操作
        System.out.println("\n===== 终止操作 =====");
        
        // forEach：遍历元素
        System.out.println("forEach示例：遍历并打印元素");
        list.stream().forEach(System.out::println);
        
        // count：计数
        long count = list.stream().count();
        System.out.println("\ncount示例：流中元素的数量 = " + count);
        
        // collect：收集结果
        System.out.println("\ncollect示例：收集到List");
        List<String> upperCaseList = list.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("转换后的List: " + upperCaseList);
        
        // 收集到Set
        System.out.println("\n收集到Set");
        Set<String> upperCaseSet = list.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toSet());
        System.out.println("转换后的Set: " + upperCaseSet);
        
        // 收集到Map
        System.out.println("\n收集到Map");
        Map<String, Integer> lengthMap = list.stream()
            .collect(Collectors.toMap(
                s -> s,            // 键映射函数
                String::length     // 值映射函数
            ));
        System.out.println("字符串长度Map: " + lengthMap);
        
        // joining：连接字符串
        String joined = list.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("\njoining示例：连接字符串 = " + joined);
        
        // reduce：归约操作
        System.out.println("\nreduce示例：计算总和");
        int sum = Stream.of(1, 2, 3, 4, 5)
            .reduce(0, Integer::sum);
        System.out.println("总和 = " + sum);
        
        // 字符串连接
        String combined = list.stream()
            .reduce("", (s1, s2) -> s1 + " | " + s2);
        System.out.println("\n使用reduce连接字符串: " + combined);
        
        // findFirst：查找第一个元素
        Optional<String> first = list.stream()
            .filter(s -> s.startsWith("J"))
            .findFirst();
        System.out.println("\nfindFirst示例：以J开头的第一个元素 = " + first.orElse("没有找到"));
        
        // findAny：查找任意元素
        Optional<String> any = list.stream()
            .filter(s -> s.length() > 4)
            .findAny();
        System.out.println("\nfindAny示例：长度大于4的任意元素 = " + any.orElse("没有找到"));
        
        // anyMatch, allMatch, noneMatch
        boolean anyMatch = list.stream().anyMatch(s -> s.length() > 5);
        boolean allMatch = list.stream().allMatch(s -> s.length() > 0);
        boolean noneMatch = list.stream().noneMatch(s -> s.equals("PHP"));
        
        System.out.println("\n匹配操作:");
        System.out.println("anyMatch (任一元素长度>5): " + anyMatch);
        System.out.println("allMatch (所有元素长度>0): " + allMatch);
        System.out.println("noneMatch (没有元素等于PHP): " + noneMatch);
        
        // min, max
        Optional<String> min = list.stream()
            .min(Comparator.comparing(String::length));
        Optional<String> max = list.stream()
            .max(Comparator.comparing(String::length));
        
        System.out.println("\n最小值和最大值:");
        System.out.println("最短的字符串: " + min.orElse("空流"));
        System.out.println("最长的字符串: " + max.orElse("空流"));
        
        // 4. 并行流
        System.out.println("\n===== 并行流 =====");
        
        // 创建并行流
        Stream<String> parallelStream = list.parallelStream();
        System.out.println("从集合创建并行流");
        
        // 将顺序流转换为并行流
        Stream<String> parallel = list.stream().parallel();
        System.out.println("将顺序流转换为并行流");
        
        // 并行流性能示例
        long startTime = System.currentTimeMillis();
        
        // 使用顺序流
        long sequentialCount = IntStream.range(0, 10_000_000)
            .filter(n -> n % 2 == 0)
            .count();
        
        long sequentialTime = System.currentTimeMillis() - startTime;
        System.out.println("\n顺序流计算偶数个数: " + sequentialCount);
        System.out.println("顺序流耗时: " + sequentialTime + "ms");
        
        // 使用并行流
        startTime = System.currentTimeMillis();
        
        long parallelCount = IntStream.range(0, 10_000_000)
            .parallel()
            .filter(n -> n % 2 == 0)
            .count();
        
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("\n并行流计算偶数个数: " + parallelCount);
        System.out.println("并行流耗时: " + parallelTime + "ms");
        
        // 5. 实际应用示例
        System.out.println("\n===== 实际应用示例 =====");
        
        // 创建一些Person对象
        List<StreamApiPerson> people = Arrays.asList(
            new StreamApiPerson("张三", 25, "男", "北京"),
            new StreamApiPerson("李四", 30, "男", "上海"),
            new StreamApiPerson("王五", 22, "女", "广州"),
            new StreamApiPerson("赵六", 28, "女", "北京"),
            new StreamApiPerson("钱七", 35, "男", "深圳"),
            new StreamApiPerson("孙八", 26, "女", "上海")
        );
        
        // 筛选出年龄大于25的人
        System.out.println("年龄大于25的人:");
        people.stream()
            .filter(p -> p.getAge() > 25)
            .forEach(p -> System.out.println(p.getName() + ", " + p.getAge() + "岁"));
        
        // 按城市分组
        System.out.println("\n按城市分组:");
        Map<String, List<StreamApiPerson>> peopleByCity = people.stream()
            .collect(Collectors.groupingBy(StreamApiPerson::getCity));
        
        peopleByCity.forEach((city, cityPeople) -> {
            System.out.println(city + ":");
            cityPeople.forEach(p -> System.out.println("  - " + p.getName()));
        });
        
        // 统计男女人数
        System.out.println("\n统计男女人数:");
        Map<String, Long> countByGender = people.stream()
            .collect(Collectors.groupingBy(StreamApiPerson::getGender, Collectors.counting()));
        
        countByGender.forEach((gender, genderCount) -> {
            System.out.println(gender + ": " + genderCount + "人");
        });
        
        // 计算平均年龄
        double averageAge = people.stream()
            .mapToInt(StreamApiPerson::getAge)
            .average()
            .orElse(0);
        
        System.out.println("\n平均年龄: " + averageAge);
        
        // 找出每个城市年龄最大的人
        System.out.println("\n每个城市年龄最大的人:");
        Map<String, Optional<StreamApiPerson>> oldestByCity = people.stream()
            .collect(Collectors.groupingBy(
                    StreamApiPerson::getCity,
                Collectors.maxBy(Comparator.comparing(StreamApiPerson::getAge))
            ));
        
        oldestByCity.forEach((city, oldestPerson) -> {
            oldestPerson.ifPresent(p -> 
                System.out.println(city + ": " + p.getName() + ", " + p.getAge() + "岁")
            );
        });
    }
}

/**
 * Person类，用于Stream示例
 */
class StreamApiPerson {
    private String name;
    private int age;
    private String gender;
    private String city;
    
    public StreamApiPerson(String name, int age, String gender, String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.city = city;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getCity() {
        return city;
    }
    
    @Override
    public String toString() {
        return "StreamApiPerson{name='" + name + "', age=" + age +
               ", gender='" + gender + "', city='" + city + "'}";
    }
}
