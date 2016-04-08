package gom.cave.sleep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sleepbear on 2016. 4. 8..
 */
public class AddStatementStrategy implements StatementStrategy {
    private User user;

    public AddStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        final String sql = "INSERT INTO user (name, password) VALUES (?, ? ) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }
}
