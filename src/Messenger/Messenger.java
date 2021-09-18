package Messenger;

import Commands.Settings.Executor;
import WorkWithConsole.ConsoleWorker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Messenger {

    private ConsoleWorker consoleWorker;
    private List<Socket> socketList = new ArrayList<>();


    public Messenger(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
        Connector connector = new Connector(socketList, consoleWorker);
        connector.createConnector();
        connector.startConnectingNewUsers();
    }

//    private void sendMessage(OutputStream outputStream, byte[] message) {
//
//    }
//
//    private byte[] receiveMessage(InputStream inputStream) {
//        return null;
//    }

    public void sendResponse(Response response) throws IOException {
        Socket socket = response.getSocket();
        response.setSocket(null);
        byte[] bytes = Serializer.serialize(response);
        socket.getOutputStream().write(bytes);
    }

    public List<Request> checkRequests(Executor executor) {
        Socket[] sockets = new Socket[socketList.size()];
        List<Request> requests = new ArrayList<Request>();
        socketList.toArray(sockets);
        for (Socket socket : sockets) {
            InputStream inputStream = null;
            int size = 0;
            try {
                inputStream = socket.getInputStream();
                size = inputStream.available();
                if (size > 0) {
                    byte[] bytes = new byte[size];
                    inputStream.read(bytes);
                    Request request = (Request) Serializer.deSerializer(bytes);
                    request.setSocket(socket);
                    requests.add(request);
                }
            } catch (Exception e) {
                e.printStackTrace();
                consoleWorker.write("Проблема с получением потока ввода у одного из пользователей!");
                break;
            }
        }
        return requests;
    }
}
