import java.awt.*;

public class T34 extends AbstractTank {

    public T34(ActionField actionField, BattleField battleField, int x, int y, Direction direction) {
        super(actionField, battleField, x, y, direction);
        speed = 10;
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
    }
}
