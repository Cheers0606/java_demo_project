/**
 * 模块五：IO流&文件处理
 * 示例3：NIO
 * 
 * 本示例展示了Java NIO (New IO)的使用，
 * 包括Path、Paths、Files工具类，以及
 * 通道（Channel）和缓冲区（Buffer）的基本操作。
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

public class NIOExample {
    public static void main(String[] args) {
        System.out.println("===== Java NIO示例 =====");
        
        // 准备测试目录
        try {
            Files.createDirectories(Paths.get("nio_test"));
        } catch (IOException e) {
            System.out.println("创建测试目录异常: " + e.getMessage());
        }
        
        // 1. Path和Paths
        System.out.println("\n===== Path和Paths示例 =====");
        
        // 创建Path对象
        Path path1 = Paths.get("nio_test/test.txt");
        Path path2 = Paths.get("nio_test", "data", "sample.txt");
        
        System.out.println("path1: " + path1);
        System.out.println("path2: " + path2);
        
        // Path操作
        System.out.println("path1的文件名: " + path1.getFileName());
        System.out.println("path1的父路径: " + path1.getParent());
        System.out.println("path1的根路径: " + path1.getRoot());
        System.out.println("path1的绝对路径: " + path1.toAbsolutePath());
        
        // 路径规范化
        Path nonNormalizedPath = Paths.get("nio_test/../nio_test/./sample.txt");
        Path normalizedPath = nonNormalizedPath.normalize();
        System.out.println("规范化前: " + nonNormalizedPath);
        System.out.println("规范化后: " + normalizedPath);
        
        // 路径解析
        Path basePath = Paths.get("nio_test");
        Path resolvedPath = basePath.resolve("subdir/file.txt");
        System.out.println("解析后的路径: " + resolvedPath);
        
        // 2. Files工具类
        System.out.println("\n===== Files工具类示例 =====");
        
        Path testFile = Paths.get("nio_test/files_test.txt");
        
        // 创建文件
        try {
            Files.createDirectories(testFile.getParent());
            Files.createFile(testFile);
            System.out.println("文件创建成功: " + testFile);
        } catch (FileAlreadyExistsException e) {
            System.out.println("文件已存在: " + testFile);
        } catch (IOException e) {
            System.out.println("创建文件异常: " + e.getMessage());
        }
        
        // 写入文件
        try {
            String content = "这是使用Files工具类写入的内容\n第二行内容\n第三行内容";
            Files.write(testFile, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("文件写入成功");
        } catch (IOException e) {
            System.out.println("写入文件异常: " + e.getMessage());
        }
        
        // 读取文件
        try {
            byte[] bytes = Files.readAllBytes(testFile);
            String content = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("读取的文件内容:\n" + content);
        } catch (IOException e) {
            System.out.println("读取文件异常: " + e.getMessage());
        }
        
        // 按行读取文件
        try {
            List<String> lines = Files.readAllLines(testFile, StandardCharsets.UTF_8);
            System.out.println("\n按行读取文件内容:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("第" + (i+1) + "行: " + lines.get(i));
            }
        } catch (IOException e) {
            System.out.println("按行读取文件异常: " + e.getMessage());
        }
        
        // 使用Stream读取文件
        try {
            System.out.println("\n使用Stream读取文件内容:");
            Stream<String> lines = Files.lines(testFile, StandardCharsets.UTF_8);
            lines.forEach(line -> System.out.println(line));
            lines.close();
        } catch (IOException e) {
            System.out.println("使用Stream读取文件异常: " + e.getMessage());
        }
        
        // 复制文件
        Path copyTarget = Paths.get("nio_test/files_copy.txt");
        try {
            Files.copy(testFile, copyTarget, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\n文件复制成功: " + copyTarget);
        } catch (IOException e) {
            System.out.println("复制文件异常: " + e.getMessage());
        }
        
        // 移动文件
        Path moveTarget = Paths.get("nio_test/files_moved.txt");
        try {
            Files.move(copyTarget, moveTarget, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件移动成功: " + moveTarget);
        } catch (IOException e) {
            System.out.println("移动文件异常: " + e.getMessage());
        }
        
        // 获取文件属性
        try {
            System.out.println("\n文件属性:");
            System.out.println("大小: " + Files.size(testFile) + " 字节");
            System.out.println("是否为目录: " + Files.isDirectory(testFile));
            System.out.println("是否为常规文件: " + Files.isRegularFile(testFile));
            System.out.println("是否可读: " + Files.isReadable(testFile));
            System.out.println("是否可写: " + Files.isWritable(testFile));
            System.out.println("是否可执行: " + Files.isExecutable(testFile));
            
            // 获取更多属性
            BasicFileAttributes attrs = Files.readAttributes(testFile, BasicFileAttributes.class);
            System.out.println("创建时间: " + attrs.creationTime());
            System.out.println("最后访问时间: " + attrs.lastAccessTime());
            System.out.println("最后修改时间: " + attrs.lastModifiedTime());
        } catch (IOException e) {
            System.out.println("获取文件属性异常: " + e.getMessage());
        }
        
        // 3. 通道（Channel）和缓冲区（Buffer）
        System.out.println("\n===== 通道和缓冲区示例 =====");
        
        Path channelFile = Paths.get("nio_test/channel_test.txt");
        
        // 使用通道写入文件
        try (FileChannel channel = FileChannel.open(channelFile, 
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            
            String text = "这是通过Channel写入的文本";
            ByteBuffer buffer = ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8));
            
            channel.write(buffer);
            System.out.println("通过Channel写入文件成功");
            
        } catch (IOException e) {
            System.out.println("通道写入异常: " + e.getMessage());
        }
        
        // 使用通道读取文件
        try (FileChannel channel = FileChannel.open(channelFile, StandardOpenOption.READ)) {
            
            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            // 读取数据到缓冲区
            int bytesRead = channel.read(buffer);
            
            if (bytesRead > 0) {
                // 切换缓冲区为读模式
                buffer.flip();
                
                // 从缓冲区读取数据
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                
                String content = new String(bytes, StandardCharsets.UTF_8);
                System.out.println("通过Channel读取的内容: " + content);
            }
            
        } catch (IOException e) {
            System.out.println("通道读取异常: " + e.getMessage());
        }
        
        // 4. 文件遍历
        System.out.println("\n===== 文件遍历示例 =====");
        
        // 准备测试目录和文件
        try {
            Path walkDir = Paths.get("nio_test/walk_test");
            Files.createDirectories(walkDir);
            Files.createDirectories(walkDir.resolve("subdir1"));
            Files.createDirectories(walkDir.resolve("subdir2"));
            
            Files.write(walkDir.resolve("file1.txt"), "文件1内容".getBytes());
            Files.write(walkDir.resolve("file2.txt"), "文件2内容".getBytes());
            Files.write(walkDir.resolve("subdir1/file3.txt"), "文件3内容".getBytes());
            
            // 使用walk方法遍历目录
            System.out.println("遍历目录内容:");
            try (Stream<Path> paths = Files.walk(walkDir)) {
                paths.forEach(p -> System.out.println(p));
            }
            
            // 使用walkFileTree方法和FileVisitor
            System.out.println("\n使用FileVisitor遍历目录:");
            Files.walkFileTree(walkDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("文件: " + file);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("进入目录: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            
        } catch (IOException e) {
            System.out.println("文件遍历异常: " + e.getMessage());
        }
    }
}
