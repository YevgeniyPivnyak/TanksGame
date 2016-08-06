package game.BField;

import java.awt.Graphics;
import java.util.Random;

public class BattleField implements Drawable {

    public static final String BRICK = "B";
    public static final String EAGLE = "E";
    public static final String ROCK = "R";
    public static final String WATER = "W";

    public int BF_WIDTH = 576;
    public int BF_HEIGHT = 576;

    String[][] battleFieldTemplate = {
            {"B ", " ", "B", "B", "W", "B", "B", " ", " "},
            {"B", " ", " ", " ", " ", " ", " ", " ", "B"},
            {"W", "W", "W", " ", "R", " ", "W", "W", "W"},
            {"B", " ", "B", " ", "R", " ", "B", " ", "B"},
            {"B", "B", "B", " ", "R", " ", "B", "B", "B"},
            {" ", "B", " ", "R", "R", "R", " ", "B", " "},
            {" ", "B", " ", " ", " ", " ", " ", "B", " "},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"},
            {"B", " ", " ", "B", "E", "B", " ", " ", "B"}
    };

    private BFObject[][] battleField = new BFObject[9][9];

    public BattleField() {
        drawBattleField();
    }

    public BattleField(String[][] bfObjects) {
        this.battleFieldTemplate = bfObjects;
        drawBattleField();
    }

    private String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    private void drawBattleField() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String coordinates = getQuadrantXY(i + 1, j + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates
                        .substring(0, separator));
                int x = Integer.parseInt(coordinates
                        .substring(separator + 1));

                String obj = battleFieldTemplate[i][j];
                BFObject bfObject;
                if (obj.equals(BRICK)) {
                    bfObject = new Brick(x, y);
                } else if (obj.equals(ROCK)) {
                    bfObject = new Rock(x, y);
                } else if (obj.equals(EAGLE)) {
                    bfObject = new Eagle(x, y);
                } else if (obj.equals(WATER)) {
                    bfObject = new Water(x, y);
                } else {
                    bfObject = new Blank(x, y);
                }
                battleField[i][j] = bfObject;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < battleField.length; j++) {
            for (int k = 0; k < battleField.length; k++) {
                battleField[j][k].draw(g);
            }
        }
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public void destroyObject(int v, int h) {
        battleField[v][h].destroy();
    }

    public BFObject ScanQadrant(int v, int h) {
        return battleField[v][h];
    }

//    public int getDimentionX() {
//        return battleField[0].length;
//    }
//
//    public int getDimentionY() {
//        return battleField.length;
//    }

//    public void UpdateQadrant(int x, int y, String updateValue) {
//        this.battleField[x][y] = updateValue;
//        if (x >= 0 && x < battleField[0].length && y >= 0 && y < battleField.length) {
//            battleField[x][y] = updateValue;
//        }
//    }

    public String getAgressorLocation() {
        int locationX = 0;
        Random r = new Random();
        int index = r.nextInt(2);

        if (index == 0) {
            locationX = 64;
        } else if (index == 1) {
            locationX = 5 * 64;
        } else {
            locationX = 7 * 64;
        }

        return locationX + "_0";
    }

}
