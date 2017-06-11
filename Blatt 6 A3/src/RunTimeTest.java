import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class RunTimeTest {

   public static void main(String[] args) {
      LinkedList llist = new LinkedList();
      ArrayList alist = new ArrayList();
      HashSet hset = new HashSet();

      CollectionRunTime a = new CollectionRunTime(alist,hset,llist);
      a.printRunTime();

   }
}
