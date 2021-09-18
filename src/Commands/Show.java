package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Show extends AbstractCommand {
    public static CommandName getName(){
        return CommandName.SHOW;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeShow();
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
