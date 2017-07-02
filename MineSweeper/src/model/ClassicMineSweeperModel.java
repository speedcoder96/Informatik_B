package model;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class ClassicMineSweeperModel extends MineSweeperModel {

    private MineSweeperField field;
    private int flagCount;

    public ClassicMineSweeperModel(int fieldWidth, int fieldHeight, int mineCount) {
        setCurrentGameState(MineSweeperModel.GameState.RUNNING);
        field = new MineSweeperField(fieldWidth, fieldHeight, mineCount);
        flagCount = 0;
    }

    @Override
    public void flag(int x, int y) {
        boolean previousState = field.flag(x, y);
        flagCount = (previousState) ? flagCount - 1 : flagCount + 1;
        notifyUIUpdate();
    }

    @Override
    public void reveal(int x, int y) {
        MineSweeperTile current = field.reveal(x, y);
        if(current != null) {
            if(current.isMine()) {
                handleGameOver();
            }
        }
        notifyUIUpdate();
    }

    private void handleGameOver() {
        setCurrentGameState(MineSweeperModel.GameState.GAME_OVER);
        field.revealAll();
    }

    @Override
    public boolean isFlagged(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.isFlagged();
        }
        return false;
    }

    @Override
    public boolean isRevealed(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.isRevealed();
        }
        return false;
    }

    @Override
    public boolean isMine(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.isMine();
        }
        return false;
    }

    @Override
    public boolean isDefused(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.isDefused();
        }
        return false;
    }

    @Override
    public int getAdjacentMineCount(int x, int y) {
        MineSweeperTile current = field.getTile(x, y);
        if(current != null) {
            return current.getAdjacentMineCount();
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
