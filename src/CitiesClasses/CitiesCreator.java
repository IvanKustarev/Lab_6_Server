package CitiesClasses;

import WorkWithConsole.ConsoleWorker;

import java.util.Date;

public class CitiesCreator {

    private static ConsoleWorker consoleWorker;

    public CitiesCreator(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
    }

    public static City createCity(ConsoleWorker consoleWorker){
        String name = createName();
        Coordinates coordinates = createCoordinates();
        Date creationDate = createCreationDate();
        Integer areas = createAreas();
        int population = createPopulation();
        Long metersAboveSeaLevel = createMetersAboveSeaLevel();
        Date establishmentDate = createEstablishmentDate();
        Climate climate = createClimate();
        Government government = createGovernment();
        Human governor = createGovernor();

        City city = new City(name, coordinates, creationDate, areas, population, metersAboveSeaLevel, establishmentDate, climate, government, governor);
        return city;
    }

    private static String createName(){
        consoleWorker.write("Введите название города:");
        String name = consoleWorker.read();
        if(name.trim().equals("")){
            consoleWorker.write("У города обязательно должно быть название");
            return createName();
        }
        return name.trim();
    }

    private static Coordinates createCoordinates(){
        int x;
        float y;
        consoleWorker.write("Введите координату города по X");
        try {
            x = Integer.valueOf(consoleWorker.read());
        }catch (Exception e){
            consoleWorker.write("Координата X должна быть целым числом");
            return createCoordinates();
        }
        consoleWorker.write("Введите координату города по Y");
        try {
            y = Float.valueOf(consoleWorker.read());
        }catch (Exception e){
            consoleWorker.write("Координата Y должна быть целым или дробным числом");
            return createCoordinates();
        }
        Coordinates coordinates = new Coordinates(x, y);
        return coordinates;
    }

    private static Date createCreationDate(){
        return new Date();
    }

    private static Integer createAreas(){
        consoleWorker.write("Задайте область целым числом");
        int area;
        try {
            area = Integer.valueOf(consoleWorker.read());
        }catch (Exception e){
            consoleWorker.write("Область должна быть задана целым числом!");
            return createAreas();
        }
        return area;
    }

    private static int createPopulation(){
        consoleWorker.write("Задайте население целым числом");
        int population;
        try {
            population = Integer.valueOf(consoleWorker.read());
        }catch (Exception e){
            consoleWorker.write("Население должно быть задано целым числом!");
            return createPopulation();
        }
        return population;
    }

    private static Long createMetersAboveSeaLevel(){
        consoleWorker.write("Задайте количество метров над уровнем моря целым числом");
        long metersAboveSeaLevel;
        try {
            metersAboveSeaLevel = Long.valueOf(consoleWorker.read());
        }catch (Exception e){
            consoleWorker.write("Количество метров над уровнем моря должно быть задано целым числом!");
            return createMetersAboveSeaLevel();
        }
        return metersAboveSeaLevel;
    }

    private static Date createEstablishmentDate(){
        consoleWorker.write("Задайте дату основания города в формате yyyy:mm:dd");
        String birthdate = consoleWorker.read();
        try {
            String[] params = birthdate.trim().split(":");
            return new Date(Integer.valueOf(params[0]) - 1900, Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        }catch (Exception e){
            consoleWorker.write("Дата основания задана некорректно!");
            return createEstablishmentDate();
        }
    }

    private static Climate createClimate(){
        consoleWorker.write("Задайте климат одной из следующих констант:");
        for (Climate climate : Climate.values()){
            consoleWorker.write(climate.name());
        }
        Climate climate;
        String str = consoleWorker.read();
        if(str.trim().equals("")){
            consoleWorker.write("Значение поля остаётся пустым!");
            return null;
        }
        try {
            climate = Climate.valueOf(str);
        }catch (Exception e){
            consoleWorker.write("Неизвестное значение!");
            return createClimate();
        }
        return climate;
    }

    private static Government createGovernment(){
        consoleWorker.write("Задайте власть одной из следующих констант:");
        for (Government government : Government.values()){
            consoleWorker.write(government.name());
        }
        Government government;
        String str = consoleWorker.read();
        if(str.trim().equals("")){
            consoleWorker.write("Значение поля остаётся пустым!");
            return null;
        }
        try {
            government = Government.valueOf(str);
        }catch (Exception e){
            consoleWorker.write("Неизвестное значение!");
            return createGovernment();
        }
        return government;
    }

    private static Human createGovernor(){
        consoleWorker.write("Задайте имя губернаотра");
        String name = consoleWorker.read();
        if(name.trim().equals("")){
            consoleWorker.write("У города нет губернатора!");
            return null;
        }
        consoleWorker.write("Задайте дату рождения в формате yyyy:mm:dd");
        String birthdate = consoleWorker.read();
        if(birthdate.trim().equals("")){
            consoleWorker.write("У города нет губернатора!");
            return null;
        }
        Date date;
        try {
            String[] params = birthdate.trim().split(":");
            date = new Date(Integer.valueOf(params[0]) - 1900, Integer.valueOf(params[2]), Integer.valueOf(params[2]));
        }catch (Exception e){
            consoleWorker.write("Не корректно задана дата рождения!");
            return createGovernor();
        }
        return new Human(name, date);
    }
}
