package DBWork;

import CitiesClasses.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBWorker implements DBWorking {

    private Connection connection;

    public DBWorker(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void pushNewUser(UserWithSalt user) throws SQLException {
        String sql = "INSERT INTO USERS " +
                "(NAME, PASSWORD, SALT) VALUES ('" + user.getName() + "', '" +
                user.getPassword() + "', '" + user.getSalt() + "');";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.commit();
    }

    @Override
    public UserWithSalt getUser(String name) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS where NAME='" + name + "';");
        resultSet.next();
        UserWithSalt user = new UserWithSalt(resultSet.getString("NAME"), resultSet.getString("PASSWORD"), resultSet.getString("SALT"));
        resultSet.close();
        statement.close();
        return user;
    }

    @Override
    public void pushNewCity(City city) throws SQLException {

        String climate = "";
        if(city.getClimate() != null){
            climate = city.getClimate().name();
        }
        String government = "";
        if(city.getGovernment() != null) {
            government = city.getGovernment().name();
        }
        String goverName = "";
        String goverBirthDay = "";
        if(city.getGovernor() != null){
            goverName = city.getGovernor().getName();
            goverBirthDay = String.valueOf(city.getGovernor().getBirthday().getTime());
        }

        String sql = "INSERT INTO CITIES " +
                "(cityname,coordinatex,coordinateY,creationDate,area,population,metersAboveSeaLevel,establishmentDate,climate,government,humanName,humanBirthday,ownerName) " +
                "VALUES ('" + String.valueOf(city.getName()) + "', '" + String.valueOf(city.getCoordinates().getX()) + "', '" + String.valueOf(city.getCoordinates().getY()) + "', '" + String.valueOf(city.getCreationDate().getTime()) + "', '" +
                String.valueOf(city.getArea()) + "', '" + String.valueOf(city.getPopulation()) + "', '" + String.valueOf(city.getMetersAboveSeaLevel()) + "', '" + String.valueOf(city.getEstablishmentDate().getTime()) + "', '" + climate + "', '" + government + "', '" +
                goverName + "', '" + goverBirthDay + "', '" + String.valueOf(city.getOwnerName())  +"');";


        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.commit();
    }

    @Override
    public void deleteCityById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM CITIES where id='" + id + "';";
        statement.executeUpdate(sql);
        statement.close();
        connection.commit();
    }

    @Override
    public Collection<City> loadCollection() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES");
        Collection<City> cities = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("cityName");
            Integer coordinateX = Integer.valueOf(resultSet.getString("coordinateX"));
            Float coordinateY = Float.valueOf(resultSet.getString("coordinateY"));
            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
            Date creationDate = new Date(Long.valueOf(resultSet.getString("creationDate")));
            Integer area = Integer.valueOf(resultSet.getString("area"));
            int population = Integer.valueOf(resultSet.getString("population"));
            Long metersAboveSeaLevel = Long.valueOf(resultSet.getString("metersAboveSeaLevel"));
            Date establishmentDate = new Date(Long.valueOf(resultSet.getString("establishmentDate")));
            Climate climate = null;
            try {
                climate = Climate.valueOf(resultSet.getString("climate"));
            } catch (Exception e) {
            }
            Government government = null;
            try {
                government = Government.valueOf(resultSet.getString("government"));
            } catch (Exception e) {
            }
            Human governor = null;
            try {
                governor = new Human(resultSet.getString("humanName"), new Date(Long.valueOf(resultSet.getString("humanBirthday"))));
            } catch (Exception e) {
            }
            String ownerName = resultSet.getString("ownerName");

            City city = new City(name, coordinates, creationDate, area, population, metersAboveSeaLevel, establishmentDate, climate, government, governor, ownerName);
            city.setId(id);
            cities.add(city);
        }
        resultSet.close();
        statement.close();

        cities = Collections.synchronizedCollection(cities);

        return cities;
    }

//    @Override
//    public void clearCollections() throws SQLException {
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("TRUNCATE TABLE cities");
//        statement.close();
//        connection.commit();
//    }
}
