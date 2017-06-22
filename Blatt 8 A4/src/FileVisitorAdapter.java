import java.io.File;
/**
 * Adapter class for the FileVistor.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * @author tadam
 * @author rsommerfeld
 * 
 */
public class FileVisitorAdapter implements FileVisitor {

   private boolean recursiv;
   private File root;
   private String search;
   private String replacement;

   public FileVisitorAdapter(boolean recursive, String search, String replacement, File root){
      this.recursiv = recursive;
      this.root = root;
      this.search = search;
      this.replacement = replacement;
   }

   @Override
   public FileVisitResult postVisitDirectory(File dir) {
      return FileVisitResult.CONTINUE;
   }

   @Override
   public FileVisitResult preVisitDirectory(File dir) {
      if(recursiv || this.root.equals(dir)){
         return FileVisitResult.CONTINUE;
      }
      return FileVisitResult.SKIP_SUBTREE;
   }

   @Override
   public FileVisitResult visitFailed(File file) {
      return FileVisitResult.CONTINUE;
   }

   @Override
   public FileVisitResult visitFile(File file) {
      SearchAndReplace.changeFile(search,replacement,file);
      return FileVisitResult.CONTINUE;
   }

}
