package gameLogic;

import gui.MainWindow;
import main.Constants;

import java.util.ArrayList;

/**
 * Created by boris on 8/1/17.
 */
public class Game {

    private char[] board;
    private char winner;
    private char player1Mark, player2Mark;

    private Ai ai;

    public Game(char playerMark, char opponentMark) {
        this.player1Mark = playerMark;
        this.player2Mark = opponentMark;
        this.board = new char[9];
        this.ai = new Ai(this.board, this, this.player2Mark);
    }

    public void playerMoved(int position) {
        MainWindow.getInstance().enableFreePositions(findFreePositions(), false);
        this.board[position] = this.player1Mark;

        if (isGameOver()) {
            prepareForNewGame();
            return;
        }

        aiPlaying();

        if (isGameOver()) { // TODO: Try to avoid these duplicate blocs of code. How ???
            prepareForNewGame();
            return;
        }

        MainWindow.getInstance().enableFreePositions(findFreePositions(), true);
    }

    private void aiPlaying() {
        int aiMovePosition = ai.move();
        this.board[aiMovePosition] = ai.getPlayerMark();
        MainWindow.getInstance().markButton(aiMovePosition, ai.getPlayerMark(), player1Mark);
    }

    private void prepareForNewGame() {
        MainWindow.getInstance().showGameOverScreen();
        ai.setPlayerMark(player1Mark);
        char tmp = player1Mark;
        this.player1Mark = this.player2Mark;
        this.player2Mark = tmp;
        MainWindow.getInstance().setTextLblWhoPlays(player1Mark);
    }

    public ArrayList<Integer> findFreePositions() {
        ArrayList<Integer> freePositions = new ArrayList<Integer>();

        for (int i = 0; i < this.board.length; ++i) {
            if (this.board[i] == Constants.EMPTY_SPOT) {
                freePositions.add(i);
            }
        }

        return freePositions;
    }

    public boolean isGameOver() {
        int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

        if (isItDraw()) {
            this.winner = Constants.DRAW;
            return true;
        }

        for (int i = 0; i < winningPositions.length; ++i) {
            if (board[winningPositions[i][0]] == Constants.EMPTY_SPOT)
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

    private boolean isItDraw() {
        for (int i = 0; i < this.board.length; ++i) {
            if (this.board[i] == Constants.EMPTY_SPOT) {
                return false;
            }
        }

        return true;
    }

    public void resetBoard() {
        for (int i = 0; i < this.board.length; ++i) {
            this.board[i] = Constants.EMPTY_SPOT;
        }
    }

    public char getWinner() {
        return this.winner;
    }

    public char getPlayer1Mark() {
        return this.player1Mark;
    }

    public char getPlayer2Mark() {
        return this.player2Mark;
    }
}
