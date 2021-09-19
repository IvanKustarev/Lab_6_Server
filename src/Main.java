import CitiesClasses.City;
import Commands.Settings.CollectionWorker;
import Commands.Settings.Executor;
import FileManager.FileManager;
import FileManager.FileWorker;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleWorker consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
        FileManager fileManager = new FileWorker(System.getenv("Lab6Path"));
        Executor executor = null;
        try {
            executor = new CollectionWorker( fileManager.loadCollection());
        } catch (FileNotFoundException e) {
            consoleWorker.write("Файл с указанным именем не найден!");
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ExecuteManager executeManager = new ExecuteManager(messenger, executor, consoleWorker);
        executeManager.startExecuteUsersCommands();

        while (true){
            consoleWorker.write("Для сохранения коллекции и остановки сервера введите exit");
            String command = consoleWorker.read();
            command = command.trim();
            command =command.toLowerCase();
            if(command.equals("exit")){
                try {
                    fileManager.saveCollection(executor.getCollection());
                    consoleWorker.write("Коллекция успешно сохранена\nЗавершаем работу сервера!");
                    System.exit(0);
                } catch (IOException e) {
                    consoleWorker.write("Проблемы с сохранением коллекции!");
                }
            }else {
                consoleWorker.write("Неизвестная команда");
            }
        }
    }

}
