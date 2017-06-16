import java.io.*;

/**
 * Created by Rene Sommerfeld on 16.06.2017.
 */
public class HeapTest {

    /**
     * the name of the serializable file
     */
    private static final String SERIALIZABLE_FILE_NAME = "SerializedHeap.ser";

    public static void main(String[] args) {

        //builds the original heap
        int[] numbersForHeap = {3, 6, 9, 12, 15, 18};
        Heap<Integer> originalHeap = new Heap<Integer>();
        for(int number : numbersForHeap) {
            originalHeap.insert(number);
        }


        //writes the heap to the file
        writeHeap(originalHeap, SERIALIZABLE_FILE_NAME);

        //reads the heap from the file
        Heap<Integer> serializedHeap = readHeap(SERIALIZABLE_FILE_NAME);


        //creates a new test unit
        Assert.create();
        //names the current created test unit
        Assert.name("Equals Original Heap After Deserialization");
        //equals method - the heap is providing the iterable interface to get compared with
        //the equals method of the Assertion class
        Assert.Generic.assertEquals(originalHeap, serializedHeap, originalHeap.size(), serializedHeap.size());

        //prints the result
        Assert.results();

    }

    /**
     * Writes the heap to the specified file.
     * @param heap the heap
     * @param fileName the name of the file
     * @param <E> the type of the elements of the heap
     */
    private static <E> void writeHeap(Heap<E> heap, String fileName) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(createFile(fileName)))){
            stream.writeObject(heap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the heap from the specified file
     * @param <E> the type of the elements of the heap
     * @param fileName the name of the file
     * @return
     */
    private static <E> Heap<E> readHeap(String fileName) {
        Heap<E> heap = null;
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(createFile(fileName)))) {
            heap = (Heap<E>) stream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return heap;
    }

    /**
     * Creates the file if it doesn't exist and returns the
     * file object.
     * @param name the name of the file
     * @return the file object
     * @throws IOException if an IO error occurs
     */
    private static File createFile(String name) throws IOException {
        File file = new File(name);
        if(!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

}
