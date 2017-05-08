package game;


import game.BField.AllTanks.AbstractTank;
import game.BField.BattleField;

import javax.swing.*;
import java.awt.*;

public class StartMenu {
    BattleField battleField;
    ActionField actionField;
    private AbstractTank defender;

    private JFrame frame;

    public StartMenu() {
        frame = new JFrame();
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setLocation(400, 100);

        frame.getContentPane().add(createPanel());
        frame.pack();
        frame.setVisible(true);
    }


    public Component createPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JButton t34 = new JButton("T34", new ImageIcon("T34.png"));
        panel.add(t34);

        JButton tiger = new JButton("Tiger", new ImageIcon("Tiger.png"));
        panel.add(tiger);

        JButton bt7 = new JButton("BT7", new ImageIcon("BT74.png"));
        panel.add(bt7);

        return panel;
    }
}