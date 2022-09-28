package rendyrobbani.java.belajar.crud.repository;

import rendyrobbani.java.belajar.crud.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, nama, tanggal_lahir FROM user";
        try (Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setNama(result.getString("nama"));
                user.setTanggalLahir(result.getDate("tanggal_lahir"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User findById(Long id) throws SQLException {
        String query = "SELECT id, nama, tanggal_lahir FROM user WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    User user = new User();
                    user.setId(result.getLong("id"));
                    user.setNama(result.getString("nama"));
                    user.setTanggalLahir(result.getDate("tanggal_lahir"));
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public void save(User user) throws SQLException {
        String query = "INSERT INTO user (nama, tanggal_lahir) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getNama());
            statement.setDate(2, user.getTanggalLahir());
            statement.executeUpdate();
            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) user.setId(result.getLong(1));
            }
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE user SET nama=?, tanggal_lahir=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getNama());
            statement.setDate(2, user.getTanggalLahir());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM user WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String query = "DELETE FROM user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    @Override
    public void resetAutoIncrement() throws SQLException {
        String query = "ALTER TABLE user AUTO_INCREMENT=0";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

}
