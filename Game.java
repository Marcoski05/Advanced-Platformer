// Marco Cecchi-Rivas
// 4/10/21
// handles general functions and game loop

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

public class Game extends JComponent {

   static String title = "Super Boks Bro 2.0";
   private static State state = State.MENU;
   private Player p1;
   private static GraphicalWindow window;
   private Timer timer;
   private final int TIMER_MILLIS = 17; // Approximately 60fps
   private final int END_PAUSE = 1000; // Pause in millis when you beat the last level before ending the program
   
   public static enum State {
      MENU, GAME
   };
   
   //Arrays of platforms and hazards for each level
   private Platform[] pl1 = new Platform[6];
   private Platform[] pl2 = new Platform[7];
   private Platform[] pl3 = new Platform[6];
   private Platform[] pl4 = new Platform[8];
   private Platform[] pl5= new Platform[3];
   private Platform[] pl6 = new Platform[4];
   private Platform[] pl7 = new Platform[10];
   private Platform[] pl8 = new Platform[12];
   private Platform[] pl9= new Platform[10];
   private Hazard[] h5 = new Hazard[3];
   private Hazard[] h6 = new Hazard[5];
   private Hazard[] h7 = new Hazard[1];
   private Hazard[] h8 = new Hazard[7];
   private Hazard[] h9 = new Hazard[7];
   private Goal goal;
   
   Menu menu = new Menu();
   
