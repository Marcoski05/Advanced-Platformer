// Marco Cecchi-Rivas
// 4/10/21
// handles platform collision and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Platform extends Rectangle {

   private boolean onWall, onGround;
   private int type; // which side the player is colliding with
   private Player p1; // reference

   Platform(Player p1, int x, int y, int width, int height) {
      super(x, y, width, height, new Color(124, 63, 88));
      this.p1 = p1;
      type = 0;
   }

   // Getters

   public boolean getOnGround() {
      return onGround;
   }

   public boolean getOnWall() {
      return onWall;
   }
   
   public int getType() {
      return type;
   }
   
   
   // Methods
   
   // Finds what side the player is to the platform, which is the side the player will collide from if it collides
   public void collisionType() {
      boolean r = false;
      boolean l = false;
      boolean t = false;
      boolean b = false;
      
      if (p1.getBottom() > getTop()) {b = true;}
      if (p1.getTop() < getBottom()) {t = true;}
      if (p1.getRight() > getLeft()) {r = true;}
      if (p1.getLeft() < getRight()) {l = true;}
      
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
               p1.setY(getTop() - p1.getHeight());
               p1.setYVelocity(0);
               p1.setCanJump(true); // can jump because player is now on the ground
               break;
            case 2:
               p1.setY(getBottom());
               p1.setYVelocity(0);
               break;
            case 3:
               p1.setX(getLeft() - p1.getWidth());
               p1.setXVelocity(0);
               break;
            case 4:
               p1.setX(getRight());
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
   
   // returns whether player is within its bounds
   private boolean isColliding() {
      if (p1.getRight()>getLeft() && p1.getLeft()<getRight() && p1.getTop()<getBottom() && p1.getBottom()>getTop())
         return true;
      else
         return false;
   }
   
   // toString
   public String toString() {
      return String.format("Platform at (%d, %d), size [%d, %d], color %s", 
                            getX(), getY(), getWidth(), getHeight(), getColor());
   }
}