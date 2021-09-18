package Commands.Settings;

import Messenger.Response;

public interface Command{
    public Response execute(Executor executor);
    public CommandName getCommandName();
}
