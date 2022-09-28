package rendyrobbani.java.belajar.crud.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static Properties config = null;

    public static Properties getConfig() {
        if (config == null) {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            try (InputStream stream = loader.getResourceAsStream("config/database.properties")) {
                config = new Properties();
                config.load(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return config;
    }
}
