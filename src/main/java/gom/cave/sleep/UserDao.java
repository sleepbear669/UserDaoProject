package gom.cave.sleep;

import java.sql.SQLException;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public class UserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(long id) throws ClassNotFoundException, SQLException {

        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.JdbcContextWithStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatementStrategy(user);

        return jdbcContext.JdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void delete(Long id) {
        final String sql = "DELETE FROM user WHERE id = ?";
        final Object[] params = {id};
        jdbcContext.update(sql, params);

    }

    public void update(User user) {
        final String sql = "UPDATE user SET name = ? , password = ? where id = ?";
        final Object[] params = {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

}
