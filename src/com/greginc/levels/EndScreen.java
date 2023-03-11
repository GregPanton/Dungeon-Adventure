package com.greginc.levels;

import com.greginc.game.Game;
import com.greginc.screens.StartGamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndScreen extends JPanel
{
 private Game g;
 private BufferedImage background;
 
 public EndScreen(Game g)
 {
  this.g=g;
  init();
 }
 
 private void init()
 {
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/endscreen.jpg"));
  }catch(Exception ex)
  {
   System.out.println("Error Loading End Screen");
  }      
  setFocusable(true);
  addKeyListener(new TAdapter());
 }
 
 protected void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  
  Graphics2D g2d = (Graphics2D) g;
  
  g2d.drawImage(background, 0, 0, null);
  g2d.setColor(Color.WHITE);
  g.drawString("You died", 200, 300);
  
 
 
  g2d.dispose();
 }
 
 private class TAdapter extends KeyAdapter
 {
  @Override   
  public void keyReleased(KeyEvent e)
  {
   if(e.getKeyCode()== KeyEvent.VK_R)
   {
    System.out.println("Going back to the start...");
    g.startGame();   
   }   
  }
 }
}
