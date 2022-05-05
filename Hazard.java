// Marco Cecchi-Rivas
// 4/10/21
// handles damage and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Hazard {

   private int x, y; // position of top left corner
   private int width, height;
   private static Color color;
   private int right, left, top, bottom; // for collision
   private Player p1; // reference
   private final int DAMAGE_PAUSE = 400; // Pause in millis before respawning

   Hazard(Player p1, int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      top = y;
      bottom = y + height;
      left = x + 10;
      right = (x + width) - 10;
      color = new Color(235, 107, 111);
      this.p1 = p1;
   }
   
   // Setters
   
   public static void setColor(Color c) {
      color = c;
   }
   
   // Methods
   
   public void drawMe(Graphics g) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
   }
   
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
      top = y + 10;
      bottom = (y + height) - 10;
      left = x;
      right = x + width;
   }
   
   // returns if player is within its bounds
   private boolean isColliding() {
      if (p1.getRight()>this.left && p1.getLeft()<this.right && p1.getTop()<this.bottom && p1.getBottom()>this.top) {
         return true;
      }
      else {
         return false;
      }
   }
   
   // toString
   public String toString() {
      return String.format("Hazard at (%d, %d), size [%d, %d], color %s", 
                            x, y, width, height, color);
   }
}