package library;

/**
 * Created by Rene Sommerfeld on 09.05.2017.
 */
public class BluRay extends LibraryItem {

    private String title;
    private String director;

    /**
     * Creates a BluRay with title and director.
     * @param title the title
     * @param director the director
     */
    public BluRay(String title, String director) {
        this.title = title;
        this.director = director;
    }

    /**
     * Returns the title of the BluRay
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the director of the BluRay
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    @Override
    public String getDescription() {
        return title + " " + director;
    }
}
