import util.List;

/**
 * Created by Adam on 20.05.2017.
 */
public class GenericList<T> implements Cloneable{

   private List l;

   /**
    * Create a new empty List.
    */
   public GenericList() {
      l = new List();
   }

   /**
    * Determines if this List is empty or not.
    *
    * @return <code>true</code>, if there are no elements in this List
    */
   public boolean empty() {
      return l.empty();
   }

   /**
    * Determines if it is possible to {@link #advance()} in this List. Returns
    * <code>true</code> if the last position of this List has been reached. An
    * {@link #empty()} List will alway deliver <code>true</code>
    *
    * @return <code>true</code> if the last Entry in this List already has been
    *         reached.
    */
   public boolean endpos() {
      return l.endpos();
   }

   /**
    * Returns to the beginning of this List.
    */
   public void reset() {
      l.reset();
   }

   /**
    * Advances one step in this List.
    *
    * @throws RuntimeExcpetion
    *            if the last Entry of this List already has been reached.
    */
   public void advance() {
      l.advance();
   }

   /**
    * Returns the actual element of this List.
    *
    * @return the actual element
    *
    * @throws RuntimeException
    *            if the last Entry of this List already has been reached.
    */
   public T elem() {
      return (T) l.elem();
   }

   /**
    * Inserts <code>o</code> in this List. It will be placed before the actual
    * element. After insertion the inserted element will become the actual
    * element.
    *
    * @param x
    *           the element to be inserted
    */
   public void add(T x) {
      l.add(x);
   }

   /**
    * Deletes the actual element of this List. The element after the actual
    * element will become the new actual element.
    *
    * @throws RuntimeExcpetion
    *            if the last Entry of this List already has been reached.
    */
   public void delete() {
      l.delete();
   }

   public GenericList clone(){
      try {
         return (GenericList) super.clone();
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
         return null;
      }

   }
}


