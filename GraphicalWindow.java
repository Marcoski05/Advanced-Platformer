// Marco Cecchi-Rivas
// 4/10/21
// handles graphical window

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GraphicalWindow extends JFrame implements KeyListener, MouseListener {

   public final static int WIDTH = 1215; // width of the window
   public final static int HEIGHT = 687; // height of the window
   private Player p1; // reference
   private Menu menu; // reference
   private boolean right, left, jump;

   GraphicalWindow(Player p1, Menu menu) {
      left = false;
      right = false;
      jump = false;
      setupFrame();
      addKeyListener(this);
      addMouseListener(this);
      this.p1 = p1;
      this.menu = menu;
   }

   // Sets the properties for the frame
   private void setupFrame() {
      setTitle(Game.title);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(WIDTH, HEIGHT);
      setLocationRelativeTo(null);
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
   
   
   // KeyListener Methods
   
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
   
   
   // MouseListener Methods
   
   public void mouseClicked(MouseEvent e) {
      int mx = e.getX();
      int my = e.getY();
      
      System.out.println("Mouse click at (" + mx + ", " + my + ")");
      
      if (menu.getPlay().wasPressed(mx, my)) {
         Game.setState(Game.State.GAME);
         Goal.setLevel(1);
         p1.respawn();
         System.out.println("Start Pressed");
      }
   }
   public void mouseEntered(MouseEvent e) {
   }
   public void mouseExited(MouseEvent e) {
   }
   public void mousePressed(MouseEvent e) {
   }
   public void mouseReleased(MouseEvent e) {
   }
   
   
   // KeyListener Methods
   
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
   
   @Override
   public int getWidth() {
      if ((super.getWidth()/(WIDTH+0.0)) - (super.getHeight()/(HEIGHT+0.0)) > 0.000001)
         return (int)(WIDTH*super.getHeight()/(HEIGHT+0.0));
      else
         return super.getWidth();
   }
   
   @Override
   public int getHeight() {
      if ((super.getWidth()/(WIDTH+0.0)) - (super.getHeight()/(HEIGHT+0.0)) < 0.000001)
         return (int)(HEIGHT*super.getWidth()/(WIDTH+0.0));
      else
         return super.getHeight();
   }
   
   // toString
   public String toString() {
      return String.format("Window with size of [%d, %d]", 
                            WIDTH, HEIGHT);
   }
}