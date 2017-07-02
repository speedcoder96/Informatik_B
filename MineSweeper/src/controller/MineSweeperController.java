package controller;

import model.MineSweeperModel;
import view.TileButtonView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class MineSweeperController extends MouseAdapter {

    private MineSweeperModel model;

    public MineSweeperController(MineSweeperModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        TileButtonView tileButtonView = (TileButtonView)e.getSource();
        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                model.reveal(tileButtonView.getFieldX(), tileButtonView.getFieldY());
                break;
            case MouseEvent.BUTTON3:
                model.flag(tileButtonView.getFieldX(), tileButtonView.getFieldY());
                break;
        }
    }
}
