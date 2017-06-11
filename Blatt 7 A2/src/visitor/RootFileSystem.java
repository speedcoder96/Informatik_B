package visitor;

import java.io.File;

/**
 * Created by Rene Sommerfeld on 06.06.2017.
 */
public class RootFileSystem {

    private File rootFile;

    /**
     * Creates a root file system with the specified rootFile.
     * @param rootFile
     */
    public RootFileSystem(File rootFile) {
        setupRoot(rootFile);
    }

    /**
     * Setups the rootFile which represents the root directory where to
     * do the listing on.
     * @param rootFile the root directory
     */
    private void setupRoot(File rootFile) {
        if(rootFile != null && rootFile.exists()) {
            this.rootFile = rootFile;
        } else if(rootFile == null) {
            throw new NullPointerException("root file is null");
        } else if(!rootFile.exists()) {
            throw new IllegalArgumentException(rootFile + " does not exist on file system");
        }
    }

    /**
     * Accepts the file visitor.
     * @param visitor the visitor
     */
    public void accept(FileVisitor visitor) {
        recurse(rootFile, visitor);
    }

    /**
     * Recursively visits the directories and calls the different methods
     * of the passed file visitor.
     * @param current the current directory
     * @param visitor the file visitor
     * @return the instruction to the file system
     */
    private FileVisitor.Result recurse(File current, FileVisitor visitor) {
        //if file or visitor is null
        if(current == null || visitor == null) {
            return FileVisitor.Result.EXIT;
        }
        //if file cant be read
        if(!current.canRead()) {
            return FileVisitor.Result.EXIT;
        }

        if(current.isFile()) {
            return visitor.visit(current);
        } else {
            //pre visit
            FileVisitor.Result result = visitor.onPreVisit(current);
            switch(result) {
                case NEXT:
                    File[] files = current.listFiles();
                    if(files != null) {
                        for(File file : files) {
                            //recursively visit subdirectories and files
                            FileVisitor.Result subResult = recurse(file, visitor);
                            if(subResult == FileVisitor.Result.EXIT) {
                                return FileVisitor.Result.EXIT;
                            }
                        }
                        //post visit
                        result = visitor.onPostVisit(current);
                    }
                    break;
            }
            return result;
        }
    }

}
