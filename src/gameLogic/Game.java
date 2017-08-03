package gameLogic;

import gui.MainWindow;
import java.util.ArrayList;
import java.util.stream.Collector;

/**
 * Created by boris on 8/1/17.
 */
public class Game {

    private char[] board;
    private char winner;

    private Ai ai;

    public Game() {
        this.board = new char[9];
        this.ai = new Ai(this.board, this, 'O');
    }

    public ArrayList<Integer> findFreePositions() {
        ArrayList<Integer> freePositions = new ArrayList<Integer>();

        for (int i = 0; i < board.length; ++i) {
            if (this.board[i] == Character.MIN_VALUE) {
                freePositions.add(i);
            }
        }

        return freePositions;
    }

    private boolean isItDraw() {
        for (int i = 0; i < this.board.length; ++i) {
            if (this.board[i] == Character.MIN_VALUE) {
                return false;
            }
        }

        return true;
    }

    public boolean isGameOver() {
        int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

        if (isItDraw()) {
            this.winner = 'D';
            return true;
        }

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

        return false;
    }

    public void markPosition(int position, char playerMark) {
        MainWindow.getInstance().enableFreePositions(findFreePositions(), false);
        this.board[position] = playerMark;

        if (isGameOver()) {
            System.out.println("GAME OVER");
            MainWindow.getInstance().gameOver();
            return;
        }

        int aiPosition = ai.move();
        this.board[aiPosition] = ai.getPlayerMark();
        MainWindow.getInstance().aiMoved(aiPosition);

        if (isGameOver()) { // TODO: NE DUPLIRAJ KOD    DRY
            System.out.println("GAME OVER");
            MainWindow.getInstance().gameOver();
            return;
        }


        MainWindow.getInstance().enableFreePositions(findFreePositions(), true);
    }

    public void resetBoard() {
        for (int i = 0; i < this.board.length; ++i) {
            this.board[i] = Character.MIN_VALUE;
        }
    }

    public char getWinner() {
        return this.winner;
    }
}
