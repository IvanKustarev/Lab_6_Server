package Commands.Settings;

import CitiesClasses.City;
import Messenger.Response;

public interface Executor {
    public Response executeAdd(City city);
    public Response executeClear();
    public Response executeExecuteScript(String fileName);
    public Response executeExit();
    public Response executeFilterContainsName(String name);
    public Response executeFilterLessThanGovernor(String governor);
    public Response executeHead();
    public Response executeHelp();
    public Response executeInfo();
    public Response executePrintFieldDescendingGovernment();
    public Response executeRemoveById(String id);
    public Response executeRemoveFirst();
    public Response executeRemoveHead();
    public Response executeShow();
    public Response executeUpdate(String id, City city);
}
