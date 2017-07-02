import javax.swing.*;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class Bier {

    public static void main(String[] args) {

        BierTheke model = new BierTheke(10);
        BierView view = new BierView(model);

        JFrame frame = new JFrame("Biertheke");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);

    }

}
