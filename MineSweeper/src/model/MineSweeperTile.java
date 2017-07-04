package model;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class MineSweeperTile {

    private int x;
    private int y;
    private Property property;
    private boolean revealed;
    private boolean flagged;
    private boolean mine;
    private int adjacentMineCount;

    public MineSweeperTile(int x, int y) {
        this.x = x;
        this.y = y;
        property = new Property();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Property getProperty() {
        return property;
    }

    public void setMine(boolean mine) {
        property.mine = mine;
    }

    public void reveal() {
        property.revealed = true;
    }

    public void flag() {
        property.flagged = !property.flagged;
    }

    public void setAdjacentMineCount(int adjacentMineCount) {
        property.adjacentMineCount = adjacentMineCount;
    }

    public boolean hasAdjacentMines() {
        return property.adjacentMineCount != 0;
    }


    public static class Property {
        private boolean revealed;
        private boolean flagged;
        private boolean mine;
        private int adjacentMineCount;
        public Property() {
            this(false, false, false, 0);
        }

        public Property(boolean revealed, boolean flagged, boolean mine, int adjacentMineCount) {
            this.revealed = revealed;
            this.flagged = flagged;
            this.mine = mine;
            this.adjacentMineCount = adjacentMineCount;
        }

        public static Property fromModel(MineSweeperModel model, int x, int y) {
            if(model != null) {
                return new Property(model, x, y);
            }
            return null;
        }

        private Property(MineSweeperModel model, int x, int y) {
            this(model.isRevealed(x, y), model.isFlagged(x, y), model.isMine(x, y), model.getAdjacentMineCount(x, y));
        }

        public boolean isRevealed() {
            return revealed;
        }

        public boolean isFlagged() {
            return flagged;
        }

        public boolean isMine() {
            return mine;
        }

        public boolean isDefused() {
            return mine && flagged;
        }

        public int getAdjacentMineCount() {
            return adjacentMineCount;
        }
    }

}
