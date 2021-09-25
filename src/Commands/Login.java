package Commands;

import CitiesClasses.User;
import Commands.Settings.Command;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

import java.io.Serializable;

public class Login implements Command, Serializable {
    private User user;

    public Login(User user) {
        this.user = user;
    }

    public static CommandName getName() {
        return CommandName.LOGIN;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeLogin(user);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
    @Override
    public User getUser() {
        return user;
    }
}
