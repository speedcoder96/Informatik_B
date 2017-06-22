import java.io.File;
import java.util.*;

/**
 * Created by Rene Sommerfeld on 20.06.2017.
 * The FileSizeUpdateTimerTask class listens to a certain
 * root file object (whether a file or directory) and checks
 * in the defined period scheduled by a Timer object whether
 * or not the file object has changed in size.
 */
public class FileSizeUpdateTimerTask extends TimerTask {


    /**
     * defines the root file object on which file or
     * directory to listen to
     */
    private File rootFile;
    /**
     * defines the current size of the file or
     * directory
     */
    private long currentSize;

    /**
     * Takes the file object that represents the file
     * or directory to listen to
     * @param rootFile the root file object
     */
    public FileSizeUpdateTimerTask(File rootFile) {
        handleFileExistence(rootFile);
        currentSize = 0L;
    }

    /**
     * Handles the existence of the passed file object.
     * Sets the rootFile if exists, otherwise an IllegalArgumentException
     * is thrown.
     * @param file the file object
     */
    private void handleFileExistence(File file) {
        if(file == null) {
            //file shouldn't be null
            throw new IllegalArgumentException("file is null");
        } else {
            if(!file.exists()) {
                //file should exist
                throw new IllegalArgumentException("file doesn't exist");
            } else {
                this.rootFile = file;
            }
        }
    }

    @Override
    public void run() {
        //check if the file still exists
        if(!rootFile.exists()) {
            System.err.println(rootFile + " doesn't exist anymore!");
        } else {
            //calculates the size of the file or directory
            long size = calcSize(rootFile);
            //if the size has changed print the new size
            if(hasSizeChanged(size)) {
                System.out.println(rootFile.getName() + " is now " + Util.toUnitString(size));
            }
        }
    }

    /**
     * Calculates the size of the given root file object.
     * @param root the root file object
     * @return the size of the root file object
     */
    private long calcSize(File root) {
        if(root.isFile()) {
            return root.length();
        } else {
            long size = 0;
            File[] files = root.listFiles();
            if(files != null) {
                for(File file : files) {
                    size += calcSize(file);
                }
            }
            return size;
        }
    }

    /**
     * Sets the size if it has changed and returns
     * whether or not if it has.
     * @param now the new calculated size
     * @return whether or not the size has changed
     */
    private boolean hasSizeChanged(long now) {
        if(currentSize != now) {
            currentSize = now;
            return true;
        }
        return false;
    }

    /**
     * Utility class for creating a string of
     * a normalized size following by a fitting unit
     */
    private static final class Util {

        /**
         * SortedMap containing the unit factors and their unit string abbreviations
         */
        private static SortedMap<Long, String> UNIT_FACTORS = new TreeMap<>();

        /**
         * adds the unit strings and their unit factors
         */
        static {
            //bytes
            UNIT_FACTORS.put(1L, "B");
            //kilobytes
            UNIT_FACTORS.put(1000L, "KB");
            //megabytes
            UNIT_FACTORS.put(1000000L, "MB");
            //gigabytes
            UNIT_FACTORS.put(1000000000L, "GB");
            //terabytes
            UNIT_FACTORS.put(1000000000000L, "TB");
            //petabytes
            UNIT_FACTORS.put(1000000000000000L, "PB");
        }
        /**
         * Returns a string of the passed size followed
         * by the fitting unit with a precision of 3 digits.
         * @param size the size
         * @return unit string, otherwise a hyphen
         */
        public static String toUnitString(long size) {
            Set<Long> unitFactors = UNIT_FACTORS.keySet();
            for(Long unitFactor : unitFactors) {
                double normalizedSize = (double)size / (double)unitFactor;
                //normalizes the size to that one fitting unit is set
                //to the string
                if(normalizedSize >= 1 && normalizedSize < 1000) {
                    return String.format("%03.3f %s", normalizedSize, UNIT_FACTORS.get(unitFactor));
                }
            }
            return "-";
        }
    }



}
