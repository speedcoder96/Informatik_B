/**
 * Created by Rene Sommerfeld on 24.06.2017.
 * Basically represents a search result found in a file.
 * It contains the information of the line where the matches
 * were found, its line number and the amount of matches.
 */
public class SearchResult {

    private String matchingLine;
    private int lineNumber;
    private int matchCount;

    /**
     * Represents a search result found in a file
     * @param matchingLine the matching line
     * @param lineNumber the line number of the matching line
     * @param matchCount the number of matches
     */
    public SearchResult(String matchingLine, int lineNumber, int matchCount) {
        this.matchingLine = matchingLine;
        this.lineNumber = lineNumber;
        this.matchCount = matchCount;
    }

    /**
     * Returns the matching line where the matches were found
     * @return the matching line
     */
    public String getMatchingLine() {
        return matchingLine;
    }

    /**
     * Returns the line number of the {@code matchingLine}
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the number of matches of the {@code matchingLine}
     * @return number of matches
     */
    public int getMatchCount() {
        return matchCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(matchCount).append(")");
        builder.append(" #").append(lineNumber);
        builder.append(" -> ").append(matchingLine);
        return builder.toString();
    }
}
