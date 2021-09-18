package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class RemoveById extends AbstractCommand {

    private String parameter;

    public RemoveById(String parameter) {
        this.parameter = parameter;
    }

    public static CommandName getName(){
        return CommandName.REMOVE_BY_ID;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeRemoveById(parameter);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
