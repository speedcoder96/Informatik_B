package view;

import model.MineSweeperTile;

import java.awt.*;

/**
 * Created by Rene Sommerfeld on 30.06.2017.
 */
public class MineSweeperTileButtonViewRenderer implements MineSweeperTileButtonView.Renderer {

    private static final String NOT_REVEALED_STRING = "?";
    private static final String FLAGGED_STRING = "X";

    private static enum RGBCode {
        ZERO(127,127,127),
        ONE(0,0,255),
        TWO(0,255,0),
        THREE(255,0,0),
        FOUR(127,0,127),
        FIVE(255,0,255),
        SIX(127,127,0),
        SEVEN(255,255,0),
        EIGHT(255,127,127);

        private Color color;
        private RGBCode(int r, int g, int b) {
            this.color = new Color(r, g, b);
        }
        public Color getColor() {
            return color;
        }
        public static RGBCode toRGBCode(int count) {
            return RGBCode.values()[count];
        }

    }

    @Override
    public void renderRevealedTile(MineSweeperTileButtonView view, int adjacentMineCount) {
        view.setText(String.valueOf(adjacentMineCount));
        view.setForeground(RGBCode.toRGBCode(adjacentMineCount).getColor());
        view.setBackground(new Color(224, 224, 224));
    }

    @Override
    public void renderNonRevealedTile(MineSweeperTileButtonView view) {
        view.setText(NOT_REVEALED_STRING);
        view.setForeground(Color.BLACK);
        view.setBackground(new Color(192,192,192));
    }

    @Override
    public void renderFlaggedTile(MineSweeperTileButtonView view) {
        view.setText(FLAGGED_STRING);
        view.setBackground(Color.YELLOW);
    }

    @Override
    public void renderRevealedMineTile(MineSweeperTileButtonView view) {
        view.setText("");
        view.setBackground(Color.RED);
    }

}
