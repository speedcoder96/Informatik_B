package util;

import java.util.Collection;

/**
 * Created by Adam on 08.06.2017.
 */
public class VisitableTest {

   public static void main(String[] args) {
      MyList<String> l = new MyList<>();
      l.add("1");
      l.add("2");
      l.add("3");
      l.add("4");
      testVisitable(l, 4);
   }

   /**
    * Tests if all elements of a visitable object can be visited
    * @param v object that implements the visitable interface
    * @param elements amount of elements in the visitable object
    */
   public static void testVisitable(Visitable v, int elements) {
      TestingVisitor visitor = new TestingVisitor();
      v.accept(visitor);
      //every element visited
      if ((elements - visitor.getAmountVisited()) == 0) {
         System.out.println("All elements visited.");
      } else {
         System.err.println("Not all elements could be visited.");
      }
   }

}

/**
 * Simple visitor that counts how many elements have been visited
 */
class TestingVisitor implements Visitor {

   private int elements = 0;

   @Override
   public boolean visit(Object o) {
      elements++;
      return true;
   }

   public int getAmountVisited() {
      return elements;
   }
}

