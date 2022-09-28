package rendyrobbani.java.belajar.crud.repository;

import org.junit.jupiter.api.*;
import rendyrobbani.java.belajar.crud.entity.User;
import rendyrobbani.java.belajar.crud.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private static Connection connection;

    private static UserRepository repository;

    @BeforeAll
    static void beforeAll() {
        connection = DatabaseUtil.getConnection();
        repository = new UserRepositoryImpl(connection);
    }

    @AfterAll
    static void afterAll() throws SQLException {
        repository = null;
        connection.close();
    }

    @Test
    @Order(1)
    void testDeleteAll() {
        assertDoesNotThrow(() -> repository.deleteAll());
    }

    @Test
    @Order(2)
    void testResetIncrement() {
        assertDoesNotThrow(() -> repository.resetAutoIncrement());
    }

    @Test
    @Order(3)
    void testSave() throws SQLException {
        User user = new User();
        user.setNama("Rendy");
        user.setTanggalLahir(Date.valueOf("1995-10-17"));
        repository.save(user);
        assertNotNull(user.getId());
        assertEquals(1, user.getId());
    }

    @Test
    @Order(4)
    void testFindById() throws SQLException {
        User user = repository.findById(1L);
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Rendy", user.getNama());
        assertEquals(Date.valueOf("1995-10-17"), user.getTanggalLahir());
    }

    @Test
    @Order(5)
    void testUpdate() throws SQLException {
        User before = repository.findById(1L);
        before.setNama("Rendy Robbani");
        repository.update(before);
        User after = repository.findById(1L);
        assertEquals(before.getId(), after.getId());
        assertEquals(before.getNama(), after.getNama());
        assertEquals(before.getTanggalLahir(), after.getTanggalLahir());
    }

    @Test
    @Order(6)
    void testFindAll() throws SQLException {
        List<User> users = repository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    @Order(7)
    void testDelete() throws SQLException {
        repository.delete(1L);
        User user = repository.findById(1L);
        assertNull(user);
    }

}