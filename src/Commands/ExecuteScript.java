package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class ExecuteScript extends AbstractCommand {

    private String parameter;

    public ExecuteScript(String parameter) {
        this.parameter = parameter;
    }

    public static CommandName getName(){
        return CommandName.EXECUTE_SCRIPT;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeExecuteScript(parameter);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
