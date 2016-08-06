package game.BField.AllTanks;

import game.BField.BattleField;
import game.Direction;

import java.awt.*;

public abstract class AbstractTank implements Tank{

    private int speed = 10;

    private Direction direction;

    private int x;
    private int y;

    private boolean destroyed;

    private BattleField battleField;

    protected Color tankColor;
    protected Color towerColor;

//    private ActionField actionField;


    public AbstractTank(BattleField battleField) {
        this(battleField, 64, 64, Direction.RIGHT);
    }

    public AbstractTank(BattleField battleField, int x, int y, Direction direction) {

        this.battleField = battleField;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

//    public void move() throws Exception {
//        actionField.ProcessMove(this);
//    }

    public void move() {

    }

    public void turn(Direction direction) {
        this.direction = direction;
    }

    public Bullet fire() {
        int bulletX = -100;
        int bulletY = -100;
        if (direction == Direction.UP) {
            bulletX = x + 25;
            bulletY = y - 64;

        } else if (direction == Direction.DOWN) {
            bulletX = x + 25;
            bulletY = y + 64;

        } else if (direction == Direction.LEFT) {
            bulletX = x - 64;
            bulletY = y + 25;
        } else if (direction == Direction.RIGHT) {
            bulletX = x + 64;
            bulletY = y + 25;
        }
        return new Bullet(bulletX, bulletY, direction);
        //        Bullet bullet = new Bullet((x + 25), (y + 25), direction);
//        actionField.ProcessFire(bullet);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }


    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(tankColor);
            g.fillRect(this.getX(), this.getY(), 64, 64);

            g.setColor(towerColor);
            if (this.getDirection() == Direction.UP) {
                g.fillRect(this.getX() + 20, this.getY(), 24, 34);
            } else if (this.getDirection() == Direction.DOWN) {
                g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
            } else if (this.getDirection() == Direction.LEFT) {
                g.fillRect(this.getX(), this.getY() + 20, 34, 24);
            } else {
                g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
            }
        }
    }

    public void moveToQadrant(int v, int h) throws Exception {
//        String coordinates = actionField.getQuadrant(v, h);
//        int y = Integer.parseInt(coordinates.split("_")[0]);
//        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (this.x < x) {
            while (this.x != x) {
                turn(Direction.UP);
                fire();
                move();
            }
        } else {
            while (this.x != x) {
                turn(Direction.DOWN);
                fire();
                move();
            }
        }

        if (this.y < y) {
            while (this.y != y) {
                turn(Direction.LEFT);
                fire();
                move();
            }
        } else {
            while (this.y != y) {
                turn(Direction.RIGHT);
                fire();
                move();
            }
        }
    }

    public void updateX(int x) {

        this.x += x;
    }

    public void updateY(int y) {

        this.y += y;
    }
}
