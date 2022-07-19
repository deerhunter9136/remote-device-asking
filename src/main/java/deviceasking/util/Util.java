package deviceasking.util;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Utility class
 */
public class Util {

    public static byte[] convert2Bytes(String param) {
        try {
            String[] split = param.split("b");
            int length = split.length;
            byte[] bytes = new byte[length];
            for (int i = 0; i < length; i++) {
                bytes[i] = Byte.parseByte(split[i]);
            }
            return bytes;
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public static String convertFromBytes(byte[] bytes) {
        if (bytes == null) return "";
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b).append("b");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static byte[] getBytesFromUrl(URL url) throws IOException {
        byte[] bytes;
        try (InputStream input = url.openStream()) {
            bytes = input.readAllBytes();
        }
        return bytes;
    }

    public static Map<String, String> getPropFromYaml(String path) throws IOException {
        Yaml yaml = new Yaml();
        Map<String, String> prop;
        try (InputStream inputStream = Files.newInputStream(Path.of(path))) {
            prop = yaml.load(inputStream);
        }
        return prop;
    }
}
