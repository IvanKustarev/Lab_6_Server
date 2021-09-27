package Commands.Settings;

import CitiesClasses.*;
import DBWork.DBWorking;
import DBWork.PasswordEncoder;
import FileManager.FileManager;
import Messenger.Messenger;
import Messenger.Response;
import FileManager.FileWorker;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionWorker implements Executor {

    private Collection<City> cities;
    private Date dateOfInitialization = new Date();
    private DBWorking dbWorking;
    private PasswordEncoder passwordEncoder;

    public CollectionWorker(Collection<City> cities, DBWorking dbWorking, PasswordEncoder passwordEncoder) {
        this.cities = cities;
        this.dbWorking = dbWorking;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response executeLogin(User user) {
        UserWithSalt userWithSalt = null;
        try {
            userWithSalt = dbWorking.getUser(user.getName());
        } catch (SQLException throwables) {
            return new Response("Пользователь с таким именем не зарегестрирован!", false);
        }
        String password = passwordEncoder.encodePassword(user.getPassword(), userWithSalt.getSalt());
        if (password.equals(userWithSalt.getPassword())) {
            return new Response("Добрый день " + user.getName() + "!", true);
        } else {
            return new Response("Неверный пароль!", false);
        }
    }

    @Override
    public Response executeRegister(User user) {
        UserWithSalt userWithSalt = null;
        try {
            userWithSalt = dbWorking.getUser(user.getName());
            return new Response("Данный никнейм уже занят!", false);
        } catch (SQLException throwables) {
        }
        String salt = passwordEncoder.getSalt();
        userWithSalt = new UserWithSalt(user.getName(), passwordEncoder.encodePassword(user.getPassword(), salt), salt);
        try {
            dbWorking.pushNewUser(userWithSalt);
            return new Response("Пользователь успешно зарегестрирован!", true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Response("Проблема при загрузке нового пользователя в БД!", false);
        }
    }

    @Override
    public Response executeAdd(City city, User user) {
        try {
            if (city.getId() == 0) {
                Random random = new Random();
                boolean err = true;
                int newId = 0;
                while (err) {
                    err = false;
                    newId = random.nextInt();
                    if (newId <= 0) {
                        err = true;
                        continue;
                    }
                    for (City c : getAllCities()) {
                        if (c.getId() == newId) {
                            err = true;
                        }
                    }
                }
                city.setId(newId);
            }
            addCity(city);
            dbWorking.pushNewCity(city);
            return new Response("Объект успешно добавлен в коллекцию", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Проблема при добавлении объекта", true);
        }
    }

    @Override
    public Response executeClear(User user) {
        List<City> problemCities = new ArrayList<>();
        for (City city : getModifiedCities(user)) {
            Response response = executeRemoveById(String.valueOf(city.getId()), user);
            if (!response.isSuccess()) {
                System.out.println(response.getMessage());
                problemCities.add(city);
            }
        }
        String message = "";
        for (City city : problemCities) {
            message += "Проблема с удалением города (id:" + city.getId() + ")\n";
        }
        if (message.equals("")) {
            return new Response("Все элементы принадлежащие вам были удалены!", true);
        } else {
            return new Response("Возникли некоторые ошибки: " + message, false);
        }
    }

    @Override
    public Response executeExecuteScript(String fileName, User user) {
        FileManager fileManager = new FileWorker("");
        String str;
        try {
            str = fileManager.readFile(fileName);
            return new Response("true;" + str, true);
        } catch (Exception e) {
            return new Response("false;Невозможно прочесть данный файл!", false);
        }
    }

    @Override
    public Response executeExit(User user) {
        return new Response("Заканчиваем работу прогрммы", true);
    }

    @Override
    public Response executeFilterContainsName(String name, User user) {
        String result = "";

        City[] cities = Arrays.stream(getAllCities()).filter(City -> City.getName().contains(name)).toArray(City[]::new);
        for (City city : cities) {
            result += city.show();
        }
        return new Response(result, true);
    }

    @Override
    public Response executeFilterLessThanGovernor(String governorStr, User user) {
        City[] cities = getAllCities();
        if (cities.length == 0) {
            return new Response("Коллкция пустая!", true);
        }
        Date date;
        try {
            date = new Date(Integer.valueOf(governorStr.split(":")[0]) - 1900, Integer.valueOf(governorStr.split(":")[1]), Integer.valueOf(governorStr.split(":")[2]));
        } catch (Exception e) {
            return new Response("Для работы команды необходимо ввести дату рождения губернатора в формате yyyy:mm:dd", false);
        }

        String str = "";
        for (City city : getAllCities()) {
            if (city.getGovernor().compareTo(new Human("", date)) < 0) {
                str += city.getGovernor().show() + "\n";
            }
        }
        return new Response(str, true);
    }

    @Override
    public Response executeHead(User user) {
        return new Response(getAllCities()[0].show(), true);
    }

    @Override
    public Response executeHelp(User user) {
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
        return new Response(answer, true);
    }

    @Override
    public Response executeInfo(User user) {
        String answer = "Тип коллекции: ArrayDeque<City>" + "\n" + "Дата инициализации: " + dateOfInitialization + "\n" +
                "Количество элементов: " + getAllCities().length;
        return new Response(answer, true);
    }

    @Override
    public Response executePrintFieldDescendingGovernment(User user) {
        City[] cities = getAllCities();
        if (cities.length == 0) {
            return new Response("Коллекция пустая!", true);
        }
        String str = "";
        for (int i = cities.length - 1; i >= 0; i++) {
            str += cities[i].getGovernment().name() + "\n";
        }
        return new Response(str, true);
    }

    @Override
    public Response executeRemoveById(String idStr, User user) {
        int id = 0;
        try {
            id = Integer.valueOf(idStr);
        } catch (Exception e) {
            return new Response("id должен быть числом!", false);
        }
        for (City city : getModifiedCities(user)) {
            if (String.valueOf(city.getId()).equals(idStr)) {
                try {
                    dbWorking.deleteCityById(id);
                    removeCity(city);
                    return new Response("Элемент успешно удалён", true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return new Response("Проблема с БД!", false);
                }
            }
        }
        return new Response("Элемента с таким id доступным для данного пользователя не существует!", false);
    }

    @Override
    public Response executeRemoveFirst(User user) {
        City[] cities = getAllCities();
        if (cities.length == 0) {
            return new Response("Коллекция пустая!", true);
        } else if (!cities[0].getOwnerName().equals(user.getName())) {
            return new Response("Первый элемент принадлежит пользователю \"" + cities[0].getOwnerName() + "\" и не может быть удалён", true);
        } else {
            int id = cities[0].getId();
            Response response = executeRemoveById(String.valueOf(id), user);
            if (!response.isSuccess()) {
                return new Response(response.getMessage(), false);
            } else {
                return new Response("Первый элемент успешно удалён!", true);
            }
        }
    }

    @Override
    public Response executeRemoveHead(User user) {
        City[] cities = getAllCities();
        if (cities.length == 0) {
            return new Response("Коллекция пустая!", true);
        } else if (cities[0].getOwnerName().equals(user.getName())) {
            return new Response(cities[0].show() + "\n" + "Первый элемент принадлежит пользователю \"" + cities[0].getOwnerName() + "\" и не может быть удалён", true);
        } else {
            Response response = executeRemoveById(String.valueOf(cities[0].getId()), user);
            if (!response.isSuccess()) {
                return new Response(response.getMessage(), false);
            } else {
                String str = cities[0].show() + "\n" + "Элемент успешно удалён!";
                return new Response(str, true);
            }
        }
    }

    @Override
    public Response executeShow(User user) {
        String showString = "";
        City[] cities = getAllCities();
        for (Object object : cities) {
            City city = (City) object;
            showString += city.show();
        }
        if (showString.equals("")) {
            return new Response("Коллекция пустая!", true);
        }

        return new Response(showString, true);

    }

    @Override
    public Response executeUpdate(String idStr, City city, User user) {
        int id = 0;
        try {
            id = Integer.valueOf(idStr);
        } catch (Exception e) {
            return new Response("id должен быть числом!", false);
        }
        try {
            Response response = executeRemoveById(idStr, user);
            Response response1 = new Response("", false);
            if (response.isSuccess()) {
                response1 = executeAdd(city, user);
                if (response1.isSuccess()) {
                    return new Response("Оюъект успешно обновлён", true);
                }
            }
            return new Response("Проблемы с обновлением объекта: " + "\n" + response.getMessage() + "\n" + response1.getMessage(), false);
        } catch (Exception e) {
            return new Response("Проблема с обновлением объекта", false);
        }
    }

//    @Override
//    public ArrayDeque<City> getCollection() {
//        ArrayDeque<City> arrayDeque = new ArrayDeque<>(cities.stream().sorted().collect(Collectors.toList()));
//        return arrayDeque;
//    }

    private City[] getModifiedCities(User user) {
        Collection<City> cities = null;
        try {
            cities = dbWorking.loadCollection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        synchronized (this){
            this.cities = cities;
        }
        return cities.stream().sorted().filter(city -> city.getOwnerName().equals(user.getName())).toArray(City[]::new);
    }

    private City[] getAllCities() {
        Collection<City> cities = null;
        try {
            cities = dbWorking.loadCollection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        synchronized (this){
            this.cities = cities;
        }
        return cities.stream().sorted().toArray(City[]::new);
    }

    private void addCity(City city){
        synchronized (this){
            cities.add(city);
        }
    }

    private void removeCity(City city){
        synchronized (this){
            cities.remove(city);
        }
    }

}
