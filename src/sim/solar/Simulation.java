package sim.solar; 
import sim.solar.planet.*; 

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

class Simulation extends JPanel implements Runnable {
   private static final Color colorBlack = new Color(0,0,0);
   private static final Color colorGreen = new Color(30,120,30);
   private static final int screenSize = 600;         // screen size both x and y
   private static final int screenMid = screenSize/2; // mid-screen location 
   
   // following constants need adjustment 
   private static final int frameDelay = 10;    // millisec delay to slow down animation speed 
   private static final int maxNurseryCount = 1;        // must be adjusted to run all the nurseries
   private static final int cyclesPerSolarSystem = 3*360;   // degrees of rotation allotted to each solar system 
   private static final int pauseDelay = 400; // millisec delay between solar systems 
   
   private int cycleCount = 1;       // must start at one
   private int nurseryCounter = 1;   // must start at one
   
   private Exhibit exhibit  = new Exhibit();
   private NurseryInterface nursery ;  
   private SolarSystem solarSystem  ;

   public Simulation() {  
    try {
       nursery = exhibit.GetNursery("PlanetNursery1");  // bootstrap the nursery 
       solarSystem = new SolarSystem(screenSize, nursery); // bootstrap the solarSystem

       setBackground(colorBlack);
       setPreferredSize( new Dimension(screenSize, screenSize) );
       Thread t = new Thread (this);
       t.start ();
    }
    catch (Exception e) {
       e.printStackTrace(); 
    }
   }
  

  public void run() {
    while(true)  {
      solarSystem.run(); // calculations for next animation 
      repaint();
      cycleCount++;
      try {
         // this will slow down display animation
         Thread.sleep(frameDelay);   
         
         // switch to next solar system and nursery setup
         if ((cycleCount % cyclesPerSolarSystem) == 0) {
             nurseryCounter++; 
             if (nurseryCounter > maxNurseryCount) {
                nurseryCounter=1; // rollover to first nursery
             }
             nursery = exhibit.GetNursery("PlanetNursery"+nurseryCounter);   // get next nursery to view 
             solarSystem = new SolarSystem(screenSize, nursery); // make a new solar system from the new nursery
             Thread.sleep(pauseDelay);  // pause between change to next solar system
         }
      } 
      catch(Exception e)
      {
         e.printStackTrace();
      }

    }
  }
  
  public void paintComponent(Graphics g)  {
    // clear out previous frame of drawings
    g.setColor( colorBlack ); 
    g.fillRect(0, 0, screenSize, screenSize);
    
    // paint planets in the solar system 
    solarSystem.paint(g); 

    // repaint x-y axis lines using dark green
    g.setColor( colorGreen );
    g.drawLine( screenMid, 0, screenMid, screenSize); 
    g.drawLine( 0, screenMid, screenSize, screenMid); 
   }
}

