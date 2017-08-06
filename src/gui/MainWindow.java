package gui;

import gameLogic.Game;
import main.Constants;
import main.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by boris on 8/1/17.
 */
public class MainWindow extends JFrame {

    private static MainWindow instance = null;
    private Game gameInstance = null;

    private JLabel lblWhoPlays, lblTime, lblScore;
    private JButton[] btnFields;

    private static char playerMark, opponentMark;

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }

        return instance;
    }

    public static void startGame() {
        getInstance().gameInstance = new Game(playerMark, opponentMark);
    }

    private MainWindow() {
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Utility.loadImageFromResource("res/tic_tac_toe_logo.png"));
        this.setResizable(true);
        this.setMinimumSize(new Dimension(500, 500));
        this.pack();
        setWindowLocation();
        this.setLayout(new GridLayout(4, 3));

        initComponents();

        this.setVisible(true);

        showWelcomeScreen();
    }

    private void setWindowLocation() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (this.getWidth() / 2), middle.y - (this.getHeight() / 2));
        this.setLocation(newLocation);
    }

    private void initComponents() {
        this.lblWhoPlays = new JLabel("On move: ?");
        this.lblTime = new JLabel("Time XX:XX");
        this.lblScore = new JLabel("Score X:Y");

        this.add(this.lblWhoPlays);
        this.add(this.lblTime);
        this.add(this.lblScore);

        this.btnFields = new JButton[9];

        for(int i = 0; i < this.btnFields.length; ++i) {
            this.btnFields[i] = new JButton("");
            int finalI = i;

            this.btnFields[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    markButton(finalI, gameInstance.getPlayer1Mark(), gameInstance.getPlayer2Mark());
                    gameInstance.playerMoved(finalI);
                }
            });

            this.add(this.btnFields[i]);
        }
    }

    public void markButton(int position, char player1Mark, char player2Mark) {
        btnFields[position].setEnabled(false);
        btnFields[position].setText(Character.toString(player1Mark));
        btnFields[position].setFont(new Font("Arial Black", Font.PLAIN, 50));
        setTextLblWhoPlays(player2Mark);
    }

    public void setTextLblWhoPlays(char mark) {
        this.lblWhoPlays.setText("On move: " + mark);
    }

    public void showGameOverScreen() {
        Object[] btnOptions = {"Play again", "Exit"};
        JPanel panel = new JPanel();

        panel.add(new JLabel(getResultMessage()));

        int result = JOptionPane.showOptionDialog(null, panel, "Game over",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, btnOptions, null);

        if (result == JOptionPane.YES_OPTION){
            this.gameInstance.resetBoard();
            resetButtons();
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private String getResultMessage() {
        if (this.gameInstance.getWinner() == this.gameInstance.getPlayer1Mark())
            return "You won!";
        else if (this.gameInstance.getWinner() == Constants.DRAW)
            return "Draw.";
        else
            return "You lost.";
    }

    private void showWelcomeScreen() {
        Object[] btnOptions = {"X", "O"};
        JPanel panel = new JPanel();
        String message = "Choose your mark";
        panel.add(new JLabel(message));

        int result = JOptionPane.showOptionDialog(null, panel, message,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, btnOptions, null);

        if (result == JOptionPane.YES_OPTION){
            playerMark = Constants.IKS;
            opponentMark = Constants.OKS;
        } else if (result == JOptionPane.NO_OPTION) {
            playerMark = Constants.OKS;
            opponentMark = Constants.IKS;
        }
    }

    public void enableFreePositions(ArrayList<Integer> freePositions, boolean enable) {
        for (Integer i : freePositions) {
            this.btnFields[i].setEnabled(enable);
        }
    }

    private void resetButtons() {
        for (int i = 0; i < this.btnFields.length; ++i) {
            this.btnFields[i].setEnabled(true);
            this.btnFields[i].setText("");
        }
    }

}
