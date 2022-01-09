package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServices {
    public static Connection makeConnection(String driver, String username, String password) throws SQLException {
        Connection connection = null;
        return DriverManager.getConnection(driver, username, password);
    }
}
