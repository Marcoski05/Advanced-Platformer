// Marco Cecchi-Rivas
// 5/11/22
// handles the menu

import java.awt.Graphics;
import java.awt.Color;

public class Menu {
   
   private String title;
   private Button play;
   private Button settings;
   private Button rankings;
   
   public Menu() {
      title = "Super Box Bros";
      play = new Button("PLAY", 500, 300, 200, 100, new Color(124, 63, 88));
   }
   
   //  Methods
   
   public void drawMe(Graphics g) {
      g.drawString(title, 100, 100);
      play.drawMe(g);
   }
}