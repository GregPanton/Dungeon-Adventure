package com.greginc.game;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound 
{
  public static synchronized void play(InputStream soundResource)
  {
   new Thread(new Runnable()
   {
     @Override
     public void run()
     {
      try
      {
       Clip clip = AudioSystem.getClip();
       AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
       clip.open(ais);
       clip.start();
      }catch(Exception ex)
      {
       System.out.println("Error playing sound" + ex.getMessage());   
      }
     }
   }).start();   
  }  
  
  public static synchronized void play(InputStream soundResource, boolean isMusic)
  {
   new Thread(new Runnable()
   {
    @Override
    public void run()
    {
     try
     {
      Clip clip = AudioSystem.getClip();
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
      clip.open(ais);
      clip.start();
      
      if(isMusic == true)
          clip.loop(Clip.LOOP_CONTINUOUSLY);
     }catch(Exception ex)
     {
      System.out.println("Error playing sound" + ex.getMessage());
     }
    }
   }).start();
  }
}
