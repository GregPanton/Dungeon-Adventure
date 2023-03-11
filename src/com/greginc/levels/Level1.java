package com.greginc.levels;

import com.greginc.characters.Door;
import com.greginc.characters.Monster;
import com.greginc.characters.Player;
import com.greginc.characters.Treasure;
import com.greginc.game.Game;
import com.greginc.game.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level1 extends JPanel implements ActionListener
{
 private Game g;
 private Timer timer;
 private BufferedImage background;
 private Player player;
 private Treasure[] treasures;
 private Monster[] monsters;
 private Door door;
 
 private final int NUMBER_OF_MONSTERS = 3;
 private final int NUMBER_OF_TREASURES = 4;
 
 //Level Constructor
 public Level1(Game g)
 {
  this.g=g;
  player = new Player();
  treasures = new Treasure[NUMBER_OF_TREASURES];
  monsters = new Monster[NUMBER_OF_MONSTERS];
  
  init();
  resetLevel();
 }
 
 public void resetLevel()
 {
  Random rand = new Random();
  
  for(int i = 0; i < NUMBER_OF_TREASURES; i++)
  {
   Vector v = new Vector();
   v.setX(rand.nextInt(Game.WINDOW_WIDTH) - 50); //So you dont go right to the edge when the treasure is created
   v.setY(rand.nextInt(Game.WINDOW_HEIGHT) - 50);
   
   treasures[i] = new Treasure(v, 30);      //Score fixed to 30, could be random
  }
  
  for(int j = 0; j < NUMBER_OF_MONSTERS; j++)
  {
   Vector v = new Vector();
   v.setX(rand.nextInt(Game.WINDOW_WIDTH) -50);//So you dont go right the edgewhen the monster is created
   v.setY(rand.nextInt(Game.WINDOW_HEIGHT) -50);
   
   monsters[j] = new Monster(v, 10);        //damage fixed to 10, could be random
  }
  
  door = new Door(new Vector(350,200));
  
 }
 
 private void init()
 {
  addKeyListener(new TAdapter());
  setFocusable(true);
  setDoubleBuffered(true);
  
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/background.jpg"));
  }catch(Exception ex)
  {
   System.out.println("Error Loading Level 1 Background");
  }
  
  timer = new Timer(10, this);
  //NOT USED THIS TIME --Sound.play(getClass().getResourceAsStream("/sounds/music.wav"),true);
 }
 
 @Override
 protected void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  
  Graphics2D g2d = (Graphics2D) g;
  //draw background
  g2d.drawImage(background, 0, 0, null);
  
  g2d.setColor(Color.WHITE);                           //This will draw the score and health and will be set as white to stand out 
  g2d.drawString("Score:" +player.getScore(), 10, 30);
  g2d.drawString("Health:" +player.getHealth(),10,60);
  
  player.draw(g2d);
  for(int i = 0; i < NUMBER_OF_MONSTERS; i++)
  {
   monsters[i].draw(g2d);   
  }
  //draw obstacles
  for(int j= 0; j < NUMBER_OF_TREASURES; j++)
  {
   treasures[j].draw(g2d);   
  }
  
  if(player.getScore()==200)        //This will change to the winscreen if the character's health goes to 0
  {
   door.draw(g2d);
   door.setVisible(true);
  }
  
  //draw character
  g2d.dispose();
 }
 
 public void collisions()
 {
  player.checkCollision(treasures);
  player.checkCollision(monsters);
  
  if(player.getHealth()==0)         //This will change to the endscreen if the character's health goes to 0
  {
   g.endGame();   
  }  
  
  player.checkCollision(door);
  if(player.checkCollision(door)==true)
  {
   g.winGame();   //This will change to the winscreen if the character interacts with the door
  }
 }
 
 public void movement()
 {
  player.doMove();
  for(Monster m: monsters)
  {
   m.doMove(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT); //Move within the bounds of the game level
  }
 }
 
 public void start()
 {
  timer.start();
 }
 
 public void stop()
 {
  timer.stop();   
 }

 @Override
 public void actionPerformed(ActionEvent e) 
 {
  collisions();
  movement();
  repaint();   
 }
 
 private class TAdapter extends KeyAdapter
 {
  @Override
  public void keyPressed(KeyEvent e)
  {
   int move = 0;

   switch(e.getKeyCode())
   {
    case KeyEvent.VK_UP:
        move=1;
    break;
    case KeyEvent.VK_DOWN:
        move=2;
    break;
    case KeyEvent.VK_LEFT:
        move=3;
    break;
    case KeyEvent.VK_RIGHT:
        move=4;
    break;
   }
   
   player.move(move);
  }
  
  @Override
  public void keyReleased(KeyEvent e)
  {
   player.stop();
  }
 }
}
