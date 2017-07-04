package model;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class MineSweeperTile {

    private int x;
    private int y;
    private boolean revealed;
    private boolean flagged;
    private boolean mine;
    private int adjacentMineCount;

    public MineSweeperTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void flag() {
        flagged = !flagged;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isDefused() {
        return flagged && mine;
    }

    public boolean isMine() {
        return mine;
    }

    public void setAdjacentMineCount(int adjacentMineCount) {
        this.adjacentMineCount = adjacentMineCount;
    }

    public boolean hasAdjacentMines() {
        return adjacentMineCount != 0;
    }

    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }

}
