package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rene Sommerfeld on 30.06.2017.
 */
public class MineSweeperTileButtonViewRenderer implements TileButtonView.Renderer {

    private static final String NOT_REVEALED_STRING = "?";
    private static final String FLAGGED_STRING = "FLAG";

    @Override
    public void render(TileButtonView view, boolean revealed, boolean flagged, boolean mine, int adjacentMineCount) {
        if(revealed) {
            if(mine) {
                view.setText("");
                view.setBackground(Color.RED);
            } else {
                view.setText(String.valueOf(adjacentMineCount));
                view.setForeground(new Color(255 - ((255 / 8) * adjacentMineCount), 255 - ((255 / 8) * adjacentMineCount), 255 - ((255 / 8) * adjacentMineCount)));
            }
        } else {
            if(flagged) {
                view.setText(FLAGGED_STRING);
                view.setBackground(Color.YELLOW);
            } else {
                view.setText(NOT_REVEALED_STRING);
                view.setForeground(Color.BLACK);
                view.setBackground(Color.GRAY);
            }
        }
    }
}
