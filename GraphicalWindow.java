// Marco Cecchi-Rivas
// 4/10/21
// handles graphical window

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

public class GraphicalWindow extends JFrame implements KeyListener, MouseListener {

   private final static int WIDTH = 1215; // width of the window
   private final static int HEIGHT = 687; // height of the window
   private Player p1; // reference
   private Menu menu; // reference
   private boolean right, left, jump;
   private final int RESTART_PAUSE = 500;

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
         case KeyEvent.VK_RIGHT:
            right = false;
            break;
         case KeyEvent.VK_A:
         case KeyEvent.VK_LEFT:
            left = false;
            break;
         case KeyEvent.VK_K:
         case KeyEvent.VK_SPACE:
         case KeyEvent.VK_W:
         case KeyEvent.VK_UP:
            jump = false;
            break;
      }
   }
   // Sets jump, right, and left to true when their button is pressed
   public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_D:
         case KeyEvent.VK_RIGHT:
            right = true;
            break;
         case KeyEvent.VK_A:
         case KeyEvent.VK_LEFT:
            left = true;
            break;
         case KeyEvent.VK_K:
         case KeyEvent.VK_SPACE:
         case KeyEvent.VK_W:
         case KeyEvent.VK_UP:
            jump = true;
            break;
         case KeyEvent.VK_R:
            restart();
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
   
   // MouseListener Methods
   
   public void mouseClicked(MouseEvent e) {
      int mx = e.getX()-7;
      int my = e.getY()-30;
      
      System.out.println("Mouse click at (" + mx + ", " + my + ")");
      
      if (menu.getPlay().wasPressed(mx, my)) {
         Game.setState(Game.State.GAME);
         Goal.setLevel(1);
         p1.respawn();
         startTimer();
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
   
   // Methods
   
   //starts timer that updates the speedrun clock
   private void startTimer() {
      Timer timer = new Timer("SRTimer");
      timer.scheduleAtFixedRate(new Task(), 0, 10);
   }
   
   private void restart() {
      try {
         Thread.sleep(RESTART_PAUSE); // Pauses everything for specified time
      } catch (InterruptedException e) {
      }
      Goal.setLevel(1);
      p1.respawn();
      Task.resetTime();
   }
   
   // toString
   public String toString() {
      return String.format("Window with size of [%d, %d]", 
                            WIDTH, HEIGHT);
   }
}


class Task extends TimerTask {
   
   private static double time = 0;
   
   public static double getTime() {
      return time;
   }
   
   public static void resetTime() {
      time = 0;
   } 
   
   public static void drawTime(Graphics g) {
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
      
      Font font = new Font("impact", Font.PLAIN, (int)((50*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      g2D.setFont(font);
      g2D.setColor(new Color(235, 107, 111));
      if (time < 60)
         g2D.drawString(String.format("%.2f", time), (int)((60*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((50*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      else
         g2D.drawString(String.format("%.0f:%.1f", time/60, time%60), (int)((60*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((50*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
   }
   
   @Override
   public void run() {
      time += 0.01;
   }
}