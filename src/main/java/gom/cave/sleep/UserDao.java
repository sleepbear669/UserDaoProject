package gom.cave.sleep;

import java.sql.*;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public class UserDao {
    public User get(long id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/UserDatabase", "root", "gom0119!1");
        final String sql = "select * from user where id = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        final User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }
}
