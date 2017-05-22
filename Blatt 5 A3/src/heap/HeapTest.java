package heap;

/**
 * Created by Rene Sommerfeld on 21.05.2017.
 */
public class HeapTest {

    public static void main(String[] args) {
        Heap<String> heap = new Heap<String>();
        String[] elements = {"c","d","a","e","b","g","f"};
        String[] sorted;
        printArray(elements);
        sorted = HeapSort.heapSort(heap, elements);
        printArray(sorted);
    }

    public static void printArray(Object[] a) {
        for (Object b : a) {
            System.out.print(b + ", ");
        }
        System.out.println();
    }
}
