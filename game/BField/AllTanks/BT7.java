package game.BField.AllTanks;

import game.BField.BattleField;
import game.Direction;

import java.awt.*;

public class BT7 extends AbstractTank {

    public BT7( BattleField battleField) {
        super(battleField);
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
    }

    public BT7(BattleField battleField, int x, int y, Direction direction) {
        super(battleField, x, y, direction);
//        super.speed = 20;
        tankColor = new Color(255, 0, 255);
        towerColor = new Color(255, 0, 0);
    }
}
