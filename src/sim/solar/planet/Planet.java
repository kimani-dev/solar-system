package sim.solar.planet;

import java.awt.Color; 

public class Planet implements PlanetInterface {

  private final static double MAX_DRIFT = 1.4;
  private final static double MIN_DRIFT = 1.0; 
  
  // variables to track position and look of planet 
  private int x;
  private int y; 
  private int angle; 
  private final int angleIncrement;
  private final double orbit ;
  private final int size;
  private final Color color;
  private double frequencyDrift = 1.0; // initial value 1.0 is no drift

    private int mult=1;
   
  public Planet (int startAngle, double scale, int inc, int planetSize, int red, int green, int blue) {
     angle = startAngle;
     orbit = scale; 
     angleIncrement = inc;
     size = planetSize;
     color = new Color(red,green,blue); 
  }
 
   public int GetX() {
    return x; 
    }
    
   public int GetY() {
    return y; 
    } 
  
  public int GetSize() {
    return size; 
    } 
    
  public Color GetColor() {
    return color; 
    }     


  public void run() {
      angle = ( angle + angleIncrement ) % 360;
      double rads = Math.toRadians(angle);
      double xd = orbit * Math.cos(rads);  
      double yd = orbit * Math.sin( frequencyDrift * rads);   
      x = (int) xd;
      y = (int) yd;
      // 0.0 drift increment is no drift, 0.0002 is a good drift
      double driftIncrement = 0.0;
      frequencyDrift = frequencyDrift + mult * driftIncrement;
      if (frequencyDrift > MAX_DRIFT) mult = -1;  // flip drift direction when max hit 
      if (frequencyDrift < MIN_DRIFT) mult =  1;  // flip drift direction when min hit
     }
  
}

