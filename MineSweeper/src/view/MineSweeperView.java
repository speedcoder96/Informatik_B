package view;

import controller.MineSweeperController;
import model.MineSweeperModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class MineSweeperView extends JPanel implements Observer {

    private MineSweeperModel model;
    private TileButtonView[][] tileButtonViews;
    private MineSweeperTileButtonViewRenderer renderer;

    public MineSweeperView(MineSweeperModel model) {
        this.model = model;
        this.setLayout(new GridLayout(model.getFieldHeight(), model.getFieldWidth(), 5, 5));
        renderer = new MineSweeperTileButtonViewRenderer();
        initialize(new MineSweeperController(model));
    }

    private void initialize(MineSweeperController controller) {
        tileButtonViews = new TileButtonView[model.getFieldHeight()][model.getFieldWidth()];
        for(int y = 0; y < tileButtonViews.length; y++) {
            for(int x = 0; x < tileButtonViews[y].length; x++) {
                tileButtonViews[y][x] = new TileButtonView(model, x, y);
                tileButtonViews[y][x].setRenderer(renderer);
                tileButtonViews[y][x].addMouseListener(controller);
                model.addObserver(tileButtonViews[y][x]);
                add(tileButtonViews[y][x]);
            }
        }
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(model.isSolved()) {
            System.out.println("Solved");
        } else if(model.isGameOver()) {
            System.out.println("Game Over");
        }
    }
}
