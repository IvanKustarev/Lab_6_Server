package Commands.Settings;

import Messenger.Request;
import Messenger.Response;

import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecuteThread extends Thread{

    private Request request;
    private Executor executor;
    private Socket socket;
    private Response response;

    public ExecuteThread(Request request, Executor executor) {
        this.request = request;
        this.executor = executor;
        this.socket = request.getSocket();
    }

    public void executeCommandInThread(){
        start();
    }

    @Override
    public void run() {
        Response response = request.getCommand().execute(executor);
        response.setSocket(socket);
        synchronized (this) {
            this.notifyAll();
        }
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
