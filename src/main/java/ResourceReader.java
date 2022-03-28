import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceReader {
    private static Properties properties = new Properties();
    public static void initialiseProperties() {
        try (InputStream input = Application.class.getClassLoader().getResourceAsStream("message.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
    public static String getProperty(String key, String ... args) {
        String str  = properties.getProperty(key);

        for(int i=0;i<args.length;i++) {
            String placeholder = "{"+ i + "}";
            if(str.contains(placeholder)){
                str = str.replace(placeholder, args[i]);
            }
        }
        return str;

    }


}
