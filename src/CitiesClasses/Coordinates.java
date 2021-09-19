package CitiesClasses;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x; //Максимальное значение поля: 96
    private float y;

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
