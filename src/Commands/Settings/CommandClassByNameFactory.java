package Commands.Settings;

import Commands.*;

public class CommandClassByNameFactory {

    public static boolean haveCommandParameter(CommandName commandName){
        if (Add.getName().equals(commandName)) {
            return Add.haveStringParameter();
        } else if (Clear.getName().equals(commandName)) {
            return Clear.haveStringParameter();
        } else if (ExecuteScript.getName().equals(commandName)) {
            return ExecuteScript.haveStringParameter();
        } else if (Exit.getName().equals(commandName)) {
            return Exit.haveStringParameter();
        } else if (FilterContainsName.getName().equals(commandName)) {
            return FilterContainsName.haveStringParameter();
        } else if (FilterLessThanGovernor.getName().equals(commandName)) {
            return FilterLessThanGovernor.haveStringParameter();
        } else if (Head.getName().equals(commandName)) {
            return Head.haveStringParameter();
        } else if (Help.getName().equals(commandName)) {
            return Help.haveStringParameter();
        } else if (Info.getName().equals(commandName)) {
            return Info.haveStringParameter();
        } else if(PrintFieldDescendingGovernment.getName().equals(commandName)){
            return PrintFieldDescendingGovernment.haveStringParameter();
        } else if(RemoveById.getName().equals(commandName)){
            return RemoveById.haveStringParameter();
        } else if(RemoveFirst.getName().equals(commandName)){
            return RemoveFirst.haveStringParameter();
        } else if(RemoveHead.getName().equals(commandName)){
            return RemoveHead.haveStringParameter();
        } else if(Show.getName().equals(commandName)){
            return Show.haveStringParameter();
        } else if(Update.getName().equals(commandName)){
            return Update.haveStringParameter();
        } else {
            return false;
        }



//        Method method = null;
//        Boolean haveStringParameter = null;
//        try {
//            method = getCommandClassByName(commandName).getMethod("haveStringParameter");
//            haveStringParameter = (Boolean) method.invoke(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return haveStringParameter;

    }

//    public static Class getCommandClassByName(String commandName) {
//        if (Add.getName().equals(commandName)) {
//            return Add.class;
//        } else if (Clear.getName().equals(commandName)) {
//            return Clear.class;
//        } else if (ExecuteScript.getName().equals(commandName)) {
//            return ExecuteScript.class;
//        } else if (Exit.getName().equals(commandName)) {
//            return Exit.class;
//        } else if (FilterContainsName.getName().equals(commandName)) {
//            return FilterContainsName.class;
//        } else if (FilterLessThanGovernor.getName().equals(commandName)) {
//            return FilterLessThanGovernor.class;
//        } else if (Head.getName().equals(commandName)) {
//            return Head.class;
//        } else if (Help.getName().equals(commandName)) {
//            return Help.class;
//        } else if (Info.getName().equals(commandName)) {
//            return Info.class;
//        } else if(PrintFieldDescendingGovernment.getName().equals(commandName)){
//            return PrintFieldDescendingGovernment.class;
//        } else if(RemoveById.getName().equals(commandName)){
//            return RemoveById.class;
//        } else if(RemoveFirst.getName().equals(commandName)){
//            return RemoveFirst.class;
//        } else if(RemoveHead.getName().equals(commandName)){
//            return RemoveHead.class;
//        } else if(Show.getName().equals(commandName)){
//            return Show.class;
//        } else if(Update.getName().equals(commandName)){
//            return Update.class;
//        } else {
//            return null;
//        }
//    }
}
