/**
 * 模块八：Java8新特性&实战项目
 * 示例3：Base64
 * 
 * 本示例展示了Java 8中Base64编码与解码的使用，
 * 包括基本编码、URL安全编码和MIME编码。
 */
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Example {
    public static void main(String[] args) {
        System.out.println("===== Java 8 Base64示例 =====");
        
        // 1. 基本编码与解码
        System.out.println("\n===== 基本编码与解码 =====");
        
        // 创建编码器和解码器
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        
        // 编码字符串
        String originalString = "Java 8 Base64编码示例";
        byte[] encodedBytes = encoder.encode(originalString.getBytes(StandardCharsets.UTF_8));
        String encodedString = new String(encodedBytes, StandardCharsets.UTF_8);
        
        System.out.println("原始字符串: " + originalString);
        System.out.println("编码后: " + encodedString);
        
        // 解码字符串
        byte[] decodedBytes = decoder.decode(encodedBytes);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        
        System.out.println("解码后: " + decodedString);
        
        // 直接获取编码字符串
        String directEncodedString = encoder.encodeToString(originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println("直接编码为字符串: " + directEncodedString);
        
        // 2. URL安全编码与解码
        System.out.println("\n===== URL安全编码与解码 =====");
        
        // 创建URL安全的编码器和解码器
        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        
        // 包含特殊字符的字符串
        String urlOriginalString = "https://example.com/path?param=value&other=123+456";
        
        // 使用基本编码器
        String basicEncoded = encoder.encodeToString(urlOriginalString.getBytes(StandardCharsets.UTF_8));
        System.out.println("基本编码: " + basicEncoded);
        
        // 使用URL安全编码器
        String urlEncoded = urlEncoder.encodeToString(urlOriginalString.getBytes(StandardCharsets.UTF_8));
        System.out.println("URL安全编码: " + urlEncoded);
        
        // 解码URL安全编码的字符串
        byte[] urlDecodedBytes = urlDecoder.decode(urlEncoded);
        String urlDecodedString = new String(urlDecodedBytes, StandardCharsets.UTF_8);
        
        System.out.println("URL安全解码: " + urlDecodedString);
        
        // 3. MIME编码与解码
        System.out.println("\n===== MIME编码与解码 =====");
        
        // 创建MIME编码器和解码器
        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
        
        // 长字符串
        StringBuilder longStringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            longStringBuilder.append("这是一个很长的字符串，用于演示MIME编码。MIME编码会在输出中插入换行符。");
        }
        String longString = longStringBuilder.toString();
        
        // MIME编码
        String mimeEncoded = mimeEncoder.encodeToString(longString.getBytes(StandardCharsets.UTF_8));
        
        System.out.println("MIME编码（长度: " + mimeEncoded.length() + "）: ");
        System.out.println(mimeEncoded);
        
        // MIME解码
        byte[] mimeDecodedBytes = mimeDecoder.decode(mimeEncoded);
        String mimeDecodedString = new String(mimeDecodedBytes, StandardCharsets.UTF_8);
        
        System.out.println("MIME解码后长度: " + mimeDecodedString.length());
        
        // 4. 自定义行长度和分隔符的MIME编码器
        System.out.println("\n===== 自定义MIME编码器 =====");
        
        // 创建自定义MIME编码器（行长度为64，分隔符为"\r\n"）
        Base64.Encoder customMimeEncoder = Base64.getMimeEncoder(64, "\r\n".getBytes());
        
        // 编码
        String customMimeEncoded = customMimeEncoder.encodeToString(longString.getBytes(StandardCharsets.UTF_8));
        
        System.out.println("自定义MIME编码（行长度64）: ");
        System.out.println(customMimeEncoded);
        
        // 5. 带填充和不带填充的编码
        System.out.println("\n===== 带填充和不带填充的编码 =====");
        
        // 原始字符串
        String paddingTestString = "Test";
        
        // 带填充的编码（默认）
        String withPadding = encoder.encodeToString(paddingTestString.getBytes());
        System.out.println("带填充的编码: " + withPadding);
        
        // 不带填充的编码
        String withoutPadding = encoder.withoutPadding().encodeToString(paddingTestString.getBytes());
        System.out.println("不带填充的编码: " + withoutPadding);
        
        // 6. 实际应用示例
        System.out.println("\n===== 实际应用示例 =====");
        
        // 6.1 基本认证
        System.out.println("\n基本认证示例:");
        String username = "admin";
        String password = "password123";
        String credentials = username + ":" + password;
        
        String basicAuth = "Basic " + encoder.encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        System.out.println("基本认证头: " + basicAuth);
        
        // 解码并验证
        String encodedCredentials = basicAuth.substring("Basic ".length());
        String decodedCredentials = new String(decoder.decode(encodedCredentials), StandardCharsets.UTF_8);
        
        String[] parts = decodedCredentials.split(":", 2);
        System.out.println("解码后的用户名: " + parts[0]);
        System.out.println("解码后的密码: " + parts[1]);
        
        // 6.2 编码二进制数据
        System.out.println("\n编码二进制数据示例:");
        
        // 模拟二进制数据
        byte[] binaryData = new byte[20];
        for (int i = 0; i < binaryData.length; i++) {
            binaryData[i] = (byte) i;
        }
        
        // 编码二进制数据
        String encodedBinary = encoder.encodeToString(binaryData);
        System.out.println("编码后的二进制数据: " + encodedBinary);
        
        // 解码二进制数据
        byte[] decodedBinary = decoder.decode(encodedBinary);
        
        System.out.print("解码后的二进制数据: [");
        for (int i = 0; i < decodedBinary.length; i++) {
            System.out.print(decodedBinary[i]);
            if (i < decodedBinary.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        // 6.3 编码图像（模拟）
        System.out.println("\n编码图像示例（模拟）:");
        
        // 模拟图像数据
        byte[] imageData = new byte[100];
        for (int i = 0; i < imageData.length; i++) {
            imageData[i] = (byte) (Math.random() * 256);
        }
        
        // 编码图像数据
        String encodedImage = encoder.encodeToString(imageData);
        System.out.println("编码后的图像数据（前50个字符）: " + encodedImage.substring(0, Math.min(50, encodedImage.length())) + "...");
        
        // 在HTML中使用
        String htmlImgTag = "<img src=\"data:image/png;base64," + encodedImage + "\" alt=\"Base64编码的图像\">";
        System.out.println("HTML中使用Base64编码的图像（示例）: " + htmlImgTag.substring(0, Math.min(70, htmlImgTag.length())) + "...");
    }
}
