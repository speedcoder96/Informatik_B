import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Reader implementation that can find the amount of matches of a regular expression
 * @author tadam
 * @author rsommerfeld
 */
public class BufferedMatchingReader extends LineNumberReader {

   private Pattern pattern;
   private String line;

   public BufferedMatchingReader(Reader in, String regex) {
      super(in);
      pattern = Pattern.compile(regex);
   }

   @Override
   public String readLine() throws IOException {
      line = super.readLine();
      return line;
   }



   /**
    * Returns the number of matches in the last read line of the given regular expression
    *
    * @return int amount of matches in last read line
    */
   public int getAmountOfMatches() {
      Matcher matcher = pattern.matcher(line);

      int amount = 0;
      while (matcher.find()) {
         amount++;
      }
      return amount;
   }
}
