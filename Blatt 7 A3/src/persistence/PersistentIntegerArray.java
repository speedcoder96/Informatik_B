package persistence;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Rene Sommerfeld on 08.06.2017.
 */
public class PersistentIntegerArray implements AutoCloseable {

    /**
     * a file object that is able to random access the
     * one that is represented by it
     */
    private RandomAccessFile rndAccessFile;

    /**
     * Takes a {@code filePath} for storing the given {@code array} in
     * a file.
     * @param filePath the path to the file for storing the array elements
     * @param array the array containing the elements to store
     * @throws IOException if an IO error occurs
     */
    public PersistentIntegerArray(String filePath, Integer[] array) throws IOException {
        rndAccessFile = createRandomAccessFile(filePath, true);
        //makes sure that the length of the file is 0 before writing
        //the elements in the file
        rndAccessFile.setLength(0);
        for(Integer elem : array) {
            rndAccessFile.writeInt(elem);
        }
    }

    /**
     * Takes a {@code filePath} for loading a given array that is stored in
     * the file given by that path.
     * @param filePath the path to the file for loading the array
     * @throws IOException if an IO error occurs
     */
    public PersistentIntegerArray(String filePath) throws IOException {
        rndAccessFile = createRandomAccessFile(filePath, false);
    }

    /**
     * Creates the random access file object given by the {@code filePath}.
     * The file on the file system is only created if {@code createIfNotExist}
     * is set to true.
     * @param filePath the path to the file
     * @param createIfNotExist if the file doesn't exist and {@code createIfNotExist}
     *                         is set to true the file will be created
     * @return the random access file object to the given {@code filePath}
     * @throws IOException if an IO error occurs
     */
    private RandomAccessFile createRandomAccessFile(String filePath, boolean createIfNotExist) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            if(createIfNotExist) {
                file.createNewFile();
            } else {
                throw new FileNotFoundException(file + "does not exist on file system");
            }
        }
        return new RandomAccessFile(file, "rws");
    }


    /**
     * Returns the elements from the given index {@code i}
     * @param i the index where the element is located
     * @return the element at
     * @throws IOException if an IO error occurs
     */
    public Integer get(int i) throws IOException {
        if(seekPositionIfExist(i)) {
            return rndAccessFile.readInt();
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    /**
     * Sets a given element {@code integer} at a given index {@code i}
     * @param i the index where to set the element
     * @param integer the element to set
     * @throws IOException if an IO error occurs
     */
    public void set(int i, Integer integer) throws IOException {
        if(seekPositionIfExist(i)) {
            rndAccessFile.writeInt(integer);
            return;
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    /**
     * Returns the length of this array
     * @return the length
     * @throws IOException if an IO error occurs
     */
    public int length() throws IOException {
       return (int)(rndAccessFile.length() / Integer.BYTES);
    }

    /**
     * Seeks the position of index {@code i} in the random
     * access file object, if and only if the position exists.
     * Returns whether or not the seeking was successful.
     * @param i the index to seek
     * @return whether or not the seeking was successful
     * @throws IOException if an IO error occurs
     */
    private boolean seekPositionIfExist(int i) throws IOException {
        if(i >= 0 && i < length()) {
            rndAccessFile.seek(i * Integer.BYTES);
            return true;
        } else {
            return false;
        }
    }

    /**
     * In order to work with try-with-resource and to ensure that
     * the file is really closed after performing operations on it.
     * @throws IOException if an IO error occurs
     */
    @Override
    public void close() throws IOException {
        rndAccessFile.close();
    }
}
