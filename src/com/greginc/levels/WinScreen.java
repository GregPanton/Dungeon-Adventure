package com.greginc.levels;

import com.greginc.game.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WinScreen extends JPanel
{
 private Game g;
 private BufferedImage background;
 
 public WinScreen(Game g)
 {
  this.g=g;
  init();
 }
 
 private void init()
 {
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/winscreen.jpg"));
  }catch(Exception ex)
  {
   System.out.println("Error Loading Win Screen");
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
  g.drawString("You win", 200, 300);
  
 
 
  g2d.dispose();
 }
 
 private class TAdapter extends KeyAdapter
 {
  @Override   
  public void keyReleased(KeyEvent e)
  {
   if(e.getKeyCode()== KeyEvent.VK_R)
   {
    System.out.println("Going back to start....");
    g.startGame();   
   }   
  }
}
}