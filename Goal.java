// Marco Cecchi-Rivas
// 4/10/21
// handles damage and graphics

import java.awt.Graphics;

public class Goal {

   private int x, y; // position of top left corner
   private int width, height;
   private int right, left, top, bottom; // for collision
   private Player p1; // reference
   private final int GOAL_PAUSE = 400; // pause in millis before switching level
   private static int level; // current level

   Goal(Player p1, int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      top = y;
      bottom =  y + height;
      left = x;
      right = x + width;
      this.p1 = p1;
      level = 1;
   }
   
   // Getters
   
   public static int getLevel() {
      return level;
   }
   
   // Methods
   
   public void drawMe(Graphics g) {
      g.fillRect(x, y, width, height);
   }
   
   // if colliding then it loads the next level and respawns the player
   public void goalReached() {
      if (isColliding()) { 
         try {
            Thread.sleep(GOAL_PAUSE); // Pauses everything for specified time
         } catch (InterruptedException e) {
         }
         p1.respawn();
         p1.setLoadLevel(true);
         nextLevel();
      }
   }
   
   // changes to next level
   private static void nextLevel() {
      level ++;
   }
   
   // returns wether player is within its bounds
   private boolean isColliding() {
      if (p1.getRight()>this.left && p1.getLeft()<this.right && p1.getTop()<this.bottom && p1.getBottom()>this.top)
         return true;
      else
         return false;
   }
   
   // toString
   public String toString() {
      return String.format("Goal at (%d, %d), size [%d, %d]", 
                            x, y, width, height);
   }
}