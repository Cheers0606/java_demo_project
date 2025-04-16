/**
 * 模块五：IO流&文件处理
 * 示例1：File类
 * 
 * 本示例展示了Java中File类的使用，
 * 包括文件和目录的创建、删除、重命名等操作，
 * 以及文件属性的获取和文件过滤器的使用。
 */
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileExample {
    public static void main(String[] args) {
        System.out.println("===== File类示例 =====");
        
        // 1. 创建File对象
        System.out.println("\n===== 创建File对象 =====");
        
        // 方式1：使用绝对路径
        File file1 = new File("C:/temp/test.txt");
        System.out.println("file1的绝对路径: " + file1.getAbsolutePath());
        
        // 方式2：使用相对路径
        File file2 = new File("test.txt");
        System.out.println("file2的绝对路径: " + file2.getAbsolutePath());
        
        // 方式3：使用父路径和子路径
        File file3 = new File("C:/temp", "data.txt");
        System.out.println("file3的绝对路径: " + file3.getAbsolutePath());
        
        // 方式4：使用父File对象和子路径
        File directory = new File("C:/temp");
        File file4 = new File(directory, "config.txt");
        System.out.println("file4的绝对路径: " + file4.getAbsolutePath());
        
        // 2. 创建目录
        System.out.println("\n===== 创建目录 =====");
        
        File dir1 = new File("testDir");
        if (!dir1.exists()) {
            boolean created = dir1.mkdir();
            System.out.println("目录创建" + (created ? "成功" : "失败") + ": " + dir1.getAbsolutePath());
        } else {
            System.out.println("目录已存在: " + dir1.getAbsolutePath());
        }
        
        // 创建多级目录
        File dir2 = new File("testDir/subDir1/subDir2");
        if (!dir2.exists()) {
            boolean created = dir2.mkdirs();  // 注意这里使用mkdirs()
            System.out.println("多级目录创建" + (created ? "成功" : "失败") + ": " + dir2.getAbsolutePath());
        }
        
        // 3. 创建文件
        System.out.println("\n===== 创建文件 =====");
        
        File newFile = new File("testDir/testFile.txt");
        try {
            if (!newFile.exists()) {
                boolean created = newFile.createNewFile();
                System.out.println("文件创建" + (created ? "成功" : "失败") + ": " + newFile.getAbsolutePath());
            } else {
                System.out.println("文件已存在: " + newFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("创建文件异常: " + e.getMessage());
        }
        
        // 4. 获取文件信息
        System.out.println("\n===== 获取文件信息 =====");
        
        if (newFile.exists()) {
            System.out.println("文件名: " + newFile.getName());
            System.out.println("绝对路径: " + newFile.getAbsolutePath());
            System.out.println("是否为文件: " + newFile.isFile());
            System.out.println("是否为目录: " + newFile.isDirectory());
            System.out.println("是否可读: " + newFile.canRead());
            System.out.println("是否可写: " + newFile.canWrite());
            System.out.println("是否可执行: " + newFile.canExecute());
            System.out.println("文件大小(字节): " + newFile.length());
            
            // 格式化最后修改时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("最后修改时间: " + sdf.format(new Date(newFile.lastModified())));
        }
        
        // 5. 文件重命名
        System.out.println("\n===== 文件重命名 =====");
        
        File renamedFile = new File("testDir/renamedFile.txt");
        if (newFile.exists()) {
            boolean renamed = newFile.renameTo(renamedFile);
            System.out.println("文件重命名" + (renamed ? "成功" : "失败"));
        }
        
        // 6. 列出目录内容
        System.out.println("\n===== 列出目录内容 =====");
        
        File dir = new File("testDir");
        if (dir.exists() && dir.isDirectory()) {
            System.out.println("testDir目录中的文件和子目录:");
            String[] fileList = dir.list();
            if (fileList != null) {
                for (String fileName : fileList) {
                    System.out.println(fileName);
                }
            }
            
            System.out.println("\ntestDir目录中的文件和子目录(File对象):");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName() + (file.isDirectory() ? " (目录)" : " (文件)"));
                }
            }
        }
        
        // 7. 使用文件过滤器
        System.out.println("\n===== 使用文件过滤器 =====");
        
        // 创建一些测试文件
        try {
            new File("testDir/test1.txt").createNewFile();
            new File("testDir/test2.txt").createNewFile();
            new File("testDir/data.csv").createNewFile();
            new File("testDir/image.jpg").createNewFile();
        } catch (IOException e) {
            System.out.println("创建测试文件异常: " + e.getMessage());
        }
        
        // 使用FilenameFilter过滤.txt文件
        FilenameFilter txtFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        
        System.out.println("testDir目录中的.txt文件:");
        String[] txtFiles = dir.list(txtFilter);
        if (txtFiles != null) {
            for (String fileName : txtFiles) {
                System.out.println(fileName);
            }
        }
        
        // 使用Lambda表达式实现文件过滤器
        System.out.println("\ntestDir目录中的.csv文件:");
        String[] csvFiles = dir.list((d, name) -> name.endsWith(".csv"));
        if (csvFiles != null) {
            for (String fileName : csvFiles) {
                System.out.println(fileName);
            }
        }
        
        // 8. 删除文件和目录
        System.out.println("\n===== 删除文件和目录 =====");
        
        File fileToDelete = new File("testDir/test1.txt");
        if (fileToDelete.exists()) {
            boolean deleted = fileToDelete.delete();
            System.out.println("文件删除" + (deleted ? "成功" : "失败") + ": " + fileToDelete.getName());
        }
        
        // 注意：只能删除空目录
        File dirToDelete = new File("testDir/emptyDir");
        dirToDelete.mkdir();  // 创建一个空目录
        if (dirToDelete.exists()) {
            boolean deleted = dirToDelete.delete();
            System.out.println("目录删除" + (deleted ? "成功" : "失败") + ": " + dirToDelete.getName());
        }
        
        // 9. 设置文件权限
        System.out.println("\n===== 设置文件权限 =====");
        
        File permissionFile = new File("testDir/permission.txt");
        try {
            permissionFile.createNewFile();
            
            // 设置文件权限
            permissionFile.setReadable(true);    // 可读
            permissionFile.setWritable(true);    // 可写
            permissionFile.setExecutable(false); // 不可执行
            
            System.out.println("文件权限设置后:");
            System.out.println("可读: " + permissionFile.canRead());
            System.out.println("可写: " + permissionFile.canWrite());
            System.out.println("可执行: " + permissionFile.canExecute());
        } catch (IOException e) {
            System.out.println("创建权限测试文件异常: " + e.getMessage());
        }
        
        // 10. 文件路径分隔符
        System.out.println("\n===== 文件路径分隔符 =====");
        
        System.out.println("路径分隔符: " + File.pathSeparator);  // 在Windows上是分号;，在Unix/Linux上是冒号:
        System.out.println("文件分隔符: " + File.separator);      // 在Windows上是反斜杠\，在Unix/Linux上是正斜杠/
        
        // 使用平台无关的文件路径
        File platformFile = new File("testDir" + File.separator + "platform.txt");
        System.out.println("平台无关的文件路径: " + platformFile.getPath());
    }
}
