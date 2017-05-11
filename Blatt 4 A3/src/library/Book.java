package library;

/**
 * Created by Rene Sommerfeld on 09.05.2017.
 */
public class Book extends LibraryItem {

    private String title;
    private String author;

    /**
     * Creates a book with title and author
     * @param title the title
     * @param author the author
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Returns the title of the book
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book
     * @return
     */
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return title + " " + author;
    }
}
