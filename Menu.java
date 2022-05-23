// Marco Cecchi-Rivas
// 5/11/22
// handles the menu

import java.awt.Graphics;
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
        Font font = new Font("impact", Font.ITALIC, 100);
        g.setFont(font);
        g.drawString(title, 250, 200);
   }
}