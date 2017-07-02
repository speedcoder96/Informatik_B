import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class BierController extends MouseAdapter {

    private BierTheke model;

    public BierController(BierTheke model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                model.zapfen();
                break;
            case MouseEvent.BUTTON3:
                model.verkaufen();
                break;
        }
    }


}
