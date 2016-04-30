import java.awt.*;

public class BT7 extends AbstractTank{
    public BT7(ActionField actionField, BattleField battleField, int x, int y, Direction direction) {
        super(actionField, battleField, x, y, direction);
        speed = 20;
        tankColor = new Color(100, 0, 150);
        towerColor = new Color(255, 0, 0);
    }
}