   // Constructor
   Game() {
      p1 = new Player();
      window = new GraphicalWindow(p1, menu);
      p1.setWindow(window);
      
      window.getContentPane().setBackground(new Color(255, 246, 211));
      window.getContentPane().add(this);
      
      timer = new Timer(TIMER_MILLIS, 
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // Runs each frame
               gameLoop();
            }
         }
         );
      
      window.setVisible(true);
      
      if (state == State.GAME) {
         instantiate();
         timer.start();
      }
   }
   
   
   // All of the processes and logic that needs to happen each frame
   // Physics, Collision, Controls, etc.
   private void gameLoop() {
      window.keyListen();
      p1.setCanJump(false);
      p1.updateBounds();
      collisionTypeLoop();
      p1.friction();
      p1.wallSlide();
      p1.playerFalls();
      p1.updateVelocity();
      p1.applyVelocity();
      p1.updateBounds();
      damageLoop();
      p1.updateBounds();
      doCollisionLoop();
      goal.goalReached();
      p1.setOnWall(0);
      slideTypeLoop();
      window.repaint();
      p1.resetAcceleration();
   }


   @Override
   public void paintComponent(Graphics g) {
   // calls drawMe for each element in each array
      p1.drawMe(g);
      menu.drawMe(g);
      switch (Goal.getLevel()) {
         case 1:
            for (int r = 0; r < pl1.length; r++) {
               pl1[r].drawMe(g);
            }
            break;
         case 2:
            for (int r = 0; r < pl2.length; r++) {
               pl2[r].drawMe(g);
            }
            break;
         case 3:
            for (int r = 0; r < pl3.length; r++) {
               pl3[r].drawMe(g);
            }
            break;
         case 4:
            for (int r = 0; r < pl4.length; r++) {
               pl4[r].drawMe(g);
            }
            break;
         case 5:
            for (int r = 0; r < pl5.length; r++) {
               pl5[r].drawMe(g);
            }
            for (int r = 0; r < h5.length; r++) {
               h5[r].drawMe(g);
            }
            break;
         case 6:
            for (int r = 0; r < pl6.length; r++) {
               pl6[r].drawMe(g);
            }
            for (int r = 0; r < h6.length; r++) {
               h6[r].drawMe(g);
            }
            break;
         case 7:
            for (int r = 0; r < pl7.length; r++) {
               pl7[r].drawMe(g);
            }
            for (int r = 0; r < h7.length; r++) {
               h7[r].drawMe(g);
            }
            break;
         case 8:
            for (int r = 0; r < pl8.length; r++) {
               pl8[r].drawMe(g);
            }
            for (int r = 0; r < h8.length; r++) {
               h8[r].drawMe(g);
            }
            break;
         case 9:
            for (int r = 0; r < pl9.length; r++) {
               pl9[r].drawMe(g);
            }
            for (int r = 0; r < h9.length; r++) {
               h9[r].drawMe(g);
            }
            break;
         case 10:
            try {
               Thread.sleep(END_PAUSE); // Pauses everything for specified time
            } catch (InterruptedException e) {
            }
            System.exit(0); // Closes the window and ends the run
            break;
      }
   }
   
   // Finds the collision type for each element in the current level
   private void collisionTypeLoop() {
      switch (Goal.getLevel()) {
         case 1:
            for (int r = 0; r < pl1.length; r++) {
               pl1[r].collisionType();
            }
            break;
         case 2:
            for (int r = 0; r < pl2.length; r++) {
               pl2[r].collisionType();
            }
            break;
         case 3:
            for (int r = 0; r < pl3.length; r++) {
               pl3[r].collisionType();
            }
            break;
         case 4:
            for (int r = 0; r < pl4.length; r++) {
               pl4[r].collisionType();
            }
            break;
         case 5:
            for (int r = 0; r < pl5.length; r++) {
               pl5[r].collisionType();
            }
            break;
         case 6:
            for (int r = 0; r < pl6.length; r++) {
               pl6[r].collisionType();
            }
            break;
         case 7:
            for (int r = 0; r < pl7.length; r++) {
               pl7[r].collisionType();
            }
            break;
         case 8:
            for (int r = 0; r < pl8.length; r++) {
               pl8[r].collisionType();
            }
            break;
         case 9:
            for (int r = 0; r < pl9.length; r++) {
               pl9[r].collisionType();
            }
            break;
      }
   }
   
   // Does the collision for each element in the current level
   private void doCollisionLoop() {
      switch (Goal.getLevel()) {
         case 1:
            for (int r = 0; r < pl1.length; r++) {
               pl1[r].doCollision();
            }
            break;
         case 2:
            for (int r = 0; r < pl2.length; r++) {
               pl2[r].doCollision();
            }
            break;
         case 3:
            for (int r = 0; r < pl3.length; r++) {
               pl3[r].doCollision();
            }
            break;
         case 4:
            for (int r = 0; r < pl4.length; r++) {
               pl4[r].doCollision();
            }
            break;
         case 5:
            for (int r = 0; r < pl5.length; r++) {
               pl5[r].doCollision();
            }
            break;
         case 6:
            for (int r = 0; r < pl6.length; r++) {
               pl6[r].doCollision();
            }
            break;
         case 7:
            for (int r = 0; r < pl7.length; r++) {
               pl7[r].doCollision();
            }
            break;
         case 8:
            for (int r = 0; r < pl8.length; r++) {
               pl8[r].doCollision();
            }
            break;
         case 9:
            for (int r = 0; r < pl9.length; r++) {
               pl9[r].doCollision();
            }
            break;
      }
   }
   
   // Finds slide type for each element in the current level
   private void slideTypeLoop() {
      switch (Goal.getLevel()) {
         case 1:
            for (int r = 0; r < pl1.length; r++) {
               pl1[r].slideType();
            }
            break;
         case 2:
            for (int r = 0; r < pl2.length; r++) {
               pl2[r].slideType();
            }
            break;
         case 3:
            for (int r = 0; r < pl3.length; r++) {
               pl3[r].slideType();
            }
            break;
         case 4:
            for (int r = 0; r < pl4.length; r++) {
               pl4[r].slideType();
            }
            break;
         case 5:
            for (int r = 0; r < pl5.length; r++) {
               pl5[r].slideType();
            }
            break;
         case 6:
            for (int r = 0; r < pl6.length; r++) {
               pl6[r].slideType();
            }
            break;
         case 7:
            for (int r = 0; r < pl7.length; r++) {
               pl7[r].slideType();
            }
            break;
         case 8:
            for (int r = 0; r < pl8.length; r++) {
               pl8[r].slideType();
            }
            break;
         case 9:
            for (int r = 0; r < pl9.length; r++) {
               pl9[r].slideType();
            }
            break;
      }
   }
   
   // Checks damage for each hazard in the current level
   private void damageLoop() {
      int r;
      switch (Goal.getLevel()) {
         case 5:
            r = 0;
            while (r < h5.length) {
               h5[r].damage();
               r++;
            }
            break;
         case 6:
            r = 0;
            while (r < h6.length) {
               h6[r].damage();
               r++;
            }
            break;
         case 7:
            r = 0;
            while (r < h7.length) {
               h7[r].damage();
               r++;
            }
            break;
         case 8:
            r = 0;
            while (r < h8.length) {
               h8[r].damage();
               r++;
            }
            break;
         case 9:
            r = 0;
            while (r < h9.length) {
               h9[r].damage();
               r++;
            }
            break;
      }
   } 
   
   // Adds each platform to its level array
   private void instantiate() {
      goal = new Goal(p1, 1249, 0, 100, 650);
      // 1
      pl1[0] = new Platform(p1, 0, 600, 1200, 50);
      pl1[1] = new Platform(p1, 0, -1001, 50, 1650);
      pl1[2] = new Platform(p1, 1150, -1000, 50, 1450);
      pl1[3] = new Platform(p1, 350, 550, 300, 50);
      pl1[4] = new Platform(p1, 500, 500, 150, 50);
      pl1[5] = new Platform(p1, 900, 500, 50, 100);
      // 2
      pl2[0] = new Platform(p1, 0, 600, 1200, 50);
      pl2[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl2[2] = new Platform(p1, 1150, -1000, 50, 1050);
      pl2[3] = new Platform(p1, 1150, 200, 50, 450);
      pl2[4] = new Platform(p1, 300, 500, 200, 25);
      pl2[5] = new Platform(p1, 600, 400, 200, 25);
      pl2[6] = new Platform(p1, 900, 300, 200, 25);
      // 3
      pl3[0] = new Platform(p1, 0, 600, 1200, 50);
      pl3[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl3[2] = new Platform(p1, 1150, -1000, 50, 1450);
      pl3[3] = new Platform(p1, 425, 100, 25, 400);
      pl3[4] = new Platform(p1, 600, 200, 50, 400);
      pl3[5] = new Platform(p1, 800, 100, 25, 400);
      // 4
      pl4[0] = new Platform(p1, 0, 600, 1200, 50);
      pl4[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl4[2] = new Platform(p1, 1150, -1000, 50, 1450);
      pl4[3] = new Platform(p1, 800, 150, 50, 450);
      pl4[4] = new Platform(p1, 625, 50, 25, 200);
      pl4[5] = new Platform(p1, 250, 500, 100, 25);
      pl4[6] = new Platform(p1, 450, 450, 100, 25);
      pl4[7] = new Platform(p1, 650, 400, 150, 25);
      // 5
      pl5[0] = new Platform(p1, 0, 600, 1200, 50);
      pl5[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl5[2] = new Platform(p1, 1150, -1000, 50, 1450);
      h5[0] = new Hazard(p1, 400, 575, 100, 25);
      h5[1] = new Hazard(p1, 650, 575, 50, 25);
      h5[2] = new Hazard(p1, 900, 525, 50, 75);
      // 6
      pl6[0] = new Platform(p1, 0, 600, 1200, 50);
      pl6[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl6[2] = new Platform(p1, 1150, -1000, 50, 1350);
      pl6[3] = new Platform(p1, 1150, 500, 50, 150);
      h6[0] = new Hazard(p1, 250, 575, 150, 25);
      h6[1] = new Hazard(p1, 500, 575, 100, 25);
      h6[2] = new Hazard(p1, 675, 575, 75, 25);
      h6[3] = new Hazard(p1, 825, 575, 125, 25);
      h6[4] = new Hazard(p1, 1050, 575, 100, 25);
      // 7
      pl7[0] = new Platform(p1, 0, 600, 1200, 50);
      pl7[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl7[2] = new Platform(p1, 1150, -1000, 50, 1050);
      pl7[3] = new Platform(p1, 1150, 200, 50, 400);
      pl7[4] = new Platform(p1, 1100, 200, 50, 25);
      pl7[5] = new Platform(p1, 400, 500, 50, 25);
      pl7[6] = new Platform(p1, 550, 400, 50, 25);
      pl7[7] = new Platform(p1, 850, 100, 25, 150);
      pl7[8] = new Platform(p1, 900, 350, 50, 25);
      pl7[9] = new Platform(p1, 850, 450, 50, 25);
      h7[0] = new Hazard(p1, 300, 575, 850, 25);
      // 8
      pl8[0] = new Platform(p1, 0, 600, 1200, 50);
      pl8[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl8[2] = new Platform(p1, 1150, -1000, 50, 1350);
      pl8[3] = new Platform(p1, 1150, 500, 50, 100);
      pl8[4] = new Platform(p1, 200, 250, 50, 350);
      pl8[5] = new Platform(p1, 250, 250, 100, 25);
      pl8[6] = new Platform(p1, 550, 150, 25, 200);
      pl8[7] = new Platform(p1, 675, 325, 25, 125);
      pl8[8] = new Platform(p1, 800, 350, 25, 125);
      pl8[9] = new Platform(p1, 900, 150, 25, 150);
      pl8[10] = new Platform(p1, 925, 150, 75, 25);
      pl8[11] = new Platform(p1, 975, 400, 25, 175);
      h8[0] = new Hazard(p1, 250, 575, 900, 25);
      h8[1] = new Hazard(p1, 575, 150, 25, 200);
      h8[2] = new Hazard(p1, 650, 325, 25, 125);
      h8[3] = new Hazard(p1, 825, 350, 25, 125);
      h8[4] = new Hazard(p1, 925, 175, 25, 125);
      h8[5] = new Hazard(p1, 950, 400, 25, 175);
      h8[6] = new Hazard(p1, 1075, 325, 75, 25);
      // 9
      pl9[0] = new Platform(p1, 0, 600, 1200, 50);
      pl9[1] = new Platform(p1, 0, -1000, 50, 1650);
      pl9[2] = new Platform(p1, 1150, -1000, 50, 1450);
      pl9[3] = new Platform(p1, 850, 150, 50, 450);
      pl9[4] = new Platform(p1, 500, 400, 200, 25);
      pl9[5] = new Platform(p1, 250, 300, 150, 25);
      pl9[6] = new Platform(p1, 050, 200, 100, 25);
      pl9[7] = new Platform(p1, 200, 25, 50, 25);
      pl9[8] = new Platform(p1, 400, 25, 50, 25);
      pl9[9] = new Platform(p1, 650, 25, 50, 25);
      h9[0] = new Hazard(p1, 200, 50, 575, 25);
      h9[1] = new Hazard(p1, 1075, 275, 75, 25);
      h9[2] = new Hazard(p1, 1025, 425, 125, 25);
      h9[3] = new Hazard(p1, 900, 150, 125, 25);
      h9[4] = new Hazard(p1, 900, 275, 75, 25);
      h9[5] = new Hazard(p1, 850, 200, 25, 150);
      h9[6] = new Hazard(p1, 850, 500, 25, 100);
      h9[5].embeddedBounds();
      h9[6].embeddedBounds();
   }
   
   public static State getState() {
      return state;
   }
   public static void setState(State s) {
      state = s;
   }
   public static GraphicalWindow getWindow() {
      return window;
   }
}