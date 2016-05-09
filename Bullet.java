import Interfaces.Destroyable;
import Interfaces.Drowable;

import java.awt.*;

public class Bullet implements Drowable, Destroyable {
    private int speed = 5;
    private int x;
    private int y;
    private Direction direction;

    Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getBulletSpeed() {
        return speed;
    }

    public int getBulletX() {
        return x;
    }

    public int getBulletY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void destroy() {
        this.x = -100;
        this.y = -100;
    }

    public void updateX(int x) {
        this.x += x;

    }

    public void updateY(int y) {

        this.y += y;
    }

    @Override
    public void draw(Graphics g) {

    }
}

