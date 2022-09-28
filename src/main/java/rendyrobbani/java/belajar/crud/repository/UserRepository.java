package rendyrobbani.java.belajar.crud.repository;

import rendyrobbani.java.belajar.crud.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    List<User> findAll() throws SQLException;

    User findById(Long id) throws SQLException;

    void save(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(Long id) throws SQLException;

    void deleteAll() throws SQLException;

    void resetAutoIncrement() throws SQLException;
}
