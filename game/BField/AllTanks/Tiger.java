package game.BField.AllTanks;

import game.BField.BattleField;
import game.Direction;

import java.awt.*;

/**
 * Created by User on 06.08.2016.
 */
public class Tiger extends AbstractTank {

    public Tiger(BattleField battleField) {
        super(battleField, 128, 128, Direction.UP);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
    }

    public Tiger(BattleField battleField, int x, int y, Direction direction) {
        super(battleField, x, y, direction);
        tankColor = new Color(250, 0, 0);
        towerColor = new Color(0, 255, 0);
    }
}
