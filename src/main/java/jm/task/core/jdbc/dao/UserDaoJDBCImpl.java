package jm.task.core.jdbc.dao;

import com.sun.source.tree.StatementTree;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private final Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS test.users " +
                    "(id int not null auto_increment, name VARCHAR(30), " +
                    "lastname VARCHAR(30), age numeric, PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS test.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection
                .prepareStatement("INSERT INTO test.users(name, lastname, age) VALUES(?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM test.users WHERE id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT id, name, lastName, age FROM test.users");

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("TRUNCATE test.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
