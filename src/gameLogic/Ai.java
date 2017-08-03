package gameLogic;

/**
 * Created by boris on 8/3/17.
 */
public class Ai {

    private Game gameInstance;
    private char[] board;
    private char playerMark;

    private Ai() {
    }

    public Ai(char[] board, Game gameInstance, char playerMark) {
        this.board = board;
        this.gameInstance = gameInstance;
        this.playerMark = playerMark;
    }

    public int move() {
        return maxiMove().getBestPosition();
    }

    private MinimaxResult maxiMove() {
        int scoreTmp = -1;
        MinimaxResult result = new MinimaxResult(-1, -1);

        for (Integer position : this.gameInstance.findFreePositions()) {
            this.board[position] = this.playerMark;

            if (this.gameInstance.isGameOver()) {
                scoreTmp = getScore();
            } else {
                scoreTmp = miniMove().getBestScore();
            }

            this.board[position] = Character.MIN_VALUE;

            if (result.getBestScore() == -1 || scoreTmp > result.getBestScore()) {
                result.setBestScore(scoreTmp);
                result.setBestPosition(position);
            }
        }

        return result;
    }

    private MinimaxResult miniMove() {
        int scoreTmp = -1;
        MinimaxResult result = new MinimaxResult(-1, -1);

        for (Integer position : this.gameInstance.findFreePositions()) {
            this.board[position] = findOpponentMarker(this.playerMark);

            if (this.gameInstance.isGameOver()) {
                scoreTmp = getScore();
            } else {
                scoreTmp = maxiMove().getBestScore();
            }

            this.board[position] = Character.MIN_VALUE;

            if (result.getBestScore() == -1 || scoreTmp < result.getBestScore()) {
                result.setBestScore(scoreTmp);
                result.setBestPosition(position);
            }
        }

        return result;
    }

    private char findOpponentMarker(char mark) {
        return (mark == 'X') ? 'O' : 'X';
    }

    private int getScore() {
        if (gameInstance.getWinner() == playerMark)
            return 10;
        else if (gameInstance.getWinner() == findOpponentMarker(playerMark))
            return -10;

        return 0;
    }

    public char getPlayerMark() {
        return this.playerMark;
    }
}
