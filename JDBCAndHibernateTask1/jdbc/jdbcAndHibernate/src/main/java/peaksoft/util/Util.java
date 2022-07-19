package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public Connection connection() throws SQLException {
        // реализуйте настройку соеденения с БД
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","5547");
    }
}
