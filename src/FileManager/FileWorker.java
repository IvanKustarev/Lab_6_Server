package FileManager;

import CitiesClasses.City;

import java.util.ArrayDeque;

public class FileWorker implements FileManager{
    private String collectionFilePath;

    public FileWorker(String collectionFilePath) {
        this.collectionFilePath = collectionFilePath;
    }

    @Override
    public boolean saveCollection(ArrayDeque<City> cities) {
        return false;
    }

    @Override
    public ArrayDeque<City> loadCollection() {
        return null;
    }


}
