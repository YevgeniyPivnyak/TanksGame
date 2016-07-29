package game;

import javax.swing.*;
import java.awt.*;

public class ActionField extends JPanel {

    final boolean COLORDED_MODE = false;

    private BattleField battleField;
    private T34 defender;
    private Bullet bullet;
    private BT7 agressor;

    void runTheGame() throws Exception {

        defender.turn(Direction.UP);
        defender.fire();
        defender.fire();
        defender.move();
        defender.fire();
        defender.turn(Direction.DOWN);
        defender.fire();
        defender.turn(Direction.LEFT);
        defender.fire();
        defender.turn(Direction.RIGHT);
        defender.fire();
        defender.fire();
        defender.move();
        defender.turn(Direction.UP);
        defender.fire();
        defender.move();
        defender.fire();
    }

    boolean processInterception() {

        String coordinates = getQuadrant(bullet.getBulletX(), bullet.getBulletY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if ((x >= 0 && x < 9) && (y >= 0 && y < 9)) {
            if (battleField.ScanQadrant(x, y) != " ") {
                battleField.UpdateQadrant(y, x, " ");
                return true;
            }
        }
        if (getQuadrant(agressor.getX(), agressor.getY()).equals(coordinates)) {
            agressor.destroy();
            bullet.destroy();
//            returnNewTank();
        }

        return false;
    }

    public String getQuadrant(int x, int y) {
        return y / 64 + "_" + x / 64;
    }

    public String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    void ProcessTurn(AbstractTank tank) throws Exception {
        repaint();
    }

    void ProcessFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        int step = 1;
        while ((bullet.getBulletX() > -14 && bullet.getBulletX() < 590)
                && (bullet.getBulletY() > -14 && bullet.getBulletY() < 590)) {
            if (bullet.getDirection() == Direction.UP) {
                bullet.updateY(-step);
            } else if (bullet.getDirection() == Direction.DOWN) {
                bullet.updateY(+step);
            } else if (bullet.getDirection() == Direction.LEFT) {
                bullet.updateX(-step);
            } else {
                bullet.updateX(+step);
            }
            if (processInterception() == true) {
                bullet.destroy();
                break;
            }
            repaint();
            Thread.sleep(bullet.getBulletSpeed());
        }
    }

    void ProcessMove(AbstractTank tank) throws Exception {

        int step = 1;
        int covered = 0;

        // check limits x: 0, 513; y: 0, 513
        if ((tank.getDirection() == Direction.UP && tank.getY() == 0)
                || (tank.getDirection() == Direction.DOWN && tank.getY() >= 512)
                || (tank.getDirection() == Direction.LEFT && tank.getX() == 0)
                || (tank.getDirection() == Direction.RIGHT && tank.getX() >= 512)) {
            System.out.println("[illegal move] direction: " + tank.getDirection()
                    + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            return;
        }

        tank.turn(tank.getDirection());

        while (covered < 64) {
            if (tank.getDirection() == Direction.UP) {
                tank.updateY(-step);
                System.out.println("[move up] direction: " + tank.getDirection()
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else if (tank.getDirection() == Direction.DOWN) {
                tank.updateY(+step);
                System.out.println("[move down] direction: " + tank.getDirection()
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else if (tank.getDirection() == Direction.LEFT) {
                tank.updateX(-step);
                System.out.println("[move left] direction: " + tank.getDirection()
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else {
                tank.updateX(+step);
                System.out.println("[move right] direction: " + tank.getDirection()
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            }
            covered += step;

            repaint();
            Thread.sleep(tank.getSpeed());
        }
    }

    public ActionField() throws Exception {

        battleField = new BattleField();
        defender = new T34(this, battleField);

        String location = battleField.getAgressorLocation();
        agressor = new BT7(this, battleField,
                Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT);

        bullet = new Bullet(-100, -100, Direction.NULL);

        JFrame frame = new JFrame("BATTLE FIELD TANK GAME");
        frame.setLocation(500, 70);
        frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 18, battleField.getBF_WIDTH() + 45));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int j = 0; j < battleField.getDimentionY(); j++) {
            for (int k = 0; k < battleField.getDimentionX(); k++) {
//                battleField.ScanQadrant(j, k).draw(g);
                if (battleField.ScanQadrant(j, k).equals("B")) {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates
                            .substring(0, separator));
                    int x = Integer.parseInt(coordinates
                            .substring(separator + 1));
                    g.setColor(new Color(0, 0, 99));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }
//        tankOOP.draw(g);
//        agressor.draw(g);
//        bullet.draw(g);

        g.setColor(new Color(255, 0, 0));
        g.fillRect(defender.getX(), defender.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (defender.getDirection() == Direction.UP) {
            g.fillRect(defender.getX() + 20, defender.getY(), 24, 34);
        } else if (defender.getDirection() == Direction.DOWN) {
            g.fillRect(defender.getX() + 20, defender.getY() + 30, 24, 34);
        } else if (defender.getDirection() == Direction.LEFT) {
            g.fillRect(defender.getX(), defender.getY() + 20, 34, 24);
        } else {
            g.fillRect(defender.getX() + 30, defender.getY() + 20, 34, 24);
        }

        //AGRESSOR
        g.setColor(new Color(255, 0, 255));
        g.fillRect(agressor.getX(), agressor.getY(), 0, 0);

        g.setColor(new Color(0, 255, 0));
        if (agressor.getDirection() == Direction.UP) {
            g.fillRect(agressor.getX() + 20, agressor.getY(), 24, 34);
        } else if (agressor.getDirection() == Direction.DOWN) {
            g.fillRect(agressor.getX() + 20, agressor.getY() + 30, 24, 34);
        } else if (agressor.getDirection() == Direction.LEFT) {
            g.fillRect(agressor.getX(), agressor.getY() + 20, 34, 24);
        } else {
            g.fillRect(agressor.getX() + 30, agressor.getY() + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bullet.getBulletX(), bullet.getBulletY(), 14, 14);

    }
}
