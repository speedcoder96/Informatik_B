/**
 * Calculates in which area a tribute is
 * @author tadam
 * @author rsommerfeld
 */
public class Arena {
   private static final double ANGLE = Math.toRadians(30);
   private static final double RADIUS = 1.5;

   /**
    * Calculates the zone of a tribute whith his x and y coordinats
    * @param x x coordinate
    * @param y y coordinate
    * @return zone number from 0 to 11 clockwise
    */
   public static int getArea(double x, double y) {
      if (Math.sqrt(x * x + y * y) > RADIUS) {
         return -1;
      }
      double quadrantAngle = 0;
      if (x >= 0 && y < 0) {
         quadrantAngle = Math.PI;
      }
      if (x < 0 && y <= 0) {
         quadrantAngle = Math.PI;
      }
      if (x < 0 && y >= 0) {
         quadrantAngle = 2 * Math.PI;
      }
      double circularAngle = Math.atan(x / y) + quadrantAngle;

      return (int) (circularAngle / ANGLE) % 12;
   }
}
