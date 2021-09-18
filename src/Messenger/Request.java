package Messenger;

import Commands.Settings.Command;

import java.io.Serializable;
import java.net.Socket;

public class Request implements Serializable {
    private Command command;
    private boolean isCommandRequest;
    private Socket socket;

    public Request(Command command, boolean isCommandRequest) {
        this.command = command;
        this.isCommandRequest = isCommandRequest;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Command getCommand() {
        return command;
    }

    public boolean isCommandRequest() {
        return isCommandRequest;
    }
}
