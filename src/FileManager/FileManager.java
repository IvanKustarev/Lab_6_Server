package FileManager;

import CitiesClasses.City;

import java.util.ArrayDeque;

public interface FileManager {
    public boolean saveCollection(ArrayDeque<City> cities);
    public ArrayDeque<City> loadCollection();
}
