package DBWork;

import CitiesClasses.City;
import CitiesClasses.UserWithSalt;

import java.sql.SQLException;
import java.util.Collection;

public interface DBWorking {
    public void pushNewUser(UserWithSalt user) throws SQLException;
    public UserWithSalt getUser(String name) throws SQLException;
    public void pushNewCity(City city) throws SQLException;
    public void deleteCityById(int id) throws SQLException;
    public Collection<City> loadCollection() throws SQLException;
}

