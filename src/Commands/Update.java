package Commands;


import CitiesClasses.City;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Update extends AbstractCommand {

    private String parameter;
    private City city;

    public Update(String parameter, City city) {
        this.parameter = parameter;
        this.city = city;
    }

    public static CommandName getName(){
        return CommandName.UPDATE;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeUpdate(parameter, city);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
