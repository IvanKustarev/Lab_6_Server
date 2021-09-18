package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class FilterLessThanGovernor extends AbstractCommand {

    private String parameter;

    public FilterLessThanGovernor(String parameter) {
        this.parameter = parameter;
    }

    public static CommandName getName(){
        return CommandName.FILTER_LESS_THAN_GOVERNOR;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeFilterLessThanGovernor(parameter);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
