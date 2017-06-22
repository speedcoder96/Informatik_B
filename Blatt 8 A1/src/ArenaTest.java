/**
 * @author tadam
 * @author rsommerfeld
 */
public class ArenaTest {

   public static void main(String[] args) {
      Assert.create();
      Assert.name("getArea(0,0)");
      Assert.Equals.assertEquals(Arena.getArea(0,0),0);

      Assert.create();
      Assert.name("getArea(100,100) (out of bounds)");
      Assert.Equals.assertEquals(Arena.getArea(100,100), -1);

      Assert.create();
      Assert.name("Border: getArea(0,1.5) inside Area?");
      Assert.Bool.assertFalse(Arena.getArea(0,1.5) == -1);

      //0 coordinates
      Assert.create();
      Assert.name("Border 2/3: getArea(1,0)");
      Assert.Equals.assertEquals(Arena.getArea(1,0),3);

      Assert.create();
      Assert.name("Border 8/9: getArea(-1,0)");
      Assert.Equals.assertEquals(Arena.getArea(-1,0), 9);

      Assert.create();
      Assert.name("Border 11/0: getArea(0,1)");
      Assert.Equals.assertEquals(Arena.getArea(0,1),0);

      Assert.create();
      Assert.name("Border 5/6: getArea(0,-1)");
      Assert.Equals.assertEquals(Arena.getArea(0,-1),6);

      //All areas
      Assert.create();
      Assert.name("Area 0: getArea(0.5,1)");
      Assert.Equals.assertEquals(Arena.getArea(0.5,1),0);

      Assert.create();
      Assert.name("Area 1: getArea(1,1)");
      Assert.Equals.assertEquals(Arena.getArea(1,1),1);

      Assert.create();
      Assert.name("Area 2: getArea(1,0.5)");
      Assert.Equals.assertEquals(Arena.getArea(1,0.5),2);

      Assert.create();
      Assert.name("Area 3: getArea(1,-0.5)");
      Assert.Equals.assertEquals(Arena.getArea(1,-0.5),3);

      Assert.create();
      Assert.name("Area 4: getArea(1,-1)");
      Assert.Equals.assertEquals(Arena.getArea(1,-1),4);

      Assert.create();
      Assert.name("Area 5: getArea(0.5,-1)");
      Assert.Equals.assertEquals(Arena.getArea(0.5,-1),5);

      Assert.create();
      Assert.name("Area 6: getArea(-0.5,-1)");
      Assert.Equals.assertEquals(Arena.getArea(-0.5,-1),6);

      Assert.create();
      Assert.name("Area 7: getArea(-1,-1)");
      Assert.Equals.assertEquals(Arena.getArea(-1,-1),7);

      Assert.create();
      Assert.name("Area 8: getArea(-1,-0.5)");
      Assert.Equals.assertEquals(Arena.getArea(-1,-0.5),8);

      Assert.create();
      Assert.name("Area 9: getArea(-1,0.5)");
      Assert.Equals.assertEquals(Arena.getArea(-1,0.5),9);

      Assert.create();
      Assert.name("Area 10: getArea(-1,1)");
      Assert.Equals.assertEquals(Arena.getArea(-1,1),10);

      Assert.create();
      Assert.name("Area 11: getArea(-0.5,1)");
      Assert.Equals.assertEquals(Arena.getArea(-0.5,1),11);


      Assert.results();

   }
}
