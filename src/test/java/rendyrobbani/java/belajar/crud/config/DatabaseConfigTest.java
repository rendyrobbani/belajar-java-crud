package rendyrobbani.java.belajar.crud.config;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {

    @Test
    void getConfig() {
        Properties config = DatabaseConfig.getConfig();
        assertNotNull(config);
        assertEquals("org.mariadb.jdbc.Driver", config.get("DB_DRIVER"));
        assertEquals("jdbc:mariadb://localhost:3306/belajar_crud", config.get("DB_URL"));
        assertEquals("root", config.get("DB_USER"));
        assertEquals("", config.get("DB_PASS"));
    }
}