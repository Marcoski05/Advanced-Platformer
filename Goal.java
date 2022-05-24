// Marco Cecchi-Rivas
// 4/10/21
// handles damage and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Goal extends Rectangle {

   private Player p1; // reference
   private final int GOAL_PAUSE = 300; // pause in millis before switching level
   private static int level; // current level

   Goal(Player p1, int x, int y, int width, int height) {
      super(x, y, width, height, Color.GREEN);
      this.p1 = p1;
      level = 0;
   }
   
   // Getters
   
   public static int getLevel() {
      return level;
   }
   
   // Setters
   
   public static void setLevel(int l) {
      level = l;
   }
   
   // Methods
   
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
      level++;
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
      return String.format("Goal at (%d, %d), size [%d, %d]", 
                            getX(), getY(), getWidth(), getHeight(), getColor());
   }
}