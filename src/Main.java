import CitiesClasses.City;
import Commands.Settings.CollectionWorker;
import Commands.Settings.Executor;
import DBWork.Connector;
import DBWork.DBWorker;
import DBWork.DBWorking;
import DBWork.PasswordEncoder;
import FileManager.FileManager;
import FileManager.FileWorker;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

        DBWorking dbWorking = new DBWorker(connection, new PasswordEncoder());
        Executor executor = null;
        try {
            executor = new CollectionWorker(/*fileManager.loadCollection()*/ dbWorking.loadCollection(), dbWorking);
        } catch (SQLException throwables) {
            consoleWorker.write("Проблемы с загрузкой коллекции из БД!");
            return;
        }
        ExecuteManager executeManager = new ExecuteManager(messenger, executor, consoleWorker);
        executeManager.startExecuteUsersCommands();

        while (true) {
            consoleWorker.write("Для сохранения коллекции введите save / Для сохранения коллекции и остановки сервера введите exit");
            String command = consoleWorker.read();
            command = command.trim();
            command = command.toLowerCase();
            if (command.equals("exit")) {
                try {
                    fileManager.saveCollection(executor.getCollection());
                    consoleWorker.write("Коллекция успешно сохранена\nЗавершаем работу сервера!");
                    System.exit(0);
                } catch (IOException e) {
                    consoleWorker.write("Проблемы с сохранением коллекции!");
                }
            } else if (command.equals("save")) {
                try {
                    fileManager.saveCollection(executor.getCollection());
                    consoleWorker.write("Коллекция успешно сохранена!");
                } catch (IOException e) {
                    consoleWorker.write("Проблемы с сохранением коллекции!");
                }
            } else {
                consoleWorker.write("Неизвестная команда");
            }
        }
    }

}
