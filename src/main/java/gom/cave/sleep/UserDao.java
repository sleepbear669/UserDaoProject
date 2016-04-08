package gom.cave.sleep;

import java.sql.*;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public class UserDao {

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(long id) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionMaker.getConnection();

            final String sql = "select * from user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        long id = 0;
        try {
            connection = connectionMaker.getConnection();

            final String sql = "INSERT INTO user (name, password) VALUES (?, ? ) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            id = getLastId(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



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

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();

            final String sql = "DELETE FROM user WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();

            final String sql = "UPDATE user SET name = ? , password = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
