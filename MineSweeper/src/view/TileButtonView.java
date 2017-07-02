package view;

import model.MineSweeperModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class TileButtonView extends JButton implements Observer {

    private MineSweeperModel mineSweeperModel;
    private TileButtonView.Renderer renderer;
    private int fieldX, fieldY;

    public TileButtonView(MineSweeperModel mineSweeperModel, int fieldX, int fieldY) {
        super();
        this.mineSweeperModel = mineSweeperModel;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public void setRenderer(TileButtonView.Renderer renderer) {
        this.renderer = renderer;
    }

    public int getFieldX() {
        return fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(renderer != null) {
            boolean revealed = mineSweeperModel.isRevealed(fieldX, fieldY);
            boolean flagged = mineSweeperModel.isFlagged(fieldX, fieldY);
            boolean mine = mineSweeperModel.isMine(fieldX, fieldY);
            int adjacentMineCount = mineSweeperModel.getAdjacentMineCount(fieldX, fieldY);
            renderer.render(this, revealed, flagged, mine, adjacentMineCount);
        }
    }

    public static interface Renderer {
        public void render(TileButtonView view, boolean revealed, boolean flagged, boolean mine, int adjacentMineCount);
    }

}
