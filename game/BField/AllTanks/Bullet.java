package game.BField.AllTanks;

import game.BField.Destroyable;
import game.BField.Drawable;
import game.Direction;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {

    private int speed = 5;
    private int x;
    private int y;

    private Direction direction;

    private boolean destroyed;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
    }

    public void updateX(int x) {
        this.x += x;

    }

    public void updateY(int y) {

        this.y += y;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(new Color(250, 250, 0));
            g.fillRect(this.x, this.y, 14, 14);
        }
    }

    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
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

//    public void setDirection(Direction direction) {
//        this.direction = direction;
//    }

}

