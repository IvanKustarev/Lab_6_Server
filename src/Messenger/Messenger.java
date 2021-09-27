package Messenger;

import Commands.Settings.Executor;
import WorkWithConsole.ConsoleWorker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Messenger {

    private ConsoleWorker consoleWorker;
    private List<Socket> socketList = new ArrayList<>();


    public Messenger(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
        Connector connector = new Connector(socketList, consoleWorker);
        connector.createConnector();
        connector.startConnectingNewUsers();
    }

//    public void sendResponse(Response response) throws IOException {
//        Socket socket = response.getSocket();
//        response.setSocket(null);
//        byte[] bytes = Serializer.serialize(response);
//        socket.getOutputStream().write(bytes);
//    }

    public class RequestChecker extends RecursiveTask<List<Request>> {

        @Override
        protected List<Request> compute() {
            Socket[] sockets = new Socket[socketList.size()];
            List<Request> requests = new ArrayList<Request>();
            List<RequestGetter> requestGetters = new ArrayList<>();
            socketList.toArray(sockets);
            for (Socket socket : sockets) {
                RequestGetter requestGetter = new RequestGetter(socket);
                requestGetter.fork();
                requestGetters.add(requestGetter);
            }
            for (RequestGetter requestGetter : requestGetters) {
                Request request = requestGetter.join();
                if (request == null) {
                    continue;
                } else {
                    requests.add(request);
                }
            }
            return requests;
        }

        public class RequestGetter extends RecursiveTask<Request> {

            private Socket socket;

            public RequestGetter(Socket socket) {
                this.socket = socket;
            }

            @Override
            protected Request compute() {
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
                        return request;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    consoleWorker.write("Проблема с получением потока ввода у одного из пользователей!");
                    return null;
                }
                return null;
            }
        }
    }

    public class ResponseSender extends RecursiveAction {

        private List<Response> responses;

        public ResponseSender(List<Response> responses) {
            this.responses = responses;
        }

        @Override
        protected void compute() {
            for(Response response : responses) {
                new Sender(response).fork();
            }
        }

        private class Sender extends RecursiveAction{

            private Response response;

            public Sender(Response response) {
                this.response = response;
            }

            @Override
            protected void compute() {
                try {
                    Socket socket = response.getSocket();
                    response.setSocket(null);
                    byte[] bytes = Serializer.serialize(response);
                    socket.getOutputStream().write(bytes);
                } catch (IOException e) {
                    consoleWorker.write("Проблема с отправкой ответа клиенту!");
                }
            }
        }

    }
}
