package Commands.Settings;

import CitiesClasses.City;
import CitiesClasses.Government;
import CitiesClasses.Human;
import FileManager.FileManager;
import Messenger.Messenger;
import Messenger.Response;
import FileManager.FileWorker;

import java.util.*;

public class CollectionWorker implements Executor {

    private ArrayDeque<City> cities;
    private Date dateOfInitialization = new Date();

    public CollectionWorker(ArrayDeque<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return sortCollection();
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
        FileManager fileManager = new FileWorker("");
        String str;
        try {
            str = fileManager.readFile(fileName);
            return new Response("true;"+str);
        }catch (Exception e){
            return new Response("false;Невозможно прочесть данный файл!");
        }
    }

    @Override
    public Response executeExit() {
        return new Response("Заканчиваем работу прогрммы");
    }

    @Override
    public Response executeFilterContainsName(String name) {
        String result = "";
        for (City city : getCitiesArray()){
            if(city.getName().contains(name)){
                result = city.show();
            }
        }
        return new Response(result);
    }

    @Override
    public Response executeFilterLessThanGovernor(String governorStr) {
        City[] cities = getCitiesArray();
        if(cities.length == 0){
            return new Response("Коллкция пустая!");
        }
        Date date;
        try {
            date = new Date(Integer.valueOf(governorStr.split(":")[0]) - 1900, Integer.valueOf(governorStr.split(":")[1]), Integer.valueOf(governorStr.split(":")[2]));
        }catch (Exception e){
            return new Response("Для работы команды необходимо ввести дату рождения губернатора в формате yyyy:mm:dd");
        }

        String str = "";
        for(City city : cities){
            if(city.getGovernor().compareTo(new Human("", date)) < 0){
                str += city.getGovernor().show() + "\n";
            }
        }
        return new Response(str);
    }

    @Override
    public Response executeHead() {
        return new Response(getCitiesArray()[0].show());
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
                "print_field_descending_government : вывести значения поля government всех элементов в порядке убывания";
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
        City[] cities = getCitiesArray();
        if(cities.length == 0){
            return new Response("Коллекция пустая!");
        }
        String str = "";
        for(int i = cities.length-1; i >=0; i++){
            str += cities[i].getGovernment().name() + "\n";
        }
        return new Response(str);
    }

    @Override
    public Response executeRemoveById(String idStr) {
        int id = 0;
        try {
            id = Integer.valueOf(idStr);
        } catch (Exception e) {
            return new Response("id должен быть числом!");
        }
        Iterator iterator = cities.iterator();
        while (iterator.hasNext()){
            City city = (City) iterator.next();
            if(city.getId() == id){
                iterator.remove();
                return new Response("Элемент успешно удалён");
            }
        }
        return new Response("Элемента с таким id не существует!");
    }

    @Override
    public Response executeRemoveFirst() {
        City[] cities = getCitiesArray();
        if(cities.length == 0){
            return new Response("Коллекция пустая!");
        }
        int id = cities[0].getId();
        executeRemoveById(String.valueOf(id));
        return new Response("Первый элемент успешно удалён!");
    }

    @Override
    public Response executeRemoveHead() {
        City[] cities = getCitiesArray();
        if(cities.length == 0){
            return new Response("Коллекция пустая!");
        }
        String str = cities[0].show();
        executeRemoveById(String.valueOf(cities[0].getId()));
        return new Response(str);
    }

    @Override
    public Response executeShow() {
        String showString = "";
        List<City> cities = sortCollection();
        for (Object object : cities) {
            City city = (City) object;
            showString += city.show();
        }
        if (showString.equals("")) {
            return new Response("Коллекция пустая!");
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

    @Override
    public ArrayDeque<City> getCollection() {
        return cities;
    }

    private List<City> sortCollection(){
        List<City> cities = new ArrayList<>();
        cities.addAll(this.cities);
        Collections.sort(cities);
        return cities;
    }

    private City[] getCitiesArray(){
        City[] cities = new City[getCollection().size()];
        cities = sortCollection().toArray(cities);
        return cities;
    }
}
