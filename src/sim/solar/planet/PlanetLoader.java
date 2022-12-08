package sim.solar.planet;

import java.util.*;
import java.io.FileReader;

import com.opencsv.CSVReader;

public class PlanetLoader {

    public List<PlanetInterface> Produce(int row) {
        
        List<PlanetInterface> planetList = new ArrayList<>();

        List<Integer> requiredRow = ReadPlanetConfig().get(row);

        //TODO: store the data from solarconfig.csv into these eleven variables:
        int numplanets = requiredRow.get(0);
        int orbit      = requiredRow.get(1);
        int increment  = requiredRow.get(2);
        int angleinc   = requiredRow.get(3);
        int planetsize = requiredRow.get(4);
        int redbase    = requiredRow.get(5);
        int greenbase  = requiredRow.get(6);
        int bluebase   = requiredRow.get(7);
        int redinc     = requiredRow.get(8);
        int greeninc   = requiredRow.get(9);
        int blueinc    = requiredRow.get(10);
        
 
        int angle     = 0;
        int red ;
        int green;
        int blue; 
        
        for (int i=0; i<numplanets; i++) {
            angle    = angle + angleinc;   // controls offset between planets
            red      = redbase   + redinc*i;   // planet color
            green    = greenbase + greeninc*i;   // planet color
            blue     = bluebase  + blueinc*i;   // planet color
            planetList.add(new Planet (angle, orbit, increment, planetsize, red, green, blue));
         }
         
        return planetList; 
   }
      
   

   private List<List<Integer>> ReadPlanetConfig()  {
      CSVReader reader;
       List<List<Integer>> rows = new ArrayList<>();

      try {  

         reader = new CSVReader(new FileReader("classes/solarconfig.csv"));
         String [] nextLine;  
         nextLine = reader.readNext();  // read the header line 

          //store headers in case we need them
          ArrayList<String> headers = new ArrayList<>();
          Collections.addAll(headers, nextLine);
          System.out.println("headers are " + headers);

          //store values
         while ((nextLine = reader.readNext()) != null) {
             List<Integer> row = new ArrayList<>();
            for(String token: nextLine){
                row.add(Integer.parseInt(token));
            }
             rows.add(row);
         }
         reader.close(); 
      }  
      catch (Exception e) {  
         e.printStackTrace();  
      }
      return rows;
   } 
}

