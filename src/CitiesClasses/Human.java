package CitiesClasses;

import java.io.Serializable;
import java.util.Date;

public class Human implements Serializable, Comparable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday;

    public Human(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String show() {
        String str = "";
        str += "Имя губернатора: " + getName() + "\n";
        str += "Дата рождения губернатора: " + getBirthday() + "\n";
        return str;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getBirthday().getTime() > ((Human) o).getBirthday().getTime()) {
            return 1;
        } else if (this.getBirthday().getTime() < ((Human) o).getBirthday().getTime()) {
            return -1;
        } else {
            return 0;
        }
    }
}
