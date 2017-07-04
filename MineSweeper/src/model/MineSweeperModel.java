package model;

import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 30.06.2017.
 */
public abstract class MineSweeperModel extends Observable {

    /**
     * Defines the states of the game
     */
    public static enum GameState {
        RUNNING, GAME_OVER, SOLVED
    }

    /**
     * Defines the current game state
     */
    private GameState currentGameState;

    public MineSweeperModel() {
        currentGameState = GameState.RUNNING;
    }

    /**
     * Sets the current game state of the game
     * @param currentGameState the current game state
     */
    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    /**
     * Returns whether or not the game is solved
     * @return if the game is solved
     */
    public boolean isSolved() {
        return currentGameState == GameState.SOLVED;
    }

    /**
     * Returns whether or not the game is lost
     * @return if the game is lost
     */
    public boolean isGameOver() {
        return currentGameState == GameState.GAME_OVER;
    }

    /**
     * Returns the current game state
     * @return current game state
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * Notifies the ui to update
     * @param args special argument
     */
    public void notifyUIUpdate(Object args) {
        setChanged();
        notifyObservers(args);
    }

    /**
     * Calls {@link #notifyUIUpdate(Object)} with null value
     */
    public void notifyUIUpdate() {
        notifyUIUpdate(null);
    }

    /**
     * Resets the game. Should start a new game with the specified
     * {@code width}, {@code height} and {@code mineCount}.
     * @param width the width of the field
     * @param height the height of the field
     * @param mineCount the amount of mines on the field
     */
    public abstract void reset(int width, int height, int mineCount);

    /**
     * Flags the tile specified by {@code x} and {@code y}
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public abstract void flag(int x, int y);

    /**
     * Returns whether or not the tile specified by
     * {@code x} and {@code y} is flagged
     * @param x the x coordinate
     * @param y the y coordinate
     * @return if the tile is flagged
     */
    public abstract boolean isFlagged(int x, int y);

    /**
     * Returns the number of tiles being flagged
     * @return number of tiles being flagged
     */
    public abstract int getFlagCount();

    /**
     * Reveals the tile specified by
     * {@code x} and {@code y}
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public abstract void reveal(int x, int y);

    /**
     * Returns whether or not the tile specified by
     * {@code x} and {@code y} is revealed
     * @param x the x coordinate
     * @param y the y coordinate
     * @return if the tile is revealed
     */
    public abstract boolean isRevealed(int x, int y);

    /**
     * Returns whether or not the tile specified by
     * {@code x} and {@code y} is a mine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return if the tile is a mine
     */
    public abstract boolean isMine(int x, int y);

    /**
     * Returns the number of mines on the field
     * @return the number of mines
     */
    public abstract int getMineCount();

    /**
     * Returns the number of adjacent mines of the tile
     * specified by {@code x} and {@code y}
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the number of adjacent mines
     */
    public abstract int getAdjacentMineCount(int x, int y);

    /**
     * Returns the number of tiles being revealed
     * @return the number of revealed tiles
     */
    public abstract int getRevealedTileCount();

    /**
     * Returns the width of the field
     * @return the width
     */
    public abstract int getFieldWidth();

    /**
     * Returns the height of the field
     * @return the height
     */
    public abstract int getFieldHeight();



}
