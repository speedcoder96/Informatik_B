import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Adam on 10.06.2017.
 */
public class SearchLines {

   public static void main(String[] args) {
      if(!checkInput(args,System.in)) System.exit(0);

      String regex = args[0];
      BufferedMatchingReader text = new BufferedMatchingReader(new InputStreamReader(System.in), regex);

      String line = matchingLine(text);
      while (line != null) {
         int count = text.getAmountOfMatches();
         int lineNumber = text.getLineNumber();
         if (count != 0) {
            System.out.println("Found " + count + " match(es) in line " + lineNumber + ": " + line);
         }
         line = matchingLine(text);
      }


   }

   // try catch abkuerzung
   private static String matchingLine(BufferedMatchingReader text) {
      String line = null;
      try {
         line = text.readLine();
      } catch (IOException e) {
         System.err.println("I/O Exception");
      }
      return line;

   }

   private static boolean checkInput(String[] args, InputStream in) {
      // nur ein argument
      if (args.length != 1) {
         System.err.println("invalid input: input must be in this format: java SearchLines \"regex\" < Example.txt");
         return false;
      }

      // ueberprueft ob eine datei uebergeben wurde
      int available = 0;
      try {
         available = in.available();
      } catch (IOException e) {
         System.err.println("I/O Exception");
      }
      if (available == 0) {
         System.err.println("invalid input: no file to search found");
         return false;
      }
      return true;
   }
}
