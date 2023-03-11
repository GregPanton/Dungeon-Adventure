package com.greginc.characters;

import com.greginc.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;


public class Monster 
{
 private String name;
 private Vector position;
 private int direction;
 private int speed;
 private int damage;
 private int score;
 private int health;
 private boolean isVisible;
 private int spriteWidth;
 private int spriteHeight;
 private BufferedImage sprite;
 
 
 public Monster()
 {
  speed =3;   
  position = new Vector(300,300);
  health = 10;
  damage = 0;
  isVisible=true;
  
  init();
 }
 
 public Monster(Vector pos, int damage)
 {
  position = new Vector(pos);
  this.damage = damage;
  
  health = 10;
  direction = 0;
  speed = 3;
  isVisible = true;
  init();  
 }
 
 private void init()
 {
  try
  {
   sprite = ImageIO.read(getClass(). getResourceAsStream("/images/monster.png"));
  }catch(Exception ex)
  {
   System.out.println("Error loading monster image");   
  }
  
  spriteWidth = sprite.getWidth();
  spriteHeight = sprite.getHeight();
 }
 
 public void doMove(int levelWidth, int levelHeight)
 {
  Random rand = new Random();
  int randomDirection;
  
  Vector tempPosition = new Vector(position);
  Vector displacement = new Vector();
  
  randomDirection = rand.nextInt(4)+1;
  
  switch(randomDirection)
  {
   case 1:
    displacement.setY(-speed);
   break;
   case 2:
    displacement.setY(speed);
   break;
   case 3:
    displacement.setX(-speed);
   break;
   case 4:
    displacement.setX(speed);
   break;
  }
  
  //Add boundary checks so we dont move off screen
  
  if(tempPosition.getX()<(spriteWidth/2))
  {
   displacement.setX(1);  
  }else if(tempPosition.getX() > levelWidth - (spriteWidth/2))
  {
   displacement.setX(-1);   
  }
  
  if(tempPosition.getY()<(spriteHeight/2))
  {
   displacement.setY(-1);
  }else if(tempPosition.getY() > levelHeight -(spriteHeight/2))
  {
   displacement.setY(1);   
  }
  
  tempPosition.add(displacement);
  
  position.setToVector(tempPosition);
 }
 
 public Rectangle getBounds()
 {
  return new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);   
 }
 
 public void draw(Graphics2D g)
 {
  if(isVisible == true)
  {
   g.drawImage(sprite, position.getX(), position.getY(), null);   
  }   
 }
 
 public boolean attack()
 {
  return true;   
 } 
 
 //Add getters and setters
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
  
  public int getDamage()
  {
   return damage;   
  }
}
