package DBWork;

import FileManager.FileManager;
import FileManager.FileWorker;
import WorkWithConsole.ConsoleWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Connector {
    private Connection connection;
    private FileManager fileManager;
    private ConsoleWorker consoleWorker;

    public Connector(ConsoleWorker consoleWorker) throws SQLException{
        fileManager = new FileWorker();
        this.consoleWorker = consoleWorker;
        connect();
    }
    private Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("password.txt"));
        } catch (FileNotFoundException e) {
            consoleWorker.write("Нет файла с поролём для подключения к БД!");
        }
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s312671", scanner.nextLine());
        try {
            connection.setAutoCommit(false);
        }catch (Exception e){
            consoleWorker.write("Нет файла с паролём для подключения к БД!");
        }
        return connection;
    }
    public Connection getConnection() {
        return connection;
    }
}
