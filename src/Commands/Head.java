package Commands;

import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Head extends AbstractCommand {

    private User user;

    public Head(User user) {
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.HEAD;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeHead(user);
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
