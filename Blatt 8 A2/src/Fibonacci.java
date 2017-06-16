import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Fibonacci {

   /**
    * the hashmap containing the fibonacci numbers
    */
   private static HashMap<Integer, Long> fibonacciHash;

   /**
    * Name of the serializable file
    */
   private static final String SERIALIZABLE_FILE_NAME = "fib.ser";

   /**
    * FileResult is a flag to indicate whether
    * or not the file exists and secondly
    * has a reference to that file if it does
    * exist
    */
   private static enum FileResult {
      EXISTS, NOT_EXISTS;

      private File file;
      private FileResult() {

      }
      public void setFile(File file) {
         this.file = file;
      }

      public File getFile() {
         return file;
      }
   }

   /*

   /**
    * Calculates the fibonacci value of n.
    * 
    * @param n
    *           a natural number >= 0 to calculate the fibonacci value of
    * 
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) throws IOException, ClassNotFoundException {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }

      if(fibonacciHash == null) {
         FileResult result = createFile(false);
         switch(result) {
            case EXISTS:
               //loads the hashmap from the file
               fibonacciHash = loadFile(result.getFile());
               break;
            case NOT_EXISTS:
               //creates the hashmap if the file doesn't exist
               fibonacciHash = new HashMap<>();
               fibonacciHash.put(0, 0L);
               fibonacciHash.put(1, 1L);
               break;
         }
      }

      int currentSize = fibonacciHash.size();
      long fibN = getFibonacci(n);
      //defines whether or not the hashmap has changed in size
      // after calling the getFibonacci method
      boolean hasChanged = currentSize != fibonacciHash.size();

      //if has changed write the hashmap to the file
      if(hasChanged) {
         FileResult result = createFile(true);
         writeFile(result.getFile());
      }

      //returns the n-th fibonacci number
      return fibN;
   }

   /**
    * Creates the file if the flag is set to true. Returns
    * the result as a FileResult enum object to indicate
    * whether or not the file exists. The file itself is
    * contained in the FileResult enum object if the method
    * results in FileResult.EXISTS
    * @param createIfNotExist if the file should be created
    * @return whether or not the file exists, indicated by a FileResult
    * @throws IOException if an IO error occurs
    */
   private static FileResult createFile(boolean createIfNotExist) throws IOException {
      File file = new File(SERIALIZABLE_FILE_NAME);
      FileResult result;
      if(!file.exists()) {
         if(createIfNotExist) {
            file.createNewFile();
            result = FileResult.EXISTS;
            result.setFile(file);
            return result;
         } else {
            result = FileResult.NOT_EXISTS;
            return result;
         }
      }
      result = FileResult.EXISTS;
      result.setFile(file);
      return result;
   }

   /**
    * Loads the hashmap from file.
    * @param file the file to load the hashmap from
    * @return the hashmap read from the file
    * @throws IOException if an IO error occurs
    * @throws ClassNotFoundException if the class according to the serialized version doesn't exists
    */
   private static HashMap<Integer, Long> loadFile(File file) throws IOException, ClassNotFoundException {
      try(ObjectInputStream oinStream = new ObjectInputStream(new FileInputStream(file))) {
         return (HashMap<Integer, Long>) oinStream.readObject();
      } catch(ClassNotFoundException cnfe) {
         throw cnfe;
      } catch(IOException ioe) {
         throw ioe;
      }
   }

   /**
    * Writes the hashmap to the file
    * @param file the file to write the hashmap to
    * @throws IOException if an IO error occurs
    */
   private static void writeFile(File file) throws IOException {
      try(ObjectOutputStream ooutStream = new ObjectOutputStream(new FileOutputStream(file))) {
         ooutStream.writeObject(fibonacciHash);
      } catch(IOException ioe) {
         throw ioe;
      }
   }

   /**
    * Returns the n-th fibonacci number.
    * @param n the index of the fibonacci number
    * @return the n-th fibonacci number
    */
   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   public static void main(String[] args) {
      if (args.length != 1) {
         printUsage();
      }
      try {
         //parses the first argument passed in program arguments
         System.out.println(fibonacci(Integer.parseInt(args[0])));
      } catch (IllegalArgumentException ex) {
         printUsage();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   private static void printUsage() {
      System.out.println("java calc/Fiboncci n");
      System.out.println("n must be a natural number >= 0");
   }

}
