import java.io.*;

/**
 * Console Application that replaces a string in a file
 * or directory with another given string.
 *
 * @author tadam
 * @author rsommerfeld
 */
public class SearchAndReplace {


   private static final String NEWLINE = System.getProperty("line.separator");

   public static void main(String[] args) {
      boolean recursive = false;
      String search = "";
      String replacement = "";
      File f = null;

      if(args.length < 3 || args.length > 4){
         inputError();
      }

      if(args[0].equals("-r") && args.length == 4){
         recursive = true;
         search = args[1];
         replacement = args[2];
         f = new File(args[3]);
      } else if(args.length == 3) {
         if(args.length > 3) System.exit(1);
         search = args[0];
         replacement = args[1];
         f = new File(args[2]);
      } else{
         inputError();
      }

      FileSystem fsys = new FileSystem(f);
      FileVisitorAdapter visitor = new FileVisitorAdapter(recursive,search,replacement,f);
      fsys.accept(visitor);

   }

   /**
    * Searches a file for a string and replaces it with another given string.
    * @param search String to search for
    * @param replacement Replacement string
    * @param file File containing the string
    */
   public static void changeFile(String search, String replacement, File file){
      String fileString = FileToString(file);
      String fileReplacement = fileString.replaceAll(search,replacement);
      try (FileWriter fw = new FileWriter(file)) {
         fw.write(fileReplacement);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static String FileToString(File file){
      BufferedReader buffread = null;
      try {
         buffread = new BufferedReader(new FileReader(file));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      String result = "";
      String line = "";
      while (line != null) {
         line = getLine(buffread);
         if(line != null) {
            result += line;
            result += NEWLINE;
         }
      }
      return result;

   }

   private static String getLine(BufferedReader text) {
      String line = null;
      try {
         line = text.readLine();
      } catch (IOException e) {
         System.err.println("I/O Exception");
      }
      return line;
   }

   private static void inputError(){
      System.err.println("ERROR: Input must be in this format: [-r] Search Replacement File-/Directory-path");
      System.exit(1);
   }
}