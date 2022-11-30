package sim.solar;

import sim.solar.planet.NurseryInterface;
import sim.solar.planet.PlanetInterface;
import sim.solar.planet.PlanetView;

import java.awt.*;
import java.util.List;

class SolarSystem   {
   private final static Color textColor = new Color(60, 120, 200);
   private final List<PlanetInterface> planetList;
   private final PlanetView planetView = new PlanetView();
   NurseryInterface nursery;
   int centerPosition;
   
   public SolarSystem(int screenSize, NurseryInterface nextNursery) {  
      centerPosition = screenSize/2; 
      nursery = nextNursery; 
      planetList = nursery.Produce(); 
   }
  
  public void run() { 
     for (PlanetInterface p : planetList) {
        p.run();
     }
  }
  
  public void paint(Graphics g)  {
     int textX = centerPosition;
     int textY = 2*centerPosition - 20; 
     g.setColor(textColor);
     g.drawString(nursery.GetTitle() + "    " + nursery.GetAuthor(), textX, textY);
     for (PlanetInterface p : planetList) {
        planetView.paint(g, centerPosition, p.GetX(), p.GetY(), p.GetSize(), p.GetColor()); 
     }
  }
  
}


