package rendyrobbani.java.belajar.crud.util;

import rendyrobbani.java.belajar.crud.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            Properties config = DatabaseConfig.getConfig();
            try {
                Class.forName(config.getProperty("DB_DRIVER"));
                connection = DriverManager.getConnection(config.getProperty("DB_URL"), config.getProperty("DB_USER"), config.getProperty("DB_PASS"));
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
