package Commands.Settings;

import CitiesClasses.City;
import CitiesClasses.User;
import Messenger.Response;

import java.util.ArrayDeque;

public interface Executor {
    public Response executeAdd(City city, User user);
    public Response executeClear(User user);
    public Response executeExecuteScript(String fileName, User user);
    public Response executeExit(User user);
    public Response executeFilterContainsName(String name, User user);
    public Response executeFilterLessThanGovernor(String governor, User user);
    public Response executeHead(User user);
    public Response executeHelp(User user);
    public Response executeInfo(User user);
    public Response executePrintFieldDescendingGovernment(User user);
    public Response executeRemoveById(String id, User user);
    public Response executeRemoveFirst(User user);
    public Response executeRemoveHead(User user);
    public Response executeShow(User user);
    public Response executeUpdate(String id, City city, User user);
    public ArrayDeque<City> getCollection();
    public Response executeLogin(User user);
    public Response executeRegister(User user);
}
