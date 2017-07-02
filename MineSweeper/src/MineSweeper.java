import model.ClassicMineSweeperModel;
import model.MineSweeperModel;
import view.MineSweeperView;

import javax.swing.*;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class MineSweeper extends JFrame {

    public MineSweeper(MineSweeperView view) {
        this.setTitle("MineSweeper");
        this.setContentPane(view);
        this.setSize(800,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MineSweeperModel model = new ClassicMineSweeperModel(10, 10, 40);
            MineSweeperView view = new MineSweeperView(model);
            new MineSweeper(view);
            model.notifyUIUpdate();
        });
    }

}
