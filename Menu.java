// Marco Cecchi-Rivas
// 5/11/22
// handles the menu

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;

public class Menu {
   
   private String title;
   private Button play;
   private Button rankings;
   private Button settings;
   
   public Menu() {
      title = "Super Box Bros";
      play = new Button("PLAY", 500, 300, 200, 100, new Color(124, 63, 88));
   }
   
   
   // Getters
   
   public Button getPlay() {
      return play;
   }
   
   public Button getRankings() {
      return rankings;
   }
   
   public Button getSettings() {
      return settings;
   }
   
   
   //  Methods
   
   public void drawMe(Graphics g) {
      drawTitle(g, title);
      play.drawMe(g);
   }
   
   private void drawTitle(Graphics g, String title) {
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
        
      Font font = new Font("impact", Font.ITALIC, (int)((100*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      g2D.setFont(font);
      g2D.drawString(title, (int)((290*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((200*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
   }
}