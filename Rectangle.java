// Marco Cecchi-Rivas
// 5/5/21
// Basic collide-able rectangle

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle {
   
   private int x, y; // position of top left corner
   private int width, height;
   private int top, bottom, left, right; // for collision
   private Color color;
   
   public Rectangle (int x, int y, int width, int height, Color color) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      top = y;
      bottom =  y + height;
      left = x;
      right = x + width;
      this.color = color;
   }
   
   
   // Getters
   
   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }
   
   public int getRight() {
      return right;
   }
   
   public int getLeft() {
      return left;
   }
   
   public int getTop() {
      return top;
   }
   
   public int getBottom() {
      return bottom;
   }
   
   public int getWidth() {
      return width;
   }
   
   public int getHeight() {
      return height;
   }
   
   public Color getColor() {
      return color;
   }
   
   
   // Setters
   
   public void setY(int y) {
      this.y = y;
   }
   
   public void setX(int x) {
      this.x = x;
   }
   
   public void setTop(int t) {
      top = t;
   }
   
   public void setBottom(int b) {
      bottom = b;
   }
   
   public void setLeft(int l) {
      left = l;
   }
   
   public void setRight(int r) {
      right = r;
   }
   
   // Methodss
   
   public void drawMe(Graphics g) {
      g.setColor(color);
      g.fillRect((int)((x*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((y*(Game.getWindow().getHeight()-37)/650.0)+0.5), 
                 (int)((width*(Game.getWindow().getWidth()-14)/1200.0)+0.5), (int)((height*(Game.getWindow().getHeight()-37)/650.0)+0.5));
   }
}