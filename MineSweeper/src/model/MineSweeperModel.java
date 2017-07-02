package model;

import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 30.06.2017.
 */
public abstract class MineSweeperModel extends Observable {

    public static enum GameState {
        RUNNING, GAME_OVER, SOLVED
    }

    private GameState currentGameState;

    public MineSweeperModel() {
        currentGameState = GameState.RUNNING;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
    public boolean isSolved() {
        return currentGameState == GameState.SOLVED;
    }
    public boolean isGameOver() {
        return currentGameState == GameState.GAME_OVER;
    }
    public void notifyUIUpdate() {
        setChanged();
        notifyObservers();
    }

    public abstract void flag(int x, int y);
    public abstract boolean isFlagged(int x, int y);
    public abstract void reveal(int x, int y);
    public abstract boolean isRevealed(int x, int y);
    public abstract boolean isMine(int x, int y);
    public abstract boolean isDefused(int x, int y);
    public abstract int getAdjacentMineCount(int x, int y);
    public abstract int getFieldWidth();
    public abstract int getFieldHeight();



}
