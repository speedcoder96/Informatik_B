package iterator;


import test.core.Evaluator;
import test.values.ClassObjectAssertion;
import test.values.IntAssertion;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Rene Sommerfeld on 24.05.2017.
 */
public class MyListTest {

    public static void main(String[] args) {
        testRunThroughIterator();
        testRunThroughIteratorWhileAdd();
        testRunThroughIteratorWhileRemove();
        testRemovingElementWhenNoNext();
        Evaluator.getInstance().printSummary();
    }

    private static void testRunThroughIterator() {
        MyList<String> list = new MyList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        Iterator<String> iterator = list.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            iterator.next();
            count++;
        }
        Evaluator.getInstance().eval(IntAssertion.create("Iterator Should Iterate 5 Elements", 5, count));

    }

    private static void testRunThroughIteratorWhileAdd() {
        MyList<String> list = new MyList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        try {
            Iterator<String> iterator = list.iterator();
            //list gets modified, so iterator should be fast failing
            //when iterating
            list.add("F");
            while(iterator.hasNext()) {
                iterator.next();
            }
        } catch(Exception e) {
            //if an exception get caught the class object should be ConcurrentModificationException
            Evaluator.getInstance().eval(ClassObjectAssertion.create("CME should be thrown",
                    ConcurrentModificationException.class, e.getClass()));
        }


    }

    private static void testRunThroughIteratorWhileRemove() {
        MyList<String> list = new MyList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        try {
            Iterator<String> iterator = list.iterator();
            //list gets modified, so iterator should be fast failing
            //when iterating
            list.delete();
            while(iterator.hasNext()) {
                iterator.next();
            }
        } catch(Exception e) {
            //if an exception get caught the class object should be ConcurrentModificationException
            Evaluator.getInstance().eval(ClassObjectAssertion.create("CME should be thrown",
                    ConcurrentModificationException.class, e.getClass()));
        }

    }

    private static void testRemovingElementWhenNoNext() {
        MyList<String> list = new MyList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        try {
            Iterator<String> iterator = list.iterator();
            while(iterator.hasNext()) {
                iterator.next();
            }
            //exception should be thrown, because remove is called when iterator is already
            //done iterating over entire list
            iterator.remove();
        } catch(Exception e) {
            //if an exception get caught the class object should be ConcurrentModificationException
            Evaluator.getInstance().eval(ClassObjectAssertion.create("NSEE should be thrown",
                    NoSuchElementException.class, e.getClass()));
        }
    }


}
