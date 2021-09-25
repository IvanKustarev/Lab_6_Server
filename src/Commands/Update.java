package Commands;


import CitiesClasses.City;
import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Update extends AbstractCommand {

    private String parameter;
    private City city;
    private User user;

    public Update(String parameter, City city, User user) {
        this.parameter = parameter;
        this.city = city;
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.UPDATE;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeUpdate(parameter, city, user);
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
