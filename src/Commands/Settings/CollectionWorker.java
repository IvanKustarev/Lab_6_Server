package Commands.Settings;

import CitiesClasses.City;
import Messenger.Messenger;
import Messenger.Response;

import java.util.*;

public class CollectionWorker implements Executor {

    private ArrayDeque<City> cities;
    private Date dateOfInitialization = new Date();

    public CollectionWorker(ArrayDeque<City> cities) {
        this.cities = cities;
    }

    @Override
    public Response executeAdd(City city) {
        try {
            if (city.getId() == 0) {
                Random random = new Random();
                boolean err = true;
                int newId = 0;
                while (err) {
                    err = false;
                    newId = random.nextInt();
                    for (Object c : cities) {
                        if (((City) c).getId() == newId) {
                            err = true;
                        }
                    }
                }
                city.setId(newId);
            }
            cities.add(city);
            return new Response("Объект успешно добавлен в коллекцию");
        } catch (Exception e) {
            return new Response("Проблема при добавлении объекта");
        }
    }

    @Override
    public Response executeClear() {
        cities.clear();
        return new Response("Коллекция была очищена");
    }

    @Override
    public Response executeExecuteScript(String fileName) {
        return null;
    }

    @Override
    public Response executeExit() {
        return new Response("Заканчиваем работу прогрммы");
    }

    @Override
    public Response executeFilterContainsName(String name) {
        return null;
    }

    @Override
    public Response executeFilterLessThanGovernor(String governor) {
        return null;
    }

    @Override
    public Response executeHead() {
        return null;
    }

    @Override
    public Response executeHelp() {
        String answer = "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "head : вывести первый элемент коллекции\n" +
                "remove_head : вывести первый элемент коллекции и удалить его\n" +
                "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                "filter_less_than_governor governor : вывести элементы, значение поля governor которых меньше заданного\n" +
                "print_field_descending_government : вывести значения поля government всех элементов в порядке убывания"
        return new Response(answer);
    }

    @Override
    public Response executeInfo() {
        String answer = "Тип коллекции: ArrayDeque<City>" + "\n" + "Дата инициализации: " + dateOfInitialization + "\n" +
                "Количество элементов: " + cities.size();
        return new Response(answer);
    }

    @Override
    public Response executePrintFieldDescendingGovernment() {
        return null;
    }

    @Override
    public Response executeRemoveById(String id) {
        return null;
    }

    @Override
    public Response executeRemoveFirst() {
        return null;
    }

    @Override
    public Response executeRemoveHead() {

        return null;
    }

    @Override
    public Response executeShow() {
        String showString = "";
        if (showString.equals("")) {
            return new Response("Коллекция пустая!");
        }
        for (Object object : cities) {
            City city = (City) object;
            showString += city.show();
        }

        return new Response(showString);

    }

    @Override
    public Response executeUpdate(String idStr, City city) {
        int id = 0;
        try {
            id = Integer.valueOf(idStr);
        } catch (Exception e) {
            return new Response("id должен быть числом!");
        }
        try {
            executeRemoveById(idStr);
            executeAdd(city);
        } catch (Exception e) {
            return new Response("Проблема с обновлением объекта");
        }
        return new Response("Оюъект успешно обновлён");
    }

    private List<City> sortCollection(){
        List<City> cities = new ArrayList<>();
        cities.addAll(this.cities);
        Collections.sort(cities);
        return cities;
    }
}
