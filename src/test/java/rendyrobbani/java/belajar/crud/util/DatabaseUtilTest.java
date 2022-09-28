package rendyrobbani.java.belajar.crud.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseUtilTest {

    @Test
    void getConnection() {
        Connection connection = DatabaseUtil.getConnection();
        assertNotNull(connection);
        assertSame(connection, DatabaseUtil.getConnection());
    }
}