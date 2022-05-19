// Marco Cecchi-Rivas
// 5/11/22
// a button that can be pressed and performs an action

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Button extends Rectangle {
   
   private String name;
   
   public Button (String n, int x, int y, int width, int height, Color color) {
      super (x, y, width, height, color);
      name = n;
   }
   
   @Override
   public void drawMe(Graphics g) {
      super.drawMe(g);
      
      Font font = new Font("vgasys.fon", Font.PLAIN, 50);
      g.setFont(font);
      g.setColor(new Color(235, 107, 111));
      g.drawString(name, getX()+getWidth()/2, getY()+getHeight()/2);
   }
}