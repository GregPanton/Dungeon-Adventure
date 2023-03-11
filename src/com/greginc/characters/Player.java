package com.greginc.characters;

import com.greginc.game.Sound;
import com.greginc.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

public class Player 
{
 //Player class attributes   
 private Vector position;
 private Vector displacement;
 
 private BufferedImage sprite;
 private int spriteWidth;
 private int spriteHeight;
 private int score;
 private int health;
 
 public Player()
 {
  position = new Vector(100, 350);
  displacement = new Vector(0,0);
  score = 0;
  health = 1000;
  init();
 }
 
 private void init()
 {
  try
  {
   sprite = ImageIO.read(getClass().getResourceAsStream("/images/character.png"));   
  } catch(Exception ex)
  {
   System.out.println("Error loading player image");
  }
 
  spriteWidth=sprite.getWidth();
  spriteHeight=sprite.getHeight();
 }
 
 public void setPosition(Vector v)
 {
   position = v;  
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
 
 public void setScore(int newScore)
 {
  score = newScore;
 }
 
 public int getScore()
 {
  return score;
 }
 
 public void setHealth(int newHealth)
 {
  health = newHealth;
 }
 
 public int getHealth()
 {
  return health;
 }
 
 public BufferedImage getSprite()
 {
  return sprite;   
 }
 
 public void move(int direction)
 {
  switch(direction)
  {
    case 1:
      displacement.setY(-2);
    break;
    case 2:
        displacement.setY(2);
    break;
    case 3:
        displacement.setX(-2);
    break;
    case 4:
        displacement.setX(2);
    break;
  }    
 }
 
 public void doMove()
 {
  position.add(displacement);   
 }
 
 public void stop()
 {
  displacement.setX(0);
  displacement.setY(0);
 }
 
 public Rectangle getBounds()
 {
  Rectangle characterRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
  return characterRect;
 }
 
 public boolean checkCollision(Treasure [] t)
 {
  for(Treasure treasure: t)
  {
   if(treasure.getBounds().intersects(getBounds()))
   { 
    if(treasure.getVisible()==true) 
    {
     score += treasure.getScore();
     Sound.play(getClass().getResourceAsStream("/sounds/collision.wav")); //sound for collection of lightning bolts(treasure)
     treasure.setVisible(false);
    } 
    return true;
   } 
  }
  return false;
 }
 
 public boolean checkCollision(Monster[] m)
 {
  for(Monster monster: m)
  {
   if(monster.getBounds().intersects(getBounds()))
   {
    health -= monster.getDamage();   
    return true;
   }   
  }
  return false;
 }
 
 public boolean checkCollision(Door d)
 {
   if(d.getBounds().intersects(getBounds()))
   {
    if(d.getVisible()==true){
    return true;
    }
   }   
  return false;   
 }
 
 public void draw(Graphics2D g)
 {
  g.drawImage(sprite, position.getX(), position.getY(), null);  
 }
}
