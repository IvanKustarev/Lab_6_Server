package Messenger;

import java.io.Serializable;
import java.net.Socket;

public class Response implements Serializable {
    private String message;
    private Socket socket;

    public Response(String message) {
        this.message = message;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getMessage() {
        return message;
    }
}
