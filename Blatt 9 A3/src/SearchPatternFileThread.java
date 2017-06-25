import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Rene Sommerfeld on 24.06.2017.
 * A thread that searches a regex pattern in a given
 * file.
 */
public class SearchPatternFileThread extends Thread {

    /**
     * the file to search the pattern in
     */
    private File fileToSearch;
    /**
     * the pattern to search for
     */
    private Pattern regexPattern;

    /**
     * listener that listens to the file threads
     */
    private SearchPatternFileThread.Listener listener;

    /**
     * Creates a {@code SearchPatternFileThread} that searches a given file {@code fileToSearch}
     * for a given pattern {@code regexPattern}.
     * @param fileToSearch
     * @param regexPattern
     * @param listener the listener that listens to the file threads
     */
    public SearchPatternFileThread(File fileToSearch, Pattern regexPattern, SearchPatternFileThread.Listener listener) {
        this.fileToSearch = fileToSearch;
        this.regexPattern = regexPattern;
        this.listener = listener;
    }

    @Override
    public void run() {
        List<SearchResult> fileSearchResults = null;
        try(SearchLineReader searchLineReader = new SearchLineReader(new FileReader(fileToSearch), regexPattern)) {
            String line;
            fileSearchResults = listener.retrieveFileSearchResults(fileToSearch);
            while((line = searchLineReader.readLine()) != null) {
                if(searchLineReader.getAmountOfMatches() > 0) {
                    fileSearchResults.add(new SearchResult(line, searchLineReader.getLineNumber(),
                            searchLineReader.getAmountOfMatches()));
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Listener that listens to the different threads
     */
    public static interface Listener {
        public List<SearchResult> retrieveFileSearchResults(File file);
    }
}
