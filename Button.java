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
      g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 40, 40);
      
      Font font = new Font("impact", Font.ITALIC, 50);
      g.setFont(font);
      g.setColor(new Color(235, 107, 111));
      g.drawString(name, getX()+50, getY()+68);
   }
   
   public boolean wasPressed(int mx, int my) {
      return (mx > getLeft() && mx < getRight() && my > getTop() && my < getBottom());
   }
}