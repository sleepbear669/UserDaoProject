package gom.cave.sleep;

import java.sql.*;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public abstract class UserDao {

    public User get(long id) throws ClassNotFoundException, SQLException {

        final Connection connection = getConnection();

        final String sql = "select * from user where id = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        final User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        final Connection connection = getConnection();

        final String sql = "INSERT INTO user (name, password) VALUES (?, ? ) ";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        final long id = getLastId(connection);

        preparedStatement.close();
        connection.close();

        return id;
    }

    private long getLastId(Connection connection) throws SQLException {
        final PreparedStatement preparedStatement2 = connection.prepareStatement("select last_insert_id()");
        final ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();
        final long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();
        return id;
    }

    abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost/UserDatabase", "root", "gom0119!1");
//    }

}
