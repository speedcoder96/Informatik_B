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
        this.flagged = false;
        this.mine = false;
        this.revealed = false;
        this.adjacentMineCount = 0;
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

    public boolean isMine() {
        return mine;
    }

    public void reveal() {
        if(!flagged)
            this.revealed = true;
    }

    public void flag() {
        flagged = !flagged;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean isDefused() {
        return mine && flagged;
    }

    public void setAdjacentMineCount(int adjacentMineCount) {
        this.adjacentMineCount = adjacentMineCount;
    }

    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }

    public boolean hasAdjacentMines() {
        return adjacentMineCount != 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(revealed) {
            if(mine) {
                if(flagged) {
                    builder.append("[").append("M").append("]");
                } else {
                    builder.append("M");
                }
            } else {
                if(flagged) {
                    builder.append("[").append(adjacentMineCount).append("]");
                } else {
                    builder.append(adjacentMineCount);
                }
            }
        } else {
            builder.append("?");
        }
        return builder.toString();
    }
}
