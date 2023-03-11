package com.greginc.characters;

import com.greginc.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Door
{
 private Vector position;
 private int spriteWidth;
 private int spriteHeight;
 private boolean isVisible;
 private BufferedImage sprite;
 
 public Door(Vector pos)
 {
  position = pos; 
  isVisible = false;
  
  try
  {
   sprite = ImageIO.read(getClass().getResourceAsStream("/images/door.png"));   
  }catch(Exception ex)
  {
   System.out.println("Error loading door sprite");
  }
  
  spriteWidth = sprite.getWidth();
  spriteHeight = sprite.getHeight();
 }
 
 public BufferedImage getSprite()
 {
  return sprite;
 }
 
 public Rectangle getBounds()
 {
  Rectangle objectRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);   
  return objectRect;
 }
 
 public void setPosition(Vector v)
 {
  position.setToVector(v);   
 }
 
 public Vector getPosition()
 {
  return position;   
 }
 
 public int getSpriteWidth()
 {
  return spriteWidth;   
 }
 
 public int getSpriteHeight()
 {
  return spriteHeight;   
 }

 public void setVisible(boolean visible)
 {
  isVisible = visible;  
 }
 
 public boolean getVisible()
 {
  return isVisible;   
 }
 
 public void draw(Graphics2D g)
 {
  g.drawImage(sprite, position.getX(), position.getY(), null);   
 }
}
