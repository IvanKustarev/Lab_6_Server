package CitiesClasses;

import java.util.Date;

public class Human {
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
}
