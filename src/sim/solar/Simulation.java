package sim.solar;

import sim.solar.planet.PlanetInterface;
import sim.solar.planet.PlanetLoader;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

class Simulation extends JPanel implements Runnable {

    private static int screenSize;   // screen size both x and y
   private static int frameDelay;       // milliseconds delay to slow down animation speed
   private static int maxSolarCount;      // cannot exceed data rows solarconfig.csv
   private static int cyclesPerSolarSystem;  // degrees of rotation allotted to each solar system
   private static int pauseDelay; // milliseconds delay between solar systems




   //These variables remain initialized here in the code, dont move them to config file :
   private static final Color colorBlack = new Color(0,0,0);
   private static final Color colorGreen = new Color(30,120,30); 
   private int cycleCount = 1;       // must start at one
   private int solarCounter = 0;   // must start at zero
   private static int screenMid; // mid-screen location
   
   private final PlanetLoader planetLoader = new  PlanetLoader();
   private SolarSystem solarSystem  ;

   public Simulation() throws IOException {
       //initialization
       Properties properties = new Properties();
       FileInputStream stream = new FileInputStream("classes/config.properties");
       properties.load(stream);
       screenSize = Integer.parseInt(properties.getProperty("screenSize"));
       frameDelay = Integer.parseInt(properties.getProperty("frameDelay"));
       maxSolarCount = Integer.parseInt(properties.getProperty("maxSolarCount"));
       cyclesPerSolarSystem = Integer.parseInt(properties.getProperty("cyclesPerSolarSystem"));
       pauseDelay = Integer.parseInt(properties.getProperty("pauseDelay"));
       screenMid = screenSize/2;

       //...cont
       List<PlanetInterface> planetList = planetLoader.Produce(solarCounter); 
       solarSystem = new SolarSystem(planetList); 
       setBackground(colorBlack);
       setPreferredSize( new Dimension(screenSize, screenSize) );
       final Thread t = new Thread (this);
       t.start ();
  }
  
  private void NextSolarSystemRun() {
       try {
         // this will slow down display animation
         Thread.sleep(frameDelay);
         
         // switch to next solar system
         if ((cycleCount % cyclesPerSolarSystem) == 0) {
             solarCounter++; 
             if (solarCounter > maxSolarCount) {
                solarCounter=0; // rollover to first planetary config
             }
             // get next set of planets to view 
             List<PlanetInterface> planetList = planetLoader.Produce(solarCounter);
             solarSystem = new SolarSystem(planetList); 
             Thread.sleep(pauseDelay);  // pause between change to next solar system
         }
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
            NextSolarSystemRun();
    }
  }
  
  public void paintComponent(final Graphics g)  {
    // clear out previous frame of drawings
    g.setColor( colorBlack ); 
    g.fillRect(0, 0, screenSize, screenSize);
    
    // paint planets in the solar system 
    solarSystem.paint(g, screenMid); 

    // repaint x-y axis lines using dark green
    g.setColor( colorGreen );
    g.drawLine( screenMid, 0, screenMid, screenSize);
    g.drawLine( 0, screenMid, screenSize, screenMid);
   }
}

