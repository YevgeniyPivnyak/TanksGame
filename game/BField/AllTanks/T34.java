package game.BField.AllTanks;

import game.BField.BattleField;
import game.Direction;

import java.awt.*;

public class T34 extends AbstractTank {

    public T34(BattleField battleField) {
        super(battleField, 128, 512, Direction.UP);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
    }

    public T34(BattleField battleField, int x, int y, Direction direction) {
        super(battleField, x, y, direction);
        tankColor = new Color(250, 0, 0);
        towerColor = new Color(0, 255, 0);
    }
}
