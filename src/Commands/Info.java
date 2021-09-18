package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Info extends AbstractCommand {
    public static CommandName getName(){
        return CommandName.INFO;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeInfo();
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
