/**
 * 模块五：IO流&文件处理
 * 示例2：IO流概述
 * 
 * 本示例展示了Java中常用的IO流类，
 * 包括字节流（InputStream/OutputStream）和字符流（Reader/Writer），
 * 以及缓冲流的使用。
 */
import java.io.*;

public class StreamExample {
    public static void main(String[] args) {
        System.out.println("===== IO流示例 =====");
        
        // 准备测试目录
        File testDir = new File("io_test");
        if (!testDir.exists()) {
            testDir.mkdir();
        }
        
        // 1. 文件字节流 - 写入
        System.out.println("\n===== 文件字节流写入 =====");
        
        try (FileOutputStream fos = new FileOutputStream("io_test/byte_data.bin")) {
            // 写入单个字节
            fos.write(65);  // 写入字符'A'的ASCII码
            
            // 写入字节数组
            byte[] bytes = {66, 67, 68, 69};  // 'B', 'C', 'D', 'E'
            fos.write(bytes);
            
            // 写入字节数组的一部分
            fos.write(bytes, 1, 2);  // 写入'C', 'D'
            
            System.out.println("字节数据写入成功");
        } catch (IOException e) {
            System.out.println("字节流写入异常: " + e.getMessage());
        }
        
        // 2. 文件字节流 - 读取
        System.out.println("\n===== 文件字节流读取 =====");
        
        try (FileInputStream fis = new FileInputStream("io_test/byte_data.bin")) {
            // 读取单个字节
            int firstByte = fis.read();
            System.out.println("第一个字节: " + firstByte + " (字符: " + (char)firstByte + ")");
            
            // 读取到字节数组
            byte[] buffer = new byte[10];
            int bytesRead = fis.read(buffer);
            
            System.out.print("读取的字节: ");
            for (int i = 0; i < bytesRead; i++) {
                System.out.print(buffer[i] + " ");
            }
            System.out.println();
            
            System.out.print("对应的字符: ");
            for (int i = 0; i < bytesRead; i++) {
                System.out.print((char)buffer[i] + " ");
            }
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("字节流读取异常: " + e.getMessage());
        }
        
        // 3. 文件字符流 - 写入
        System.out.println("\n===== 文件字符流写入 =====");
        
        try (FileWriter writer = new FileWriter("io_test/char_data.txt")) {
            // 写入单个字符
            writer.write('你');
            
            // 写入字符串
            writer.write("好，Java！");
            
            // 写入字符数组
            char[] chars = {'H', 'e', 'l', 'l', 'o'};
            writer.write(chars);
            
            // 写入字符数组的一部分
            writer.write(chars, 1, 3);  // 'e', 'l', 'l'
            
            System.out.println("字符数据写入成功");
        } catch (IOException e) {
            System.out.println("字符流写入异常: " + e.getMessage());
        }
        
        // 4. 文件字符流 - 读取
        System.out.println("\n===== 文件字符流读取 =====");
        
        try (FileReader reader = new FileReader("io_test/char_data.txt")) {
            // 读取单个字符
            int firstChar = reader.read();
            System.out.println("第一个字符: " + (char)firstChar);
            
            // 读取到字符数组
            char[] buffer = new char[20];
            int charsRead = reader.read(buffer);
            
            System.out.print("读取的字符: ");
            for (int i = 0; i < charsRead; i++) {
                System.out.print(buffer[i]);
            }
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("字符流读取异常: " + e.getMessage());
        }
        
        // 5. 缓冲字节流
        System.out.println("\n===== 缓冲字节流 =====");
        
        // 写入
        try (BufferedOutputStream bos = 
                new BufferedOutputStream(new FileOutputStream("io_test/buffered_byte.bin"))) {
            
            for (int i = 0; i < 1000; i++) {
                bos.write(i % 256);  // 写入0-255循环的字节
            }
            
            System.out.println("缓冲字节流写入成功");
        } catch (IOException e) {
            System.out.println("缓冲字节流写入异常: " + e.getMessage());
        }
        
        // 读取
        try (BufferedInputStream bis = 
                new BufferedInputStream(new FileInputStream("io_test/buffered_byte.bin"))) {
            
            byte[] buffer = new byte[100];
            int bytesRead = bis.read(buffer, 0, 10);  // 只读取前10个字节
            
            System.out.print("前10个字节: ");
            for (int i = 0; i < bytesRead; i++) {
                System.out.print(buffer[i] + " ");
            }
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("缓冲字节流读取异常: " + e.getMessage());
        }
        
        // 6. 缓冲字符流
        System.out.println("\n===== 缓冲字符流 =====");
        
        // 写入
        try (BufferedWriter writer = 
                new BufferedWriter(new FileWriter("io_test/buffered_char.txt"))) {
            
            writer.write("这是使用BufferedWriter写入的第一行");
            writer.newLine();  // 写入一个换行符
            writer.write("这是第二行");
            writer.newLine();
            writer.write("这是第三行");
            
            System.out.println("缓冲字符流写入成功");
        } catch (IOException e) {
            System.out.println("缓冲字符流写入异常: " + e.getMessage());
        }
        
        // 读取
        try (BufferedReader reader = 
                new BufferedReader(new FileReader("io_test/buffered_char.txt"))) {
            
            String line;
            System.out.println("按行读取文件内容:");
            
            // readLine()方法读取一行文本
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            System.out.println("缓冲字符流读取异常: " + e.getMessage());
        }
        
        // 7. 数据流 - 用于读写基本数据类型
        System.out.println("\n===== 数据流 =====");
        
        // 写入
        try (DataOutputStream dos = 
                new DataOutputStream(new FileOutputStream("io_test/data.bin"))) {
            
            // 写入各种基本数据类型
            dos.writeInt(12345);
            dos.writeDouble(3.14159);
            dos.writeBoolean(true);
            dos.writeUTF("Hello, DataOutputStream!");
            
            System.out.println("数据流写入成功");
        } catch (IOException e) {
            System.out.println("数据流写入异常: " + e.getMessage());
        }
        
        // 读取
        try (DataInputStream dis = 
                new DataInputStream(new FileInputStream("io_test/data.bin"))) {
            
            // 按写入顺序读取
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean booleanValue = dis.readBoolean();
            String stringValue = dis.readUTF();
            
            System.out.println("读取的整数: " + intValue);
            System.out.println("读取的浮点数: " + doubleValue);
            System.out.println("读取的布尔值: " + booleanValue);
            System.out.println("读取的字符串: " + stringValue);
            
        } catch (IOException e) {
            System.out.println("数据流读取异常: " + e.getMessage());
        }
        
        // 8. 对象流 - 用于读写对象（序列化和反序列化）
        System.out.println("\n===== 对象流 =====");
        
        // 创建一个可序列化的对象
        Person person = new Person("张三", 25);
        
        // 写入对象（序列化）
        try (ObjectOutputStream oos = 
                new ObjectOutputStream(new FileOutputStream("io_test/object.bin"))) {
            
            oos.writeObject(person);
            System.out.println("对象序列化成功");
            
        } catch (IOException e) {
            System.out.println("对象序列化异常: " + e.getMessage());
        }
        
        // 读取对象（反序列化）
        try (ObjectInputStream ois = 
                new ObjectInputStream(new FileInputStream("io_test/object.bin"))) {
            
            Person deserializedPerson = (Person) ois.readObject();
            System.out.println("反序列化的对象: " + deserializedPerson);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("对象反序列化异常: " + e.getMessage());
        }
    }
}

