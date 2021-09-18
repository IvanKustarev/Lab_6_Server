import Commands.Settings.CollectionWorker;
import Commands.Settings.Executor;
import FileManager.FileManager;
import FileManager.FileWorker;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;

public class Main {

    public static void main(String[] args) {
        ConsoleWorker consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
        FileManager fileManager = new FileWorker("");
        Executor executor = new CollectionWorker(fileManager.loadCollection());
        ExecuteManager executeManager = new ExecuteManager(messenger, executor, consoleWorker);
        executeManager.startExecuteUsersCommands();
    }

}
