// Marco Cecchi-Rivas
// 4/10/21
// handles player movement and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Player {

   private int width, height;
   private int eyeWidth, eyeHeight;
   private Color color, eyeColor;
   private Boolean eyeRight;
   private int y, x; // position of top left corner
   private int top, bottom, left, right; // for collision
   private final int X_SPAWN = 100;
   private final int Y_SPAWN = 500;
   private boolean loadLevel; // whether or not the next level should be loaded
   
   private double yVelocity, xVelocity; // current velocity (speed)
   private double yAcceleration, xAcceleration; // "Force" that you are putting on object
   private final double FALL_GRAV = 1.4; // Gravity while falling
   private final double RISE_GRAV = 1; // Gravity while rising
   private final double APEX_GRAV = 0.7; // Gravity at th epeak of your jump (lower to give more hang time)
   private double maxFallSpeed;
   private final double APEX = 3;
   private final double SPEED = 2; // Speed that you accelerate by
   private final double MAX_SPEED = 6.5;
   private final double FRICTION = 1; // How much friction slows you down
   private final double JUMP_STRENGTH = 15;
   private final double HORIZONTAL_WJUMP = 10; // pushes you away from wall when walljumping
   private final double VERTICAL_WJUMP = 15;
   private boolean canJump; // turns on when you are on the ground or wall so that you cannot jump infinitely
   private int onWall;
   
   private GraphicalWindow window; // reference
   
   Player() {
      width = 50;
      height = 50;
      y = Y_SPAWN;
      x = X_SPAWN;
      loadLevel = true;
      color = new Color(249, 168, 117);
      eyeColor = new Color(77, 40, 55);
      eyeWidth = 6;
      eyeHeight = 17;
      eyeRight = true;
      maxFallSpeed = 15;
   }
   
   // Getters
   
   public double getYVelocity() {
      return yVelocity;
   }
   
   public double getXVelocity() {
      return xVelocity;
   }
   
   public int getY() {
      return y;
   }
   
   public int getX() {
      return x;
   }
   
   public int getRight() {
      return right;
   }
   
   public int getLeft() {
      return left;
   }
   
   public int getTop() {
      return top;
   }
   
   public int getBottom() {
      return bottom;
   }
   
   public int getHeight() {
      return height;
   }
   
   public int getWidth() {
      return width;
   }
   
   public int getOnWall() {
      return onWall;
   }
   
   public boolean getLoadLevel() {
      return loadLevel;
   }
   
   // Setters
   
   public void setY(int y) {
      this.y = y;
   }
   
   public void setX(int x) {
      this.x = x;
   }
   
   public void setYVelocity(double v) {
      yVelocity = v;
   }
   
   public void setXVelocity(double v) {
      xVelocity = v;
   }
   
   public void setXAcceleration(double a) {
      xAcceleration = a;
   }
   
   public void setYAcceleration(double a) {
      yAcceleration = a;
   }
   
   public void setCanJump(boolean cj) {
      canJump = cj;
   }
   
   public void setWindow(GraphicalWindow w) {
      window = w;
   }
   
   public void setOnWall(int w) {
      onWall = w;
   }
   
   public void setLoadLevel(boolean l) {
      loadLevel = l;
   }
   
   
   // Methods
   
   public void drawMe(Graphics g) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
      drawEyes(g);
   }
   
   // draws eyes based on the current direction being traveled
   private void drawEyes(Graphics g) {
      g.setColor(eyeColor);
      if (window.getRight()) {
         g.fillRect(x + 20, y + 13, eyeWidth, eyeHeight);
         g.fillRect(x + 38, y + 13, eyeWidth, eyeHeight);
         eyeRight = true;
      }
      else if (window.getLeft()){
         g.fillRect(x + 7, y + 13, eyeWidth, eyeHeight);
         g.fillRect(x + 25, y + 13, eyeWidth, eyeHeight);
         eyeRight = false;
      }
      else {
         // eyes stay facing the same direction when no buttons are being pressed
         if (eyeRight == true) {
            g.fillRect(x + 20, y + 13, eyeWidth, eyeHeight);
            g.fillRect(x + 38, y + 13, eyeWidth, eyeHeight);
         }
         else {
            g.fillRect(x + 7, y + 13, eyeWidth, eyeHeight);
            g.fillRect(x + 25, y + 13, eyeWidth, eyeHeight);
         }
      }
   }
   
   // Resets acceleration to 0
   public void resetAcceleration() {
      xAcceleration = 0;
      yAcceleration = 0;
   }
   
   // Makes player fall by gravity by adding to the acceleration
   // If player is falling by over maxFallSpeed, set velocity to equal maxFallSpeed
   public void playerFalls() {
      if (yVelocity < maxFallSpeed) {
         if (yVelocity > APEX) {
            yAcceleration += FALL_GRAV;
         }
         else if (yVelocity < (-1 * APEX)) {
            yAcceleration += RISE_GRAV;
         }
         else {
            yAcceleration += APEX_GRAV;
         }
      }
      else {
         yVelocity = maxFallSpeed;
      }
   }
   
   // Adds acceleration to velocity
   public void updateVelocity() {
      xVelocity += xAcceleration;
      yVelocity += yAcceleration;
   }
   
   // applys velocity to the players position
   public void applyVelocity() {
      x += xVelocity;
      y += yVelocity;
   }
   
   // Updates bounds after player moves
   public void updateBounds() {
      top = y;
      bottom = y + height;
      left = x;
      right = x + width;
   }
   
   // adds positive force to xAcceleration
   public void moveRight() {
      if (xVelocity < MAX_SPEED) {
         xAcceleration += SPEED;
      }
   }
   
   // adds negative force to xAcceleration
   public void moveLeft() {
      if (xVelocity > (-1 * MAX_SPEED)) {
         xAcceleration += (-1 * SPEED);
      }
   }
   
   // Adds upward force to yAcceleratio,
   public void jump() {
      if (canJump) {
         yAcceleration = -1 * JUMP_STRENGTH;
      }
   }
   
   // If player is on a wall, then lowers maxFallSpeed to appear as if player is sliding down the wall
   public void wallSlide() {
      if (onWall == 1 || onWall == 2) {
         maxFallSpeed = 5;
         window.setJump(false);
      }
      else {
         maxFallSpeed = 15;
      } 
   }
   
   // Adds force to the side and upward to jump away from whichever side of wall player is facing
   public void wallJump(int t) {
      if (t == 1) {
         yVelocity = 0;
         yAcceleration = -1 * VERTICAL_WJUMP;
         xAcceleration = HORIZONTAL_WJUMP;
      }
      else if (t == 2) {
         yVelocity = 0;
         yAcceleration = -1 * VERTICAL_WJUMP;
         xAcceleration = -1 * HORIZONTAL_WJUMP;
      }
   }
   
   // Checks if you have stopped pressing the arrows, and then slows you down to a stop (so you don't continue forever)
   public void friction() {
      if (!window.getLeft() && !window.getRight()) {
         if (xVelocity > 0) {
            xVelocity -= FRICTION;
            if (xVelocity < 0) {
               xVelocity = 0;
            }
         }
         else if (xVelocity < 0) {
            xVelocity += FRICTION;
            if (xVelocity > 0) {
               xVelocity = 0;
            }
         }
      }
   }
   
   // Sets players position to the spawn
   public void respawn() {
      x = X_SPAWN;
      y = Y_SPAWN;
   }
   
   // toString
   public String toString() {
      return String.format("Player at (%d, %d), size [%d, %d], color %s", 
                            x, y, width, height, color);
   }
}