package model;



/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class ClassicMineSweeperModel extends MineSweeperModel {
    private MineSweeperField field;
    private int flagCount;

    public ClassicMineSweeperModel(int fieldWidth, int fieldHeight, int mineCount) {
        reset(fieldWidth, fieldHeight, mineCount);
    }

    @Override
    public void reset(int width, int height, int mineCount) {
        field = new MineSweeperField(width, height, mineCount);
        setCurrentGameState(GameState.RUNNING);
        flagCount = 0;
        notifyUIUpdate();
    }

    @Override
    public void flag(int x, int y) {
        if(!isRevealed(x, y)) {
            if(!isFlagged(x, y) && flagCount < field.getMineCount()) {
                flagCount++;
                field.flag(x, y);
            } else if(isFlagged(x, y)) {
                flagCount--;
                field.flag(x, y);
            }
            boolean solved = field.isSolved();
            if(solved) {
                handleSolved();
            }
            notifyUIUpdate();
        }
    }

    @Override
    public int getFlagCount() {
        return flagCount;
    }

    @Override
    public int getMineCount() {
        return field.getMineCount();
    }

    @Override
    public void reveal(int x, int y) {
        if(!isRevealed(x, y) && !isFlagged(x, y)) {
            MineSweeperTile current = field.reveal(x, y);
            if(current != null) {
                if(current.getProperty().isMine()) {
                    handleGameOver();
                } else {
                    if(field.isSolved()) {
                        handleSolved();
                    }
                }
            }
            notifyUIUpdate();
        }
    }

    @Override
    public int getRevealedTileCount() {
        return field.getRevealedTileCount();
    }

    private void handleGameOver() {
        setCurrentGameState(GameState.GAME_OVER);
        field.revealAll();
    }

    private void handleSolved() {
        setCurrentGameState(GameState.SOLVED);
    }

    @Override
    public boolean isFlagged(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.getProperty().isFlagged();
        }
        return false;
    }

    @Override
    public boolean isRevealed(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.getProperty().isRevealed();
        }
        return false;
    }

    @Override
    public boolean isMine(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.getProperty().isMine();
        }
        return false;
    }

    @Override
    public int getAdjacentMineCount(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.getProperty().getAdjacentMineCount();
        }
        return -1;
    }

    @Override
    public int getFieldWidth() {
        return field.getWidth();
    }

    @Override
    public int getFieldHeight() {
        return field.getHeight();
    }
}
