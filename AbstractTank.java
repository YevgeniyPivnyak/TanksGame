import java.awt.*;

public abstract class AbstractTank {

    protected int speed;
    protected int x;
    protected int y;
    protected Direction direction;
    protected Color tankColor;
    protected Color towerColor;

    private ActionField actionField;
    private BattleField battleField;

    public AbstractTank(ActionField af, BattleField bf) {
        this(af, bf, 64, 64, Direction.RIGHT);
    }

    public AbstractTank(ActionField actionField, BattleField battleField, int x, int y, Direction direction) {
        this.actionField = actionField;
        this.battleField = battleField;
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public void move() throws Exception {
        actionField.ProcessMove(this);
    }

    public void turn(Direction direction) throws Exception {
        this.direction = direction;
        actionField.ProcessTurn(this);
    }

    public void fire() throws Exception {
        Bullet bullet = new Bullet((x + 25), (y + 25), direction);
        actionField.ProcessFire(bullet);
    }

    public void destroy() {
        this.x = -1000;
        this.y = -1000;
    }

    public void moveToQadrant(int v, int h) throws Exception {
        String coordinates = actionField.getQuadrant(v, h);
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

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
