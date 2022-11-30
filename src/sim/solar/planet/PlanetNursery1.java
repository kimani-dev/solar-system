package sim.solar.planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetNursery1 implements NurseryInterface {

    public String GetAuthor () {
     return "name1"; 
    }
    
    public String GetTitle () {
      return "Title1"; 
    }     
     
    public List <PlanetInterface> Produce () {
        List <PlanetInterface> planetList = new ArrayList<>();
        int angle = 0;
        int numPlanets = 90;  // sets number of planets created and range of i 
        for (int i=0; i<numPlanets; i++) {
            angle = angle + 5;   // controls offset between planets
            int orbit = 240;   // controls distance to center of solar system
            int increment = 1;         // controls speed of planet rotation
            int planetSize = 10;       // size of the planet
            int red = 240 - 2 * i;   // planet color
            int green = 240 - 2 * i;   // planet color
            int blue = 60 + 2 * i;   // planet color
            planetList.add(new Planet (angle, orbit, increment, planetSize, red, green, blue));
         }
         
         return planetList;
    }
}

