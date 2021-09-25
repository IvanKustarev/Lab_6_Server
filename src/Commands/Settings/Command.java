package Commands.Settings;

import CitiesClasses.User;
import Messenger.Response;

public interface Command{
    public Response execute(Executor executor);
    public CommandName getCommandName();
    public User getUser();
}
