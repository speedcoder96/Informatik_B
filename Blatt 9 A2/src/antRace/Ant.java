package antRace;

import antRace.AntField.Field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Ant implements Runnable {

   private static int antcount = 0;

   private int x;
   private int y;
   private AntField fields;
   private int stepCount;


   /**
    * @param fields    the {@code AntField} on which this {@code Ant} operates
    * @param x         x-axis value of the starting position
    * @param y         y-axis value of the starting position
    * @param stepCount initial stepCount of this {@code Ant}.
    * @throws IllegalArgumentException If the {@code Field} at position {@code x,y} does not exist, or
    *                                  if its value is < 0
    */
   public Ant(AntField fields, int x, int y, int stepCount) {
      antcount++;
      this.fields = fields;
      this.x = x;
      this.y = y;
      this.stepCount = stepCount;
   }


   public void run() {
      //setzt die amaise auf ihren startfeld
      fields.getField(x, y).setValue(stepCount);
      //alle wege die gegangen werden können im stack
      Stack<Way> paths = AllValidPaths(x,y);
      if(paths.empty()){
         //done
      } else {
         //den ersten weg geht die ameise selbst
         Way first = paths.peek();
         paths.pop();
         int oldX = x;
         int oldY = y;
         int oldStepCount = stepCount;
         moveAnt(first);
         // weg auf feld markieren
         fields.getField(x, y).setValue(stepCount);

         //für alle anderen wege die gangen werden können wird eine neue ameise erzeugt
         for (Way w : paths) {
            int newX = oldX + cooridates(w)[0];
            int newY = oldY + cooridates(w)[1];
            int newStepCount = oldStepCount + 1;

            Ant newAnt = new Ant(fields,newX,newY,newStepCount);

            //new Thread(newAnt).start();
            //threads funktionieren noch nicht
            newAnt.run();
         }
      }

   }

   //gibt den wert eines feldes zurück wenn das feld nicht begangen werden kann wird -1 zurückgegeben
   private int vicinity(int x, int y) {
      if (fields.getField(x, y) != null) {
         return fields.getField(x, y).getValue();
      } else return -1;
   }

   private Stack<Way> AllValidPaths(int x, int y) {
      int[] v = new int[8];
      //top
      v[0] = vicinity(x - 1, y);
      //bottom
      v[1] = vicinity(x + 1, y);
      //left
      v[2] = vicinity(x, y - 1);
      //right
      v[3] = vicinity(x, y + 1);
      //topleft
      v[4] = vicinity(x - 1, y - 1);
      //topright
      v[5] = vicinity(x - 1, y + 1);
      //bottomleft
      v[6] = vicinity(x + 1, y - 1);
      //bottomright
      v[7] = vicinity(x + 1, y + 1);

      // Ist ein Feld als frei gekennzeichnet
      // oder die dort eingetragene Schrittzahl größer als die Schrittzahl der Ameise plus eins,
      // hat die Ameise einen neuen Weg gefunden
      // Alle diese wege werden im stack gespeichert
      Stack<Way> paths = new Stack();
      for(int i = 0; i < 8; i++){
         if(v[i] != -1 ){
            if(v[i] == AntField.FREE || v[i] > stepCount + 1){
               //coordiantes übersetzt den array index zu einem weg
               paths.push(cooridates(i));
            }
         }
      }
      return paths;
   }

   private int[] cooridates(Way way) {
      switch (way) {
         case TOP:
            return new int[]{-1, 0};
         case BOT:
            return new int[]{1, 0};
         case LEFT:
            return new int[]{0, -1};
         case RIGHT:
            return new int[]{0, 1};
         case TOPLEFT:
            return new int[]{-1, -1};
         case TOPRIGHT:
            return new int[]{-1, 1};
         case BOTLEFT:
            return new int[]{1, -1};
         case BOTRIGHT:
            return new int[]{1, 1};
         default:
            return new int[]{0, 0};
      }
   }


   private Way cooridates(int way) {
      switch (way) {
         case 0:
            return Way.TOP;
         case 1:
            return Way.BOT;
         case 2:
            return Way.LEFT;
         case 3:
            return Way.RIGHT;
         case 4:
            return Way.TOPLEFT;
         case 5:
            return Way.TOPRIGHT;
         case 6:
            return Way.BOTLEFT;
         case 7:
            return Way.BOTRIGHT;
         default:
            return Way.CENTER;
      }
   }

   // bewegt die ameise in die jeweilige richtung
   private void moveAnt(Way way) {
      switch (way) {
         case TOP:
            x--;
            stepCount++;
            break;
         case BOT:
            x++;
            stepCount++;
            break;
         case LEFT:
            y--;
            stepCount++;
            break;
         case RIGHT:
            y++;
            stepCount++;
            break;
         case TOPLEFT:
            x--;
            y--;
            stepCount++;
            break;
         case TOPRIGHT:
            x--;
            y++;
            stepCount++;
            break;
         case BOTLEFT:
            x++;
            y--;
            stepCount++;
            break;
         case BOTRIGHT:
            x++;
            y++;
            stepCount++;
            break;
         default:
      }
   }

}
