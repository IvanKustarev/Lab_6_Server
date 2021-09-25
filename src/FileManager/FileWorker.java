package FileManager;

import CitiesClasses.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class FileWorker implements FileManager {
    private String collectionFilePath;

    public FileWorker(String collectionFilePath) {
        this.collectionFilePath = collectionFilePath;
    }

    @Override
    public boolean saveCollection(ArrayDeque<City> cities) throws IOException {
        String json = parsToJSON(cities);
        saveFile(collectionFilePath, json);
        return true;
    }

    @Override
    public ArrayDeque<City> loadCollection() throws FileNotFoundException, ParseException {
        String jsonStr = readFile(collectionFilePath);
        ArrayDeque<City> cities = parsToCollection(jsonStr);
        return cities;
    }

    @Override
    public String readFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine() + "\n";
        }
        return result;
    }

    public void saveFile(String path, String fileString) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(path));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write(fileString);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    private ArrayDeque<City> parsToCollection(String json) throws ParseException {
        JSONObject jsonObject = null;
        if(json.equals("")){
            return new ArrayDeque<>();
        }
        jsonObject = (JSONObject) new JSONParser().parse(json);
        ArrayDeque<City> cities = new ArrayDeque<>();
        for (int i = 0; i < jsonObject.size(); i++) {
            JSONObject element = (JSONObject) jsonObject.get(i+"");
            String name = (String) element.get("name");
            int coordinateX = Integer.valueOf(String.valueOf(element.get("coordinateX")));
            float coordinateY = Float.valueOf(String.valueOf(element.get("coordinateY")));
            Date creationDate = new Date(Long.valueOf((Long) element.get("creationDate")));
            Integer area = Integer.valueOf(String.valueOf(element.get("area")));
            int population = Integer.valueOf(String.valueOf(element.get("population")));
            Long metersAboveSeaLevel = Long.valueOf(String.valueOf(element.get("metersAboveSeaLevel")));
            java.util.Date establishmentDate = new Date(Long.valueOf(String.valueOf(element.get("establishmentDate"))));
            Climate climate = null;
            try {
                climate = Climate.valueOf(String.valueOf(element.get("climate")));
            } catch (Exception e) {
            }
            Government government = null;
            try {
                government = Government.valueOf(String.valueOf(element.get("government")));
            } catch (Exception e) {
            }
            Human governor = null;
            try {
                governor = new Human(String.valueOf(element.get("humanName")), Date.valueOf(String.valueOf(element.get("humanBirthday"))));
            } catch (Exception e) {
            }
            City city = new City(name, new Coordinates(coordinateX, coordinateY), creationDate, area, population, metersAboveSeaLevel, establishmentDate, climate, government, governor, null);
            city.setId(Integer.valueOf(String.valueOf(element.get("id"))));
            cities.add(city);
        }
        return cities;
    }

    private String parsToJSON(ArrayDeque<City> cities) {
        Object[] objects = cities.toArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < objects.length; i++) {
            City city = (City) objects[i];
            JSONObject cityJson = new JSONObject();
            cityJson.put("id", city.getId());
            cityJson.put("name", city.getName());
            cityJson.put("coordinateX", city.getCoordinates().getX());
            cityJson.put("coordinateY", city.getCoordinates().getY());
            cityJson.put("creationDate", city.getCreationDate().getTime());
            cityJson.put("area", city.getArea());
            cityJson.put("population", city.getPopulation());
            cityJson.put("metersAboveSeaLevel", city.getMetersAboveSeaLevel());
            cityJson.put("establishmentDate", city.getEstablishmentDate().getTime());
            if (city.getClimate() != null) {
                cityJson.put("climate", city.getClimate().name());
            } else {
                cityJson.put("climate", "");
            }
            if (city.getGovernment() != null) {
                cityJson.put("government", city.getGovernment().name());
            } else {
                cityJson.put("government", "");
            }
            if (city.getGovernor() != null) {
                cityJson.put("humanName", city.getGovernor().getName());
                cityJson.put("humanBirthday", city.getGovernor().getBirthday());
            } else {
                cityJson.put("humanName", "");
                cityJson.put("humanBirthday", "");
            }

            jsonObject.put(i, cityJson);
        }
        return jsonObject.toJSONString();
    }

}
