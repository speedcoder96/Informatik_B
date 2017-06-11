package visitor;

import java.io.File;

/**
 * Created by Rene Sommerfeld on 06.06.2017.
 */
public interface FileVisitor {

    /**
     * Representing the results of the FileVisitor methods.
     * If {@link FileVisitor.Result#NEXT} is returned the
     * file system continues walking over files.
     * If {@link FileVisitor.Result#IGNORE} is returned the
     * file system will ignore the directory and its subdirectories.
     * If {@link FileVisitor.Result#EXIT} is returned the
     * file system will stop walking over files.
     */
    enum Result {
        NEXT, IGNORE, EXIT
    }

    /**
     * Gets called before visiting a file or directory
     * of the file system.
     * @param current the current file or directory represented by
     *                this file object
     * @return the result of how the file system should
     * proceed walking over the files
     */
    public FileVisitor.Result onPreVisit(File current);

    /**
     * Gets called after visiting a file or directory of
     * the file system.
     * @param current the current file or directory represented by
     *                this file object
     * @return the result of how the file system should
     * proceed walking over the files
     */
    public FileVisitor.Result onPostVisit(File current);

    /**
     * Gets called if a visit has failed
     * @param current the current file or directory represented by
     *                this file object
*    * @return the result of how the file system should
     * proceed walking over the files
     */
    public FileVisitor.Result onVisitFailed(File current);

    /**
     * Gets called while visiting a file.
     * @param current the current file represented by
     *                this file object
     * @return the result of how the file system should
     * proceed walking over the files
     */
    public FileVisitor.Result visit(File current);

}
