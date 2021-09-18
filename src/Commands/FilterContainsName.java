package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class FilterContainsName extends AbstractCommand {

    private String parameter;

    public FilterContainsName(String parameter) {
        this.parameter = parameter;
    }

    public static CommandName getName(){
        return CommandName.FILTER_CONTAINS_NAME;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeFilterContainsName(parameter);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
