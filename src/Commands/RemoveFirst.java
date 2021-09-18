package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class RemoveFirst extends AbstractCommand {
    public static CommandName getName(){
        return CommandName.REMOVE_FIRST;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeRemoveFirst();
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
