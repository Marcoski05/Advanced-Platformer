// Marco Cecchi-Rivas
// 4/10/21
// handles graphical window

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GraphicalWindow extends JFrame implements KeyListener {

   private final static int WIDTH = 1214; // width of the window
   private final static int HEIGHT = 687; // height of the window
   private Player p1; // reference
   private boolean right, left, jump;

   GraphicalWindow(Player p1) {
      left = false;
      right = false;
      jump = false;
      setupFrame();
      addKeyListener(this);
      this.p1 = p1;
   }

   // Sets the properties for the frame
   private void setupFrame() {
      setTitle(Game.title);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(WIDTH, HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
   }
   
   // Getters
   
   public boolean getRight() {
      return right;
   }
   
   public boolean getLeft() {
      return left;
   }
   
   public boolean getJump() {
      return jump;
   }
   
   // Setters
   
   public void setJump(boolean s) {
      jump = s;
   }
   
   // Methods
   
   // Sets jump, right, and left, to false when their button is released
   public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_D:
            right = false;
            break;
         case KeyEvent.VK_A:
            left = false;
            break;
         case KeyEvent.VK_K:
            jump = false;
            break;
      }
   }
   // Sets jump, right, and left to true when their button is pressed
   public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_D:
            right = true;
            break;
         case KeyEvent.VK_A:
            left = true;
            break;
         case KeyEvent.VK_K:
            jump = true;
            break;
      }
   }
   public void keyTyped(KeyEvent e) {
   }
   
   // Does specified move based on which keys are currently pressed
   public void keyListen() {
      if (right && left) {
         if (p1.getXVelocity()>0) {
            p1.moveLeft();
         }
         else if (p1.getXVelocity()<0) {
            p1.moveRight();
         }
      }
      else  {
         if (right) {
            p1.moveRight();
         }
         if (left) {
            p1.moveLeft();
         }
      }
      if (jump && !(p1.getOnWall() == 0)) {
         p1.wallJump(p1.getOnWall());
      }
      else if (jump) {
         p1.jump();
      }
   }
   
   // toString
   public String toString() {
      return String.format("Windo with size of [%d, %d]", 
                            WIDTH, HEIGHT);
   }
}