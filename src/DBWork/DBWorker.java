package DBWork;

import CitiesClasses.*;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Date;

public class DBWorker implements DBWorking {

    private Connection connection;
    private PasswordEncoder passwordEncoder;

    public DBWorker(Connection connection, PasswordEncoder passwordEncoder) {
        this.connection = connection;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void pushNewUser(User user) throws SQLException {
        String salt = passwordEncoder.getSalt();
        String sql = "INSERT INTO USERS " +
                "(NAME, PASSWORD, SALT) VALUES ('" + user.getName() + "', '" +
                passwordEncoder.encodePassword(user.getPassword(), salt) + "', '" + salt + "');";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.commit();
    }

    @Override
    public User getUser(String name) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS where NAME='" + name + "';");
        resultSet.close();
        statement.close();
        User user = new User(resultSet.getString("NAME"), resultSet.getString("PASSWORD"));
        return user;
    }

    @Override
    public void pushNewCity(City city) throws SQLException {
        String sql = "INSERT INTO CITIES " +
                "(id,cityName,coordinateX,coordinateY,creationDate,area,population,metersAboveSeaLevel,establishmentDate,climate,government,humanBirthday,humanName,ownerName) " +
                "VALUES ('" + String.valueOf(city.getId()) + "', '" + String.valueOf(city.getName()) + "', '" + String.valueOf(city.getCoordinates().getX()) + "', '" + String.valueOf(city.getCoordinates().getY()) + "', '" + String.valueOf(city.getCreationDate().getTime()) + "', '" +
                String.valueOf(city.getArea()) + "', '" + String.valueOf(city.getPopulation()) + "', '" + String.valueOf(city.getMetersAboveSeaLevel()) + "', '" + String.valueOf(city.getEstablishmentDate().getTime()) + "', '" + String.valueOf(city.getClimate().name()) + "', '" + String.valueOf(city.getGovernment().name()) + "', '" +
                String.valueOf(city.getGovernor().getName()) + "', '" + String.valueOf(city.getGovernor().getBirthday().getTime()) + String.valueOf(city.getOwnerName())  +"');";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.commit();
    }

    @Override
    public void deleteCityById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM CITIES where id=''" + id + "';";
        statement.executeUpdate(sql);
        statement.close();
    }

    @Override
    public ArrayDeque<City> loadCollection() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES");
        ArrayDeque<City> cities = new ArrayDeque<>();
        while (resultSet.next()) {
            int id = Integer.valueOf(resultSet.getString("id"));
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
        return cities;
    }
}
