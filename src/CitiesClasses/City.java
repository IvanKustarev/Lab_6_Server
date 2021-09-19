package CitiesClasses;

import java.io.Serializable;
import java.util.Date;

public class City implements Comparable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Значение поля должно быть больше 0, Поле не может быть null
    private int population; //Значение поля должно быть больше 0
    private Long metersAboveSeaLevel;
    private Date establishmentDate;
    private Climate climate; //Поле может быть null
    private Government government; //Поле может быть null
    private Human governor; //Поле может быть null


    public City(String name, Coordinates coordinates, Date creationDate, Integer area, int population, Long metersAboveSeaLevel, Date establishmentDate, Climate climate, Government government, Human governor) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.establishmentDate = establishmentDate;
        this.climate = climate;
        this.government = government;
        this.governor = governor;
    }

    public String show() {
        String str = "" + "\n";
        str += "Id города: " + id + "\n";
        str += "Название города: " + name + "\n";
        str += "X координата города: " + coordinates.getX() + "\n";
        str += "Y координата города: " + coordinates.getY() + "\n";
        str += "Дата создания элемента: " + creationDate + "\n";
        str += "Область: " + area + "\n";
        str += "Население: " + population + "\n";
        str += "Количество метров над уровнем моря: " + metersAboveSeaLevel + "\n";
        str += "Дата основания: " + establishmentDate + "\n";
        if (climate != null) {
            str += "Климат: " + climate.name() + "\n";
        } else {
            str += "Климат: " + "не задан" + "\n";
        }
        if (government != null) {
            str += "Власть: " + government.name() + "\n";
        } else {
            str += "Власть: " + "не задана" + "\n";
        }
        if (governor != null) {
            governor.show();
        } else {
            str += "Губернатор не задан\n";
        }
        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Long getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Long metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    @Override
    public int compareTo(Object o) {
        int score = 0;
        City enotherCity = (City) o;
        if (establishmentDate.getTime() < enotherCity.getEstablishmentDate().getTime()) {
            score++;
        } else if(establishmentDate.getTime() > enotherCity.getEstablishmentDate().getTime()){
            score--;
        }
        if (population > enotherCity.getPopulation()) {
            score++;
        } else if(population < enotherCity.getPopulation()){
            score--;
        }
        if (metersAboveSeaLevel > enotherCity.getMetersAboveSeaLevel()) {
            score++;
        } else if(metersAboveSeaLevel < enotherCity.getMetersAboveSeaLevel()){
            score--;
        }
        score += climate.compareTo(enotherCity.getClimate());
        score += government.compareTo(enotherCity.getGovernment());
        score += governor.compareTo(enotherCity.getGovernor());
        return score;
    }
}
