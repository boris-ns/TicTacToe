package gameLogic;

/**
 * Created by boris on 8/3/17.
 */
public class MinimaxResult {

    private int bestPosition;
    private int bestScore;

    private MinimaxResult() {
    }

    public MinimaxResult(int bestPosition, int bestScore) {
        this.bestPosition = bestPosition;
        this.bestScore = bestScore;
    }

    public int getBestPosition() {
        return this.bestPosition;
    }

    public int getBestScore() {
        return this.bestScore;
    }

    public void setBestPosition(int bestPosition) {
        this.bestPosition = bestPosition;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
