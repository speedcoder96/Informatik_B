import java.util.Collection;

/**
 * Test class for Collection run times.
 *
 * @author tadam, rsommerfeld
 */
public class CollectionRunTime {

   private long start;
   private long end;
   private String[] a;
   private Collection[] c;


   public CollectionRunTime(Collection... collections) {
      c = collections;
      items();
   }

   private void items() {
      a = new String[10000];
      for (int i = 0; i < 10000; i++) {
         a[i] = Integer.toString(i);
      }
   }

   /**
    * Prints a table of all given Collections and their run time in nanoseconds
    * for the methods add, remove and contains.
    */
   public void printRunTime() {
      if (c == null) {
         throw new InternalError();
      }

      String[] classname = new String[c.length];

      for (int i = 0; i < classname.length; i++) {
         classname[i] = c[i].getClass().getSimpleName();
      }

      System.out.println("|  Collection   |   add   | remove  | contains |");
      System.out.println("|---------------|---------+---------+----------|");
      for (int i = 0; i < classname.length; i++) {
         System.out.printf("| %-14s|%8d |%8d |%9d | %n", classname[i], averageAddRunTime(c[i]), averageRemoveRunTime(c[i]), averageContainsRunTime(c[i]));
      }
   }

   private long averageAddRunTime(Collection c) {
      for (int i = 0; i < 10000; i++) {
         //JVM warmup
         if (i == 1000) {
            start = System.nanoTime();
         }
         c.add(a[i]);
      }
      end = System.nanoTime();
      return ((end - start) / 9000);
   }

   private long averageRemoveRunTime(Collection c) {
      for (int i = 0; i < 10000; i++) {
         //JVM warmup
         if (i == 1000) {
            start = System.nanoTime();
         }
         c.remove(a[i]);
      }
      end = System.nanoTime();
      return ((end - start) / 9000);
   }

   private long averageContainsRunTime(Collection c) {
      for (int i = 0; i < 10000; i++) {
         //JVM warmup
         if (i == 1000) {
            start = System.nanoTime();
         }
         c.contains(a[i]);
      }
      end = System.nanoTime();
      return ((end - start) / 9000);

   }


}
