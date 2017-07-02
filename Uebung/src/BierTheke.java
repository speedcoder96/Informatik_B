import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 29.06.2017.
 */
public class BierTheke extends Observable {

    private int bier;
    private int groesse;

    public BierTheke(int groesse) {
        this.groesse = groesse;
        this.bier = 0;
    }

    public void zapfen()  {
        if(!istVoll())
            bier++;
        setChanged();
        notifyObservers();
    }

    public  void verkaufen() {
        if(!istLeer())
            bier--;
        setChanged();
        notifyObservers();
    }

    public int getAnzahlBiere() {
        return this.bier;
    }

    public boolean istVoll() {
        return bier == groesse;
    }

    public boolean istLeer() {
        return bier == 0;
    }

}
