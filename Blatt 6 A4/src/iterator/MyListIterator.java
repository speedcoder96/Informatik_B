package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Rene Sommerfeld on 24.05.2017.
 */
public class MyListIterator<E> implements Iterator<E> {

    /**
     * References the list for iteration
     */
    private MyList<E> list;
    /**
     * Defines the modification count of the list
     * when the iterator was created
     */
    private int prevModCount;


    public MyListIterator(MyList<E> list) {
        this.list = list;
        prevModCount = list.getModCount();
    }

    /**
     * Returns whether or not the list has a next element
     * @return true if the list has a next element, otherwise false
     */
    public boolean hasNext() {
        if(!list.endpos()) {
            return true;
        }
        return false;
    }

    /**
     * Returns the next element of this list.
     * @throws ConcurrentModificationException if the list is modified while the
     * iterator is already created
     * @throws NoSuchElementException if the list has no next element
     * @return the next element if it exists
     */
    public E next() {
        if(list.isModified(prevModCount)) {
           throw new ConcurrentModificationException();
        }
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        E temp = list.elem();
        list.advance();
        return temp;
    }

    /**
     * Removes the current selected element by this
     * iterator from list.
     * @throws NoSuchElementException if the list has no next element
     */
    public void remove() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        list.delete();
        prevModCount = list.getModCount();
    }
}
