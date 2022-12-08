package sim.solar;

import sim.solar.planet.PlanetInterface;
import sim.solar.planet.PlanetView;

import java.awt.*;
import java.util.List;

class SolarSystem   {
   private final List<PlanetInterface> planetList ;
   private final PlanetView planetView = new PlanetView();

   public SolarSystem(List<PlanetInterface> planetList ) {  
       this.planetList = planetList; 
   }
  
  public void run() { 
     for (final PlanetInterface p : planetList) {
        p.run();
     }
  }
  
  public void paint(final Graphics g, int center)  {
     for (final PlanetInterface p : planetList) {
        planetView.paint(g, p.GetX(), p.GetY(), p.GetSize(), p.GetColor(), center); 
     }
  }
  
}


