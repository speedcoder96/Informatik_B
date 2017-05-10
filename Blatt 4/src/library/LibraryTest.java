package library;

import test.core.AssertionEvaluator;
import test.values.IntAssertion;
import test.values.ObjectAssertion;


/**
 * Created by Rene Sommerfeld on 09.05.2017.
 */
public class LibraryTest {

    public static void main(String[] args) {
        testItemDescription();
        testSearchItem();
        testSearchItems();
        testAddItems();
        testRemoveItem();
        AssertionEvaluator.getInstance().printSummary();
    }

    private static void testItemDescription() {
        Book b = new Book("Harry Potter", "J.K. Rowling");
        AssertionEvaluator.getInstance().evaluate(
                ObjectAssertion.create("Description Test Of 'Harry Potter'",
                        "Harry Potter J.K. Rowling", b.getDescription()));
    }

    private static void testSearchItem() {
        Library lib = new Library();
        Book b = new Book("Harry Potter", "J.K. Rowling");
        BluRay bd = new BluRay("Saw", "James Wan");

        lib.addItem(b);
        lib.addItem(bd);

        //This result should only contain the book of Harry Potter
        //The first element in list has to be the book of Harry Potter
        List result = lib.search("Harry Potter");
        LibraryItem item = (LibraryItem)result.elem();
        AssertionEvaluator.getInstance().evaluate(
                ObjectAssertion.create("Search For 'Harry Potter'",
                        "Harry Potter J.K. Rowling", item.getDescription()));

    }

    private static void testSearchItems() {
        Library lib = new Library();
        Book b = new Book("Harry Potter", "J.K. Rowling");
        BluRay bd = new BluRay("Saw", "James Wan");

        lib.addItem(b);
        lib.addItem(bd);

        //This result should contain both added elements, because the
        //author and the director both starts with a 'J'
        List result = lib.search("J");
        LibraryItem firstItem = (LibraryItem)result.elem();
        result.advance();
        LibraryItem secondItem = (LibraryItem)result.elem();
        AssertionEvaluator.getInstance().evaluate(
            ObjectAssertion.create("Search For 'J' Should Contain 'Harry Potter'",
                    "Harry Potter J.K. Rowling", firstItem.getDescription()));
        AssertionEvaluator.getInstance().evaluate(
                ObjectAssertion.create("Search For 'J' Should Contain 'Saw'",
                        "Saw James Wan", secondItem.getDescription()));
    }

    //Extra test. Searching items already requires the adding of items
    //to library. So the result of this test shows that adding items
    //REALLY works
    private static void testAddItems() {
        Library lib = new Library();
        Book b = new Book("Harry Potter", "J.K. Rowling");
        BluRay bd = new BluRay("Saw", "James Wan");
        lib.addItem(b);
        lib.addItem(bd);

        int count = 0;
        //both author and director contain "J" so both should
        //be contained in search result
        List result = lib.search("J");
        while(!result.endpos()) {
            count++;
            result.advance();
        }

        AssertionEvaluator.getInstance().evaluate(
                IntAssertion.create("Add Test Two Items", 2, count));

    }

    private static void testRemoveItem() {
        Library lib = new Library();
        Book b = new Book("Harry Potter", "J.K. Rowling");
        BluRay bd = new BluRay("Saw", "James Wan");
        lib.addItem(b);
        lib.addItem(bd);

        //Should remove 'Saw' -> item count of library should be 1
        lib.deleteItem(bd);

        //If library contains still both elements the search query 'J'
        //should find both elements. If the result count is only 1, than
        //the item 'Saw' is successfully removed from library
        int count = 0;
        List result = lib.search("J");
        while(!result.endpos()) {
            count++;
            result.advance();
        }

        AssertionEvaluator.getInstance().evaluate(
                IntAssertion.create("Remove 'Saw' From Library", 1, count));

    }





}
