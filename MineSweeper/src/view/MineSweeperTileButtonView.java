package view;

import model.MineSweeperModel;
import model.MineSweeperTile;

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
            renderer.render(this, MineSweeperTile.Property.fromModel(mineSweeperModel, fieldX, fieldY));
        }
    }

    public static interface Renderer {
        public void render(MineSweeperTileButtonView view, MineSweeperTile.Property property);
    }

}
