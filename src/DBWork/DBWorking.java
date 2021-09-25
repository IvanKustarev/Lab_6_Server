package DBWork;

import CitiesClasses.City;
import CitiesClasses.User;

import java.sql.SQLException;
import java.util.ArrayDeque;

public interface DBWorking {
    public void pushNewUser(User user) throws SQLException;
    public User getUser(String name) throws SQLException;
    public void pushNewCity(City city) throws SQLException;
    public void deleteCityById(int id) throws SQLException;
    public ArrayDeque<City> loadCollection() throws SQLException;
}

