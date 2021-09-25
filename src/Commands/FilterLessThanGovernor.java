package Commands;

import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class FilterLessThanGovernor extends AbstractCommand {

    private String parameter;
    private User user;

    public FilterLessThanGovernor(String parameter, User user) {
        this.parameter = parameter;
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.FILTER_LESS_THAN_GOVERNOR;
    }
    public static boolean haveStringParameter() {
        return true;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeFilterLessThanGovernor(parameter, user);
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
