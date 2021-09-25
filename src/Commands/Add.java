package Commands;

import CitiesClasses.City;
import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Add extends AbstractCommand {

    private City city;
    private User user;

    public Add(City city, User user) {
        this.city = city;
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.ADD;
    }

    public static boolean haveStringParameter(){
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeAdd(city, user);
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
