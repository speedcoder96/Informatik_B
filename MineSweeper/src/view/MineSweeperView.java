package view;

import controller.MineSweeperController;
import model.MineSweeperModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class MineSweeperView extends JPanel implements Observer {

    private static final Font MAIN_FONT = new Font(Font.MONOSPACED, Font.BOLD, 15);

    private MineSweeperModel model;
    private JLabel flagCountLabel, toRevealTileCountLabel;
    private MineSweeperTileButtonView.Renderer renderer;

    public MineSweeperView(MineSweeperModel model) {
        this.model = model;
        renderer = new MineSweeperTileButtonViewRenderer();

        //set the border layout to layout the two panels accordingly
        setLayout(new BorderLayout());

        //display panel two columns for flag and mine count labels
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 3, 5, 5));

        //creating some labels and setting font
        flagCountLabel = new JLabel();
        JLabel mineCountLabel = new JLabel();
        toRevealTileCountLabel = new JLabel();
        flagCountLabel.setFont(MAIN_FONT.deriveFont(20.0f));
        mineCountLabel.setFont(MAIN_FONT.deriveFont(20.0f));
        toRevealTileCountLabel.setFont(MAIN_FONT.deriveFont(20.0f));

        //display the mine count at its label
        mineCountLabel.setText(String.format("Mines : %3d", model.getMineCount()));
        toRevealTileCountLabel.setText(String.format("Tiles to reveal : %3d",
                (model.getFieldWidth() * model.getFieldHeight()) - (model.getMineCount() + model.getRevealedTileCount())));

        //add components to panel
        displayPanel.add(flagCountLabel);
        displayPanel.add(mineCountLabel);
        displayPanel.add(toRevealTileCountLabel);
        add(BorderLayout.NORTH, displayPanel);

        //initializes the controller
        initialize(new MineSweeperController(model));
    }

    private void initialize(MineSweeperController controller) {
        JPanel tileButtonViewPanel = new JPanel();
        tileButtonViewPanel.setLayout(new GridLayout(model.getFieldHeight(), model.getFieldWidth(), 5, 5));
        MineSweeperTileButtonView[][] tileButtonViews = new MineSweeperTileButtonView[model.getFieldHeight()][model.getFieldWidth()];
        for(int y = 0; y < tileButtonViews.length; y++) {
            for(int x = 0; x < tileButtonViews[y].length; x++) {
                tileButtonViews[y][x] = new MineSweeperTileButtonView(model, x, y);
                tileButtonViews[y][x].setFont(MAIN_FONT.deriveFont(17.0f));
                //sets the renderer that decides how the button should be displayed
                tileButtonViews[y][x].setRenderer(renderer);
                //add controller to handle input events
                tileButtonViews[y][x].addMouseListener(controller);
                model.addObserver(tileButtonViews[y][x]);
                tileButtonViewPanel.add(tileButtonViews[y][x]);
            }
        }
        //adds all buttons to the main panel
        add(BorderLayout.CENTER, tileButtonViewPanel);
        model.addObserver(this);
        model.notifyUIUpdate();
    }

    @Override
    public void update(Observable o, Object arg) {
        Timer timer = new Timer();
        if(model.isSolved()) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    onOptionSelected(JOptionPane.showConfirmDialog(MineSweeperView.this,
                            "Game Solved. Want To Start Another Round?",
                            "Solved",
                            JOptionPane.YES_NO_OPTION));
                }
            }, 1000);

        } else if(model.isGameOver()) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    onOptionSelected(JOptionPane.showConfirmDialog(MineSweeperView.this,
                            "Game Over. You Have Lost. Want To Start Another Round?",
                            "Game Over",
                            JOptionPane.YES_NO_OPTION));
                }
            }, 1000);
        } else {
            flagCountLabel.setText(String.format("Flags: %3d", model.getMineCount() - model.getFlagCount()));
            toRevealTileCountLabel.setText(String.format("Tiles to reveal : %3d",
                    (model.getFieldWidth() * model.getFieldHeight()) - (model.getMineCount() + model.getRevealedTileCount())));
        }
    }

    private void onOptionSelected(int optionSelected) {
        switch(optionSelected) {
            case JOptionPane.YES_OPTION:
                model.reset(model.getFieldWidth(), model.getFieldHeight(), model.getMineCount());
                break;
            case JOptionPane.NO_OPTION:
                System.exit(1);
                break;
        }
    }
}
