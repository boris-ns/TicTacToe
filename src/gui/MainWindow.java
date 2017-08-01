package gui;

import gameLogic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by boris on 8/1/17.
 */
public class MainWindow extends JFrame {

    private static Game gameInstance = null;

    private JLabel lblWhoPlays, lblTime, lblScore;
    private JButton[] btnFields;

    private char playerMark = 'X';


    public MainWindow() {
        gameInstance = new Game();
        gameInstance.setViewComponent(this);

        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setIconImage(); TODO
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        Dimension dimension = new Dimension(500, 500);
        this.setMinimumSize(dimension);
        this.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (this.getWidth() / 2), middle.y - (this.getHeight() / 2));
        this.setLocation(newLocation);

        this.setLayout(new GridLayout(4, 3));
        initComponents();

        this.setVisible(true);
    }

    private void initComponents() {
        this.lblWhoPlays = new JLabel("On move: ?");
        this.lblTime = new JLabel("Time XX:XX");
        this.lblScore = new JLabel("Score X:Y");

        this.add("", this.lblWhoPlays);
        this.add("", this.lblTime);
        this.add("", this.lblScore);

        this.btnFields = new JButton[9];

        for(int i = 0; i < this.btnFields.length; ++i) {
            this.btnFields[i] = new JButton("");
            int finalI = i;

            this.btnFields[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnFields[finalI].setEnabled(false);
                    btnFields[finalI].setText(Character.toString(playerMark));
                    gameInstance.markPosition(finalI, playerMark);
                }
            });

            this.add(this.btnFields[i]);
        }
    }

    public void enableFreePositions(ArrayList<Integer> freePositions, boolean enable) {
        for (Integer i : freePositions) {
            this.btnFields[i].setEnabled(enable);
        }
    }

    public Game getGameInstance() {
        return this.gameInstance;
    }
}