package tests.utils;

import java.io.FileInputStream;
import java.io.InputStream;

public class Utils {

    public static String readProperty(String key) {
        String value = "";
        try (InputStream input = new FileInputStream("./src/test/resources/data/configuration.properties")) {
            // load a properties file
            PropertiesConfiguration.initiateProperties().load(input);
            // get the property value and print it out
            value = PropertiesConfiguration.initiateProperties().getProperty(key);

        } catch (Exception ignored) {

        }
        return value;
    }
}
