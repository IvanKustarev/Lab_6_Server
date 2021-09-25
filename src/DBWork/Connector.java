package DBWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private Connection connection;

    public Connector() throws SQLException{
        connect();
    }
    private Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s312671", "nbo470");
        try {
            connection.setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public Connection getConnection() {
        return connection;
    }
}
