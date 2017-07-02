import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class BierView extends JPanel implements Observer {

    private JButton bierButton;
    private JLabel bierCountLabel;

    private BierTheke model;

    public BierView(BierTheke model) {
        this.model = model;
        model.addObserver(this);

        bierButton = new JButton("Bier");
        bierCountLabel = new JLabel("Anzahl an Biere: ");

        bierButton.addMouseListener(new BierController(model));

        setLayout(new BorderLayout());
        add(bierButton, BorderLayout.SOUTH);
        add(bierCountLabel, BorderLayout.NORTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        bierCountLabel.setText("Anzahl der Biere : " + model.getAnzahlBiere());
    }
}
