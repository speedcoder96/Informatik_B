package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 16.05.2017.
 */

/**
 *  Person Student HyperStudent
 *  Heap<Person>
 *  Comparator<Student>
 *  Comparator<HyperStudent>
 *
 *
 *
 * @param <T> generic type T
 */
public class Heap<T> {

    /**
     * Comparator for comparing elements of
     * the heap. Set with a lower bound, such
     * that only the type T or its super classes
     * can be compared
     */
    private Comparator<? super T> comparator;
    /**
     * Holds the elements of the heap
     */
    private List<T> elements;

    /**
     * Creates a heap with a specific comparator
     * for comparing the elements of the heap
     * @param comparator the comparator for comparing the
     *                   elements
     */
    public Heap(Comparator<? super T> comparator) {
        this.comparator = comparator;
        elements = new ArrayList<T>();
    }

    /**
     * Creates a heap. The elements will be compared by
     * compareTo method of the Comparable interface
     */
    public Heap() {
        this(null);
    }

    /**
     * Inserts an element into the heap
     * @param element the element to insert
     */
    public void insert(T element) {
        List<T> temp = new ArrayList<>();
        //put the element to insert into the root
        temp.add(element);
        //add all elements from the old list to new list
        temp.addAll(elements);
        elements = temp;
        //reorganize heap so that every property of a heap
        //is fulfilled
        reorganize();
    }

    /**
     * Returns whether or not the heap is empty
     * @return true if the heap is empty, otherwise false
     */
    public boolean empty() {
        return elements.isEmpty();
    }

    /**
     * Deletes and returns the minimum element of the heap
     * @return returns the minimum element of the heap
     */
    public T deleteFirst() {
        if(elements.size() > 1) {
            reorganize();
        }
        return elements.remove(0);
    }

    /**
     * Compares two elements either by using the comparator
     * or the compareTo method of <code>Comparable</code> interface
     * @param element the element
     * @param other the other element to compare the element with
     * @return the specified values of compare or compareTo for comparing
     *         two elements
     */
    private int compare(T element, T other) {
        if(comparator != null) {
            return comparator.compare(element, other);
        } else {
            Comparable<T> comparableElement = (Comparable<T>)element;
            T comparableOther = (T)other;
            return comparableElement.compareTo(comparableOther);
        }
    }

    public T getMin() {
        return elements.get(0);
    }

    /**
     * Reorganizes the heap such that every property of a heap
     * is fulfilled.
     */
    private void reorganize() {
        int left = 0;
        int right = elements.size() - 1;
        int i = left;
        int j = 2 * i + 1;
        T temp = elements.get(left);
        if ((j < right) && (compare(elements.get(j + 1), elements.get(j)) < 1)) {
            j++;
        }
        while ((j <= right) && (compare(elements.get(j), temp) < 1)) {
            elements.set(i, elements.get(j));
            i = j;
            j = 2 * i + 1;
            if ((j < right) && (compare(elements.get(j + 1), elements.get(j)) < 1)) {
                j++;
            }
        }
        elements.set(i, temp);
    }


}
