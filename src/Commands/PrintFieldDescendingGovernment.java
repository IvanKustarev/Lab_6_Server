package Commands;

import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class PrintFieldDescendingGovernment extends AbstractCommand {

    private User user;

    public PrintFieldDescendingGovernment(User user) {
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.PRINT_FIELD_DESCENDING_GOVERNMENT;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executePrintFieldDescendingGovernment(user);
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
