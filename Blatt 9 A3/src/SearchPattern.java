import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by Rene Sommerfeld on 24.06.2017.
 */
public class SearchPattern extends FileVisitorAdapter implements SearchPatternFileThread.Listener {

    /**
     * holds the search result of each file
     */
    private ConcurrentHashMap<File, List<SearchResult>> searchResults;

    /**
     * holds all current started search currentStartedThreads
     */
    private List<SearchPatternFileThread> currentStartedThreads;

    /**
     * handler for uncaught exceptions of the threads
     */
    private Thread.UncaughtExceptionHandler handler;

    /**
     * the regex pattern to search for
     */
    private Pattern regexPattern;

    /**
     * whether or not the search is recursive
     */
    private boolean recursive;

    /**
     * SearchPattern
     * @param regexPattern the regex pattern to search for
     * @param recursive the recursive
     */
    public SearchPattern(Pattern regexPattern, boolean recursive) {
        this.regexPattern = regexPattern;
        this.recursive = recursive;
        searchResults = new ConcurrentHashMap<>();
        currentStartedThreads = new ArrayList<SearchPatternFileThread>();
        //lambda expression: instead of writing new Thread.UncaughtExceptionHandler() {public void uncaughtException(...)....}
        handler = (Thread t, Throwable e) -> {printError(e.getMessage());};
    }

    @Override
    public FileVisitResult preVisitDirectory(File dir) {
        if(recursive) {
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

    @Override
    public FileVisitResult visitFile(File file) {
        //creates a new thread to search the file
        SearchPatternFileThread thread = new SearchPatternFileThread(file, regexPattern, this);
        currentStartedThreads.add(thread);
        //sets the exception handler
        thread.setUncaughtExceptionHandler(handler);
        //starts the thread
        thread.start();
        return FileVisitResult.CONTINUE;
    }

    /**
     * Returns the list of all current started threads.
     * @return a list of all threads
     */
    public List<SearchPatternFileThread> getCurrentStartedThreads() {
        return currentStartedThreads;
    }

    @Override
    public List<SearchResult> retrieveFileSearchResults(File file) {
        if(!searchResults.containsKey(file)) {
            searchResults.put(file, new ArrayList<>());
        }
        return searchResults.get(file);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<File, List<SearchResult>>> resultEntrySet = searchResults.entrySet();
        for(Map.Entry<File, List<SearchResult>> entry : resultEntrySet) {
            String fileName = entry.getKey().getName();
            builder.append("Search Results for : ").append(fileName);
            builder.append("\n");
            List<SearchResult> results = entry.getValue();
            if(results.size() > 0) {
                for(SearchResult result : results) {
                    builder.append(result);
                    builder.append("\n");
                }
            } else {
                builder.append("No Search Results");
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Prints out the error message.
     * @param error the error message
     */
    private static void printError(String error) {
        System.err.println(error);
        printInstruction();
        System.exit(1);
    }

    /**
     * Prints the instruction for the user how
     * to use this program.
     */
    private static void printInstruction() {
        System.out.println("java " + SearchPattern.class.getSimpleName() + " [-r] regex file/directory");
    }

    public static void main(String[] args) {
        boolean recursive = false;
        String search = null;
        boolean argumentsRead = false;
        int i = 0;
        while (!argumentsRead && i < args.length) {
            switch (args[i]) {
                case "-r":
                    recursive = true;
                    i++;
                    break;
                default:
                    argumentsRead = true;
                    break;
            }
        }
        //retrieves the regex argument
        search = retrieveArgument(args, i, null);
        if(search == null) {
            printError("No search expression");
        } else {
            i++;
        }
        //retrieves the path argument
        File file = new File(retrieveArgument(args, i, "."));
        if(!file.exists()) {
            printError(file + " doesn't exist!!!");
        }
        try {
            Pattern regexPattern = Pattern.compile(search);

            SearchPattern concurrentSearch = new SearchPattern(regexPattern, recursive);

            //accept the SearchPattern class as a FileVisitorAdapter
            FileSystem system = new FileSystem(file);
            system.accept(concurrentSearch);

            //ensures that the results only will be printed if all threads have done
            //their work
            for(SearchPatternFileThread thread : concurrentSearch.getCurrentStartedThreads()) {
                thread.join();
            }

            //prints the concurrent search results (via toString())
            System.out.println(concurrentSearch);

        } catch(RuntimeException e) {
            printError(e.getMessage());
        } catch(InterruptedException e) {
            printError("Internal Error: Threads interrupted!");
        }

    }

    /**
     * Retrieves an argument from the arguments array
     * @param arguments the arguments array
     * @param i the current index
     * @param defaultValue value that is returned if all arguments are being read
     * @return an argument or the default value
     */
    private static String retrieveArgument(String[] arguments, int i, String defaultValue) {
        if (i >= arguments.length) {
            return defaultValue;
        } else {
            return arguments[i++];
        }
    }
}
