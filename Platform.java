// Marco Cecchi-Rivas
// 4/10/21
// handles platform collision and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Platform {

   private int x, y; // position of top left corner
   private int width, height;
   private int top, bottom, left, right; // for collision
   private static Color color;
   private boolean onWall, onGround;
   private int type; // which side the player is colliding with
   private Player p1; // reference

   Platform(Player p1, int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      top = y;
      bottom =  y + height;
      left = x;
      right = x + width;
      this.p1 = p1;
      type = 0;
      color = new Color(124, 63, 88);
   }

   // Getters

   public boolean getOnGround() {
      return onGround;
   }

   public boolean getOnWall() {
      return onWall;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }
   
   public int getType() {
      return type;
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
   
   // Finds what side the player is to the platform, which is the side the player will collide from if it collides
   public void collisionType() {
      boolean r = false;
      boolean l = false;
      boolean t = false;
      boolean b = false;
      
      if (p1.getBottom() > this.top) {b = true;}
      if (p1.getTop() < this.bottom) {t = true;}
      if (p1.getRight() > this.left) {r = true;}
      if (p1.getLeft() < this.right) {l = true;}
      
      if (b == false) {
         type = 1;
      }
      else if (t == false) {
         type = 2;
      }
      else if (r == false) {
         type = 3;
      }
      else if (l == false) {
         type = 4;
      }
   }
   
   // Does necessary collision based on the type
   // snaps player to edge of platform that it collided with, and set velocities to 0
   public void doCollision() {
      if (isColliding()) {
         switch (type) {
            case 1:
               p1.setY(this.top - p1.getHeight());
               p1.setYVelocity(0);
               p1.setCanJump(true); // can jump because player is now on the ground
               break;
            case 2:
               p1.setY(this.bottom);
               p1.setYVelocity(0);
               break;
            case 3:
               p1.setX(this.left - p1.getWidth());
               p1.setXVelocity(0);
               break;
            case 4:
               p1.setX(this.right);
               p1.setXVelocity(0);
               break;
         }
      }
   }
   
   // sets onWall based on which side of the wall the player is colliding with
   public void slideType() {
      if (isColliding() && type == 3)
         p1.setOnWall(2);
      else if (isColliding() && type == 4)
         p1.setOnWall(1);
   }
   
   // updates bounds when level is loaded for collision
   public void updateBounds() {
      top = y;
      bottom = y + height;
      left = x;
      right = x + width;
   }
   
   // returns whether player is within its bounds
   private boolean isColliding() {
      if (p1.getRight()>this.left && p1.getLeft()<this.right && p1.getTop()<this.bottom && p1.getBottom()>this.top)
         return true;
      else
         return false;
   }
   
   // toString
   public String toString() {
      return String.format("Platform at (%d, %d), size [%d, %d], color %s", 
                            x, y, width, height, color);
   }
}