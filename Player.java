// Marco Cecchi-Rivas
// 4/10/21
// handles player movement and graphics

import java.awt.Graphics;
import java.awt.Color;

public class Player extends Rectangle {

   private Color eyeColor;
   private int eyeWidth, eyeHeight;
   private Boolean eyeRight;
   private final int X_SPAWN = 100;
   private final int Y_SPAWN = 500;
   private boolean loadLevel; // whether or not the next level should be loaded
   
   private double yVelocity, xVelocity; // current velocity (speed)
   private double yAcceleration, xAcceleration; // "Force" that you are putting on object
   private final double FALL_GRAV = 1.4; // Gravity while falling
   private final double RISE_GRAV = 1; // Gravity while rising
   private final double APEX_GRAV = 0.7; // Gravity at the peak of your jump (lower to give more hang time)
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
      super(100, 500, 50, 50, new Color (249, 168, 117));
      loadLevel = true;
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
      
   public int getOnWall() {
      return onWall;
   }
   
   public boolean getLoadLevel() {
      return loadLevel;
   }
   
   
   // Setters
   
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
   
   @Override
   public void drawMe(Graphics g) {
         super.drawMe(g);
         drawEyes(g);
    }
   
   // draws eyes based on the current direction being traveled
   private void drawEyes(Graphics g) {
      g.setColor(eyeColor);
      if (window.getRight()) {
         //g.fillRect(getX() + 20, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(20*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         //g.fillRect(getX() + 38, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(38*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         eyeRight = true;
      }
      else if (window.getLeft()){
         //g.fillRect(getX() + 7, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect(((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(7*(Game.getWindow().getWidth()-14)/1200.0+0.5))), ((int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5))), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         //g.fillRect(getX() + 25, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect(((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(25*(Game.getWindow().getWidth()-14)/1200.0+0.5))), ((int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5))), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         eyeRight = false;
      }
      else {
         // eyes stay facing the same direction when no buttons are being pressed
         if (eyeRight == true) {
            //g.fillRect(getX() + 20, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(20*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         //g.fillRect(getX() + 38, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(38*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         }
         else {
         //g.fillRect(getX() + 7, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(7*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
         //g.fillRect(getX() + 25, getY() + 13, eyeWidth, eyeHeight);
         g.fillRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+(25*(Game.getWindow().getWidth()-14)/1200.0+0.5)), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+(13*(Game.getWindow().getHeight()-14)/650.0+0.5)), 
                 (int)((eyeWidth*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((eyeHeight*(Game.getWindow().getHeight()-37)/650.0)+0.5));
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
      setX(getX() + (int)xVelocity);
      setY(getY() + (int)yVelocity);
   }
   
   // Updates bounds after player moves
   public void updateBounds() {
      setTop(getY());
      setBottom(getY() + getHeight());
      setLeft(getX());
      setRight(getX() + getWidth());
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
   
   // Adds upward force to yAcceleration
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
      setX(X_SPAWN);
      setY(Y_SPAWN);
   }
   
   // toString
   public String toString() {
      return String.format("Player at (%d, %d), size [%d, %d], color %s", 
                            getX(), getY(), getWidth(), getHeight(), getColor());
   }
}