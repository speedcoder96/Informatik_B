import model.ClassicMineSweeperModel;
import model.MineSweeperModel;
import view.MineSweeperView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class MineSweeper extends JFrame {

    public MineSweeper(MineSweeperView view) {
        this.setTitle("MineSweeper");
        this.setContentPane(view);
        this.setMinimumSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MineSweeperModel model = new ClassicMineSweeperModel(10, 10, 3);
            MineSweeperView view = new MineSweeperView(model);
            MineSweeper ms = new MineSweeper(view);
            ms.pack();
        });
    }

}
