package Commands;

import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class ExecuteScript extends AbstractCommand {

    private String parameter;
    private User user;

    public ExecuteScript(String parameter, User user) {
        this.parameter = parameter;
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    public static CommandName getName(){
        return CommandName.EXECUTE_SCRIPT;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeExecuteScript(parameter, user);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }

    public String getParameter() {
        return parameter;
    }
}
