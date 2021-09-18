import Commands.Settings.Executor;
import Messenger.Messenger;
import Messenger.Request;
import Messenger.Response;
import WorkWithConsole.ConsoleWorker;

import java.io.IOException;
import java.util.List;

public class ExecuteManager {
    private Messenger messenger;
    private Executor executor;
    private ConsoleWorker consoleWorker;

    public ExecuteManager(Messenger messenger, Executor executor, ConsoleWorker consoleWorker) {
        this.messenger = messenger;
        this.executor = executor;
        this.consoleWorker = consoleWorker;
    }

    public void startExecuteUsersCommands(){
        while (true) {
            List<Request> requests = messenger.checkRequests(executor);
            for(Request request : requests){
                if(request.isCommandRequest()){
                    Response response = request.getCommand().execute(executor);
                    response.setSocket(request.getSocket());
                    try {
                        messenger.sendResponse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                        consoleWorker.write("Проблема с отправкой ответа клиенту!");
                        break;
                    }
                }
            }
        }
    }
}
