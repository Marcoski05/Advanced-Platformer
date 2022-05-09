// Marco Cecchi-Rivas
// 4/10/21
// handles damage and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Hazard extends Rectangle {

   private Player p1; // reference
   private final int DAMAGE_PAUSE = 400; // Pause in millis before respawning

   Hazard(Player p1, int x, int y, int width, int height) {
      super(x, y, width, height, new Color(235, 107, 111));
      setLeft(x + 10);
      setRight(getRight() - 10);
      this.p1 = p1;
   }
   
   
   // Methods
   
   // checks if the player is colliding and respawns them if so
   public void damage() {
      if (isColliding()) { 
         try {
            Thread.sleep(DAMAGE_PAUSE); // Pauses everything for specified time
         } catch (InterruptedException e) {
         }
         p1.respawn();
      }
   }
   
   // for hazards in a wall, so they can be hit
   public void embeddedBounds() {
      setTop(getY() + 10);
      setBottom((getY() + getHeight()) - 10);
      setLeft(getX());
      setRight(getX() + getWidth());
   }
   
   // returns whether player is within its bounds
   private boolean isColliding() {
      if (p1.getRight()>getLeft() && p1.getLeft()<getRight() && p1.getTop()<getBottom() && p1.getBottom()>getTop())
         return true;
      else
         return false;
   }
   
   // toString
   public String toString() {
      return String.format("Hazard at (%d, %d), size [%d, %d], color %s", 
                            getX(), getY(), getWidth(), getHeight(), getColor());
   }
}