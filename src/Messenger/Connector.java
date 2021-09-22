package Messenger;

import WorkWithConsole.ConsoleWorker;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Connector {
    private ServerSocket serverSocket;
    private List<Socket> socketList;
    private ConsoleWorker consoleWorker;

    public Connector(List<Socket> socketList, ConsoleWorker consoleWorker) {
        this.socketList = socketList;
        this.consoleWorker = consoleWorker;
    }

    public void createConnector(){
        consoleWorker.write("Введите порт, который будет прослушивать сервер:");
        String portStr = consoleWorker.read();
        int port;
        try {
            port = Integer.valueOf(portStr);
        }catch (Exception e){
            consoleWorker.write("Порт должен быть целым числом!");
            createConnector();
            return;
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            consoleWorker.write("Порт уже занят, попробуйте другой!");
            createConnector();
            return;
        }
    }

    public void startConnectingNewUsers(){
        NewUsersConnector newUsersConnector = new NewUsersConnector();
        newUsersConnector.start();
    }

    private class NewUsersConnector extends Thread{
        @Override
        public void run() {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    socket = new Socket(socket.getInetAddress(), socket.getPort());
                } catch (IOException e) {
                    consoleWorker.write("Проблема с подключением нового пользователя!");
                    break;
                }
                socketList.add(socket);
            }
        }
    }
}
