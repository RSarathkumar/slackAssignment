package api.support;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    Properties property;

    public Properties returnPropertyValue() {
        property = new Properties();
        try {
            FileReader fileReader = new FileReader("src/main/resources/api.properties");
            property.load(fileReader);
            return property;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getBaseUrl() {
        property = new Properties();
        return returnPropertyValue().getProperty("baseURL");
    }

    public String getContentType() {
        return returnPropertyValue().getProperty("content-type");
    }

    public String getAuthToken() {
        return returnPropertyValue().getProperty("token");
    }
}
