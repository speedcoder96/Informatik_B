package view;

import model.MineSweeperModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class MineSweeperTileButtonView extends JButton implements Observer {

    private MineSweeperModel mineSweeperModel;
    private MineSweeperTileButtonView.Renderer renderer;
    private int fieldX, fieldY;

    public MineSweeperTileButtonView(MineSweeperModel mineSweeperModel, int fieldX, int fieldY) {
        super();
        this.mineSweeperModel = mineSweeperModel;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public void setRenderer(MineSweeperTileButtonView.Renderer renderer) {
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
            if(revealed) {
                if(mine) {
                    renderer.renderRevealedMineTile(this);
                } else {
                    renderer.renderRevealedTile(this, adjacentMineCount);
                }
            } else {
                if(flagged) {
                    renderer.renderFlaggedTile(this);
                } else {
                    renderer.renderNonRevealedTile(this);
                }
            }
        }
    }

    public static interface Renderer {
        public void renderRevealedTile(MineSweeperTileButtonView view, int adjacentMineCount);
        public void renderNonRevealedTile(MineSweeperTileButtonView view);
        public void renderRevealedMineTile(MineSweeperTileButtonView view);
        public void renderFlaggedTile(MineSweeperTileButtonView view);
    }

}
