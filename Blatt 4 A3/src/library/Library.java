package library;

/**
 * Created by Rene Sommerfeld on 09.05.2017.
 */
public class Library {

    //TODO This inventory attribute isn't defined in UML diagram ?!?!
    private List inventory;

    public Library() {
        inventory = new List();
    }

    /**
     * Adds an item to the library's inventory
     * @param item the item to add
     */
    public void addItem(LibraryItem item) {
        inventory.add(item);
    }

    /**
     * Deletes an item from the library's inventory
     * @param toRemove the item dummy to remove
     */
    public void deleteItem(LibraryItem toRemove) {
        inventory.reset();
        while(!inventory.endpos()) {
            LibraryItem current = (LibraryItem)inventory.elem();
            if(current.getDescription().equals(toRemove.getDescription())) {
                inventory.delete();
                return;
            }
            inventory.advance();
        }
    }

    /**
     * Searches the inventory of the Library
     * for all LibraryItems that contain the
     * <tt>text</tt> in their description
     * @param text the text to search LibraryItems for
     * @return an already resetted list of all LibraryItems that
     * contain the <tt>text</tt> in their description
     */
    public List search(String text) {
        List foundItems = new List();
        inventory.reset();
        while(!inventory.endpos()) {
            LibraryItem current = (LibraryItem)inventory.elem();
            if(current.getDescription().contains(text)) {
                foundItems.add(current);
            }
            inventory.advance();
        }
        foundItems.reset();
        return foundItems;
    }

}
