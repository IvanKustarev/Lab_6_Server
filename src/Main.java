import CitiesClasses.City;
import Commands.Settings.CollectionWorker;
import Commands.Settings.Executor;
import DBWork.Connector;
import DBWork.DBWorker;
import DBWork.DBWorking;
import DBWork.PasswordEncoder;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ConsoleWorker consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
//        FileManager fileManager = new FileWorker(System.getenv("Lab6Path"));

        Connection connection = null;
        try {
            connection = new Connector().getConnection();
        } catch (SQLException throwables) {
            consoleWorker.write("Проблемы с подключением к БД!");
            return;
        }

        DBWorking dbWorking = new DBWorker(connection);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        Executor executor = null;
        try {
            executor = new CollectionWorker(dbWorking.loadCollection(), dbWorking, passwordEncoder);
        } catch (SQLException throwables) {
            consoleWorker.write("Проблемы с загрузкой коллекции из БД!");
            throwables.printStackTrace();
            return;
        }
        ExecuteManager executeManager = new ExecuteManager(messenger, executor, consoleWorker);
        executeManager.startExecuteUsersCommands();

        boolean exit = false;
        while (!exit) {
            consoleWorker.write("Для остановки сервера введите exit");
            String command = consoleWorker.read();
            command = command.trim();
            command = command.toLowerCase();
            if (command.equals("exit")) {
                consoleWorker.write("Завершаем работу сервера!");
                exit = true;

            } else {
                consoleWorker.write("Неизвестная команда");
            }
        }
        System.exit(0);
    }
}
