package gameLogic;

import gui.MainWindow;
import java.util.ArrayList;

/**
 * Created by boris on 8/1/17.
 */
public class Game {

    private char[] board;
    private char winner;

    private MainWindow viewComponent;

    public Game() {
        this.board = new char[9];
        findFreePositions();
    }

    private ArrayList<Integer> findFreePositions() {
        ArrayList<Integer> freePositions = new ArrayList<Integer>();

        for (int i = 0; i < board.length; ++i) {
            if (this.board[i] == Character.MIN_VALUE) {
                freePositions.add(i);
            }
        }

        return freePositions;
    }

    private boolean isGameOver() {
        int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

        for (int i = 0; i < winningPositions.length; ++i) {
            if (board[winningPositions[i][0]] == Character.MIN_VALUE)
                continue;

            if (board[winningPositions[i][0]] == board[winningPositions[i][1]] &&
                    board[winningPositions[i][0]] == board[winningPositions[i][2]] &&
                    board[winningPositions[i][1]] == board[winningPositions[i][2]]) {
                this.winner = board[winningPositions[i][0]];
                return true;
            }
        }

        this.winner = 'D';
        return false;
    }

    public void markPosition(int position, char playerMark) {
        this.board[position] = playerMark;
    }

    public void play() {
        for (int i = 0; i < 9; ++i) {
            if (isGameOver()) {
                // TODO: do stuff
            }

            if (i % 2 == 0) {
                this.viewComponent.enableFreePositions(findFreePositions(), false);
            } else {
                this.viewComponent.enableFreePositions(findFreePositions(), true);
            }
        }
    }

    public void setViewComponent(MainWindow viewComponent) {
        this.viewComponent = viewComponent;
    }
}
