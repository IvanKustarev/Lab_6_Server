package Commands;

import CitiesClasses.City;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Add extends AbstractCommand {

    private City city;

    public Add(City city) {
        this.city = city;
    }

    public static CommandName getName(){
        return CommandName.ADD;
    }

    public static boolean haveStringParameter(){
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeAdd(city);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}
