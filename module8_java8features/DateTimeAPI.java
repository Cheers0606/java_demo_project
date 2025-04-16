/**
 * 模块八：Java8新特性&实战项目
 * 示例2：日期时间API
 * 
 * 本示例展示了Java 8中新的日期时间API的使用，
 * 包括LocalDate/LocalTime/LocalDateTime、ZonedDateTime、
 * Period与Duration以及日期时间格式化。
 */
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.Locale;

public class DateTimeAPI {
    public static void main(String[] args) {
        System.out.println("===== Java 8日期时间API示例 =====");
        
        // 1. LocalDate - 表示日期（年月日）
        System.out.println("\n===== LocalDate示例 =====");
        
        // 获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println("当前日期: " + today);
        
        // 创建特定日期
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        System.out.println("生日: " + birthday);
        
        // 从字符串解析日期
        LocalDate parsedDate = LocalDate.parse("2023-10-20");
        System.out.println("解析的日期: " + parsedDate);
        
        // 获取日期的各个部分
        System.out.println("年: " + today.getYear());
        System.out.println("月: " + today.getMonth() + " (" + today.getMonthValue() + ")");
        System.out.println("日: " + today.getDayOfMonth());
        System.out.println("星期: " + today.getDayOfWeek());
        System.out.println("一年中的第几天: " + today.getDayOfYear());
        
        // 日期计算
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("下周的今天: " + nextWeek);
        
        LocalDate threeMonthsAgo = today.minusMonths(3);
        System.out.println("三个月前: " + threeMonthsAgo);
        
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天: " + firstDayOfMonth);
        
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("今年最后一天: " + lastDayOfYear);
        
        // 日期比较
        boolean isBefore = birthday.isBefore(today);
        boolean isAfter = birthday.isAfter(today);
        System.out.println("生日是否在今天之前: " + isBefore);
        System.out.println("生日是否在今天之后: " + isAfter);
        
        // 2. LocalTime - 表示时间（时分秒纳秒）
        System.out.println("\n===== LocalTime示例 =====");
        
        // 获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println("当前时间: " + now);
        
        // 创建特定时间
        LocalTime meetingTime = LocalTime.of(14, 30, 0);
        System.out.println("会议时间: " + meetingTime);
        
        // 从字符串解析时间
        LocalTime parsedTime = LocalTime.parse("08:45:30");
        System.out.println("解析的时间: " + parsedTime);
        
        // 获取时间的各个部分
        System.out.println("小时: " + now.getHour());
        System.out.println("分钟: " + now.getMinute());
        System.out.println("秒: " + now.getSecond());
        System.out.println("纳秒: " + now.getNano());
        
        // 时间计算
        LocalTime twoHoursLater = now.plusHours(2);
        System.out.println("两小时后: " + twoHoursLater);
        
        LocalTime thirtyMinutesBefore = now.minusMinutes(30);
        System.out.println("三十分钟前: " + thirtyMinutesBefore);
        
        // 3. LocalDateTime - 表示日期和时间
        System.out.println("\n===== LocalDateTime示例 =====");
        
        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("当前日期时间: " + currentDateTime);
        
        // 创建特定日期时间
        LocalDateTime eventDateTime = LocalDateTime.of(2023, 12, 31, 23, 59, 59);
        System.out.println("跨年活动时间: " + eventDateTime);
        
        // 从LocalDate和LocalTime创建
        LocalDateTime fromDateAndTime = LocalDateTime.of(today, meetingTime);
        System.out.println("从日期和时间创建: " + fromDateAndTime);
        
        // 日期时间计算
        LocalDateTime nextYear = currentDateTime.plusYears(1);
        System.out.println("一年后: " + nextYear);
        
        // 提取日期和时间部分
        LocalDate dateComponent = currentDateTime.toLocalDate();
        LocalTime timeComponent = currentDateTime.toLocalTime();
        System.out.println("日期部分: " + dateComponent);
        System.out.println("时间部分: " + timeComponent);
        
        // 4. ZonedDateTime - 带时区的日期时间
        System.out.println("\n===== ZonedDateTime示例 =====");
        
        // 获取当前带时区的日期时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前带时区的日期时间: " + zonedDateTime);
        
        // 获取特定时区的日期时间
        ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        
        System.out.println("东京时间: " + tokyoTime);
        System.out.println("纽约时间: " + newYorkTime);
        
        // 转换时区
        ZonedDateTime convertedTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("转换为洛杉矶时间: " + convertedTime);
        
        // 获取所有可用的时区ID
        System.out.println("\n部分可用的时区ID:");
        ZoneId.getAvailableZoneIds().stream()
            .filter(zoneId -> zoneId.startsWith("Asia") || zoneId.startsWith("Europe"))
            .limit(10)
            .forEach(System.out::println);
        
        // 5. Instant - 表示时间戳
        System.out.println("\n===== Instant示例 =====");
        
        // 获取当前时间戳
        Instant instant = Instant.now();
        System.out.println("当前时间戳: " + instant);
        
        // 从毫秒创建Instant
        Instant fromEpochMilli = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println("从毫秒创建的时间戳: " + fromEpochMilli);
        
        // 获取秒数和纳秒部分
        System.out.println("秒数: " + instant.getEpochSecond());
        System.out.println("纳秒部分: " + instant.getNano());
        
        // 时间戳计算
        Instant future = instant.plusSeconds(3600); // 一小时后
        System.out.println("一小时后的时间戳: " + future);
        
        // 转换为ZonedDateTime
        ZonedDateTime fromInstant = instant.atZone(ZoneId.systemDefault());
        System.out.println("从时间戳转换为系统默认时区的日期时间: " + fromInstant);
        
        // 6. Period - 表示日期间隔
        System.out.println("\n===== Period示例 =====");
        
        // 创建Period
        Period period = Period.between(birthday, today);
        System.out.println("从生日到今天的Period: " + period);
        
        // 获取Period的各个部分
        System.out.println("年数: " + period.getYears());
        System.out.println("月数: " + period.getMonths());
        System.out.println("天数: " + period.getDays());
        System.out.println("总月数: " + period.toTotalMonths());
        
        // 创建特定的Period
        Period sixMonths = Period.ofMonths(6);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        
        System.out.println("六个月的Period: " + sixMonths);
        System.out.println("2年6个月1天的Period: " + twoYearsSixMonthsOneDay);
        
        // 使用Period进行日期计算
        LocalDate futureDate = today.plus(sixMonths);
        System.out.println("六个月后的日期: " + futureDate);
        
        // 7. Duration - 表示时间间隔
        System.out.println("\n===== Duration示例 =====");
        
        // 创建Duration
        Duration duration = Duration.between(LocalTime.of(9, 0), LocalTime.of(17, 30));
        System.out.println("工作时间的Duration: " + duration);
        
        // 获取Duration的各个部分
        System.out.println("秒数: " + duration.getSeconds());
        System.out.println("纳秒部分: " + duration.getNano());
        System.out.println("分钟数: " + duration.toMinutes());
        System.out.println("小时数: " + duration.toHours());
        
        // 创建特定的Duration
        Duration twoHours = Duration.ofHours(2);
        Duration tenMinutes = Duration.ofMinutes(10);
        
        System.out.println("两小时的Duration: " + twoHours);
        System.out.println("十分钟的Duration: " + tenMinutes);
        
        // 使用Duration进行时间计算
        LocalTime endTime = now.plus(twoHours);
        System.out.println("两小时后的时间: " + endTime);
        
        // 8. 日期时间格式化
        System.out.println("\n===== 日期时间格式化 =====");
        
        // 使用预定义的格式化器
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String formattedDateTime = currentDateTime.format(isoFormatter);
        System.out.println("ISO格式化: " + formattedDateTime);
        
        // 自定义格式化器
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String customFormatted = currentDateTime.format(customFormatter);
        System.out.println("自定义格式化: " + customFormatted);
        
        // 带区域的格式化器
        DateTimeFormatter localizedFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 EEEE", Locale.CHINESE);
        String localizedFormatted = today.format(localizedFormatter);
        System.out.println("带区域的格式化: " + localizedFormatted);
        
        // 解析日期时间字符串
        LocalDate parsedFromCustom = LocalDate.parse("2023年10月20日", 
                                                  DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        System.out.println("从自定义格式解析: " + parsedFromCustom);
        
        // 9. 实际应用示例
        System.out.println("\n===== 实际应用示例 =====");
        
        // 计算两个日期之间的工作日数量
        LocalDate startDate = LocalDate.of(2023, 10, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 31);
        
        long workdays = countWorkdays(startDate, endDate);
        System.out.println("从 " + startDate + " 到 " + endDate + " 的工作日数量: " + workdays);
        
        // 计算下一个生日还有多少天
        LocalDate nextBirthday = calculateNextBirthday(birthday);
        long daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday);
        System.out.println("距离下一个生日还有 " + daysUntilBirthday + " 天");
        
        // 计算年龄
        int age = calculateAge(birthday);
        System.out.println("年龄: " + age + " 岁");
    }
    
    /**
     * 计算两个日期之间的工作日数量（周一至周五）
     */
    private static long countWorkdays(LocalDate startDate, LocalDate endDate) {
        long workdays = 0;
        LocalDate date = startDate;
        
        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                workdays++;
            }
            date = date.plusDays(1);
        }
        
        return workdays;
    }
    
    /**
     * 计算下一个生日日期
     */
    private static LocalDate calculateNextBirthday(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = birthday.withYear(today.getYear());
        
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        
        return nextBirthday;
    }
    
    /**
     * 计算年龄
     */
    private static int calculateAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return Period.between(birthday, today).getYears();
    }
}
