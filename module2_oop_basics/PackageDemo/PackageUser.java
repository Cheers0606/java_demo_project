/**
 * 模块二：面向对象基础
 * 示例2：包的概念与使用（配套类）
 * 
 * 这是PackageExample的配套类，展示了同一个包中类的定义和使用。
 */
package PackageDemo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class PackageUser {
    private String name;
    private Date registerDate;
    
    // 构造方法
    public PackageUser(String name) {
        this.name = name;
        this.registerDate = new Date();
    }
    
    // 显示用户信息
    public void displayInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n===== 用户信息 =====");
        System.out.println("用户名: " + this.name);
        System.out.println("注册时间: " + sdf.format(this.registerDate));
    }
    
    // getter和setter方法
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getRegisterDate() {
        return registerDate;
    }
}
