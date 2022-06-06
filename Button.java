// Marco Cecchi-Rivas
// 5/11/22
// a button that can be pressed and performs an action

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
      g2D.fillRoundRect((int)((getX()*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((getY()*(Game.getWindow().getHeight()-37)/650.0)+0.5), (int)((getWidth()*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((getHeight()*(Game.getWindow().getHeight()-37)/650.0)+0.5), (int)((40*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((40*(Game.getWindow().getHeight()-37)/650.0)+0.5));
      
      Font font = new Font("impact", Font.ITALIC, (int)((50*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
      g2D.setFont(font);
      g2D.setColor(new Color(235, 107, 111));
      g2D.drawString(name, (int)(((getX()*(Game.getWindow().getWidth()-14)/1200.0)+50*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)(((getY()*(Game.getWindow().getHeight()-37)/650.0)+68*(Game.getWindow().getWidth()-14)/1200.0)+0.5));
   }
   
   public boolean wasPressed(int mx, int my) {
      return (mx > (int)((getLeft()*(Game.getWindow().getWidth()-14)/1200.0)+0.5) && mx < (int)((getRight()*(Game.getWindow().getWidth()-14)/1200.0)+0.5) && my > (int)((getTop()*(Game.getWindow().getHeight()-37)/650.0)+0.5) && my < (int)((getBottom()*(Game.getWindow().getHeight()-37)/650.0)+0.5));
   }
}