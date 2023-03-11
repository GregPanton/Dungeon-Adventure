package com.greginc.game;

import com.greginc.levels.EndScreen;
import com.greginc.levels.Level1;
import com.greginc.levels.WinScreen;
import com.greginc.screens.StartGamePanel;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Game 
{
 public static final int WINDOW_WIDTH= 600;
 public static final int WINDOW_HEIGHT= 600;
 
 private JFrame gameWindow; // The Game's main window
 private StartGamePanel startScreen;//This is a starting screen/mainmenu
 private Level1 lvl1; //This is the first level screen
 private EndScreen endScreen;//This is for the end screen
 private WinScreen winScreen;//This is for the win screen
 
 public Game()
 {
  initWindow(); 
  initScreens();  
 }
 
 private void initWindow()
 {
  gameWindow = new JFrame();
  
  gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
  gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  gameWindow.getContentPane().setLayout(new CardLayout());
  gameWindow.setResizable(false);
  gameWindow.setLocationRelativeTo(null);//Centres window to screen
  gameWindow.setTitle("My First Game");
 }
 
 private void initScreens()
 {
  startScreen = new StartGamePanel(this);  
  startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  lvl1 = new Level1(this);
  lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  endScreen = new EndScreen(this);
  endScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  winScreen = new WinScreen(this);
  winScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  gameWindow.getContentPane().add(startScreen, "StartScreen");
  gameWindow.getContentPane().add(lvl1, "Level1");
  gameWindow.getContentPane().add(endScreen, "EndScreen");
  gameWindow.getContentPane().add(winScreen, "WinScreen");
 }
 
 public void showStartScreen()
 {
  gameWindow.setVisible(true);
  startScreen.requestFocus();
 }
 
 public void startGame() //Goes to level 1
 {
  CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
  cl.next(gameWindow.getContentPane());
  lvl1.requestFocus();
  lvl1.start(); 
 }
 
 public void endGame() //Goes to End/loss Screen
 {
  CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
  cl.show(gameWindow.getContentPane(),"EndScreen");
  endScreen.requestFocus();
 }
 
 public void winGame() //Goes to Win Screen
 {
  CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
  cl.show(gameWindow.getContentPane(),"WinScreen");
  endScreen.requestFocus();   
 }
 
 public static void main(String[] args) 
 {
  Game g = new Game();
  g.showStartScreen();
 }
}
