package library;

/**
 * Created by Rene Sommerfeld on 09.05.2017.
 */

/**
 * Defines a LibraryItem
 */
public abstract class LibraryItem {

    private boolean borrowed;

    public LibraryItem() {

    }

    /**
     * Sets the state of borrowed for this LibraryItem
     * @param borrowed the new state of borrowed
     */
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    /**
     * Returns whether or not the LibraryItem is borrowed
     * @return true if borrowed, otherwise false
     */
    public boolean isBorrowed() {
        return borrowed;
    }

    /**
     * Returns a description for the specific LibraryItem.
     * In many cases all attributes of a specific LibraryItem
     * in sequence
     * @return the description of a LibraryItem
     */
    public abstract String getDescription();


}
