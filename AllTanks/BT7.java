package AllTanks;

import java.awt.*;

public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        this(af, bf, 64, 64, Direction.UP);
    }

    public BT7(ActionField actionField, BattleField battleField, int x, int y, Direction direction) {
        super(actionField, battleField, x, y, direction);
        speed = 20;
        tankColor = new Color(255, 0, 255);
        towerColor = new Color(255, 0, 0);
    }

    @Override
    public void draw(Graphics g) {

    }
}
