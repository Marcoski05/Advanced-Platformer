// Marco Cecchi-Rivas
// 5/11/22
// handles the menu

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.Scanner;

public class Menu {
   
   private String title;
   private Button play;
   
   public Menu() {
      title = "Super Box Bros";
      play = new Button("PLAY", 500, 290, 200, 100, new Color(124, 63, 88));
   }
   
   
   // Getters
   
   public Button getPlay() {
      return play;
   }
   
   
   //  Methods
   
   public void drawMe(Graphics g) {
      drawTitle(g, title);
      play.drawMe(g);
      drawBestTime(g);
      drawGraphics(g);
   }
   
   private void drawTitle(Graphics g, String title) {
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
        
      Font font = new Font("impact", Font.ITALIC, (int)((100*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      g2D.setFont(font);
      g2D.drawString(title, (int)((290*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((175*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
   }
   
   private void drawBestTime(Graphics g) {
      String pathname = "SBB Times.txt";
      File times = new File(pathname);
      Scanner input = null;
      
      try {
         input = new Scanner(times);
      }
      catch (FileNotFoundException ex) {
         System.out.println("***No Times Found***");
         return;
      }
      
      double bestTime = Double.MAX_VALUE;
      double nextDouble = 0;
      while (input.hasNextDouble()) {
         nextDouble = input.nextDouble();
         if (nextDouble < bestTime)
            bestTime = nextDouble;
      }
            
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
        
      Font font = new Font("impact", Font.PLAIN, (int)((50*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      g2D.setFont(font);
      g2D.setColor(new Color (249, 168, 117));
      g2D.drawString(String.format("Best Time: %.2f", bestTime), (int)((425*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((250*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
   }
   
   private boolean eyeRight = true;
   private void drawGraphics(Graphics g) {
      int px = 125;
      int py = 350;
      Rectangle player = new Rectangle(px, py, 200, 200, new Color (249, 168, 117));
      player.drawMe(g);
      if (Game.getWindow().getRight())
         eyeRight = true;
      else if (Game.getWindow().getLeft())
         eyeRight = false;
      if (eyeRight) {
         Rectangle leftEye = new Rectangle(px+80, py+52, 24, 68, new Color(77, 40, 55));
         Rectangle rightEye = new Rectangle(px+152, py+52, 24, 68, new Color(77, 40, 55));
         leftEye.drawMe(g);
         rightEye.drawMe(g);
      }
      else {
         Rectangle leftEye = new Rectangle(px+28, py+52, 24, 68, new Color(77, 40, 55));
         Rectangle rightEye = new Rectangle(px+100, py+52, 24, 68, new Color(77, 40, 55));
         leftEye.drawMe(g);
         rightEye.drawMe(g);
      }
      Rectangle ground = new Rectangle(0, py+200, 1200, 550-py, new Color(124, 63, 88));
      Rectangle hazard = new Rectangle(750, py+100, 400, 100, new Color(235, 107, 111));
      ground.drawMe(g);
      hazard.drawMe(g);
   }
}