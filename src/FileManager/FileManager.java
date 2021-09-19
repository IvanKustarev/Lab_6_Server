package FileManager;

import CitiesClasses.City;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;

public interface FileManager {
    public boolean saveCollection(ArrayDeque<City> cities) throws IOException;
    public ArrayDeque<City> loadCollection() throws FileNotFoundException, ParseException;
    public String readFile(String path) throws FileNotFoundException;
}
