package visitor;

import java.io.File;

/**
 * Created by Rene Sommerfeld on 08.06.2017.
 */
public class ListFileVisitor implements FileVisitor {

    /**
     * Defines the folder prefix
     */
    public static final String ROOT_FOLDER_PREFIX = "- ";

    /**
     * Defines the folder structure prefix that indicates the
     * structure line
     */
    public static final String FOLDER_STRUCTURE_PREFIX = "| ";

    /**
     * the root directory where to start the listing from
     */
    private File root;

    /**
     * defines whether or not the listing should include subdirectories
     */
    private boolean recurse;

    /**
     * string builder for spacing on console
     */
    private StringBuilder spacing;

    /**
     *
     * @param root the root directory
     * @param recurse whether or not the subdirectories should be listed
     */
    public ListFileVisitor(File root, boolean recurse) {
        this.root = root;
        this.recurse = recurse;
        spacing = new StringBuilder();
    }


    @Override
    public FileVisitor.Result onPreVisit(File current) {
        System.out.print(spacing);
        System.out.print(ROOT_FOLDER_PREFIX);
        System.out.println(current.getName());
        if(recurse || root.equals(current)) {
            spacing.append(FOLDER_STRUCTURE_PREFIX);
            return Result.NEXT;
        } else {
            return Result.IGNORE;
        }
    }

    @Override
    public FileVisitor.Result onPostVisit(File current) {
        //if the folder is visited the spacing for the folder structure get removed
        spacing.delete(spacing.length() - FOLDER_STRUCTURE_PREFIX.length(), spacing.length());
        return Result.NEXT;
    }

    @Override
    public FileVisitor.Result onVisitFailed(File current) {
        //print out if a visit fails
        System.out.println(spacing);
        System.out.println(current.getName() + " error!!!");
        return Result.NEXT;
    }

    @Override
    public FileVisitor.Result visit(File current) {
        //prints out the files from the directory
        System.out.print(spacing);
        System.out.println(current.getName());
        return Result.NEXT;
    }
}
