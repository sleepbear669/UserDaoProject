package gom.cave.sleep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sleepbear on 2016. 4. 8..
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection) throws SQLException;

}
