
/**
 * Created by Adam on 20.05.2017.
 */
public class ListTest {

   public static void main(String[] args) {

      GenericList<String> list = new GenericList<String>();

      list.add("a");
      list.add("b");
      list.add("c");
      list.add("d");

      GenericList<String> clone = list.clone();

      cloneTest(list,clone);
   }

   public static void cloneTest(Object x, Object clone) {
      System.out.println("TEST1: x.clone() != x");
      if (clone != x) {
         System.out.println("Passed");
      } else {
         System.out.println("Failed");
      }
      System.out.println();

      System.out.println("TEST2 (not a requirement): x.clone.getClass() == x.getClass()");
      if (clone.getClass() == x.getClass()) {
         System.out.println("Passed");
      } else {
         System.out.println("Failed");
      }
      System.out.println();

      System.out.println("TEST3 (not a requirement): x.clone().equals(x)");
      if (clone.equals(x)){
         System.out.println("Passed");
      } else {
         System.out.println("Failed");
      }
      System.out.println();
   }
}
