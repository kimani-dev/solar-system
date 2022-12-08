package sim.solar;

import javax.swing.*;
import java.io.IOException;

public class DisplayFrame extends JFrame {
  public void Activate () throws IOException {
  
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Simulation sim = new Simulation(); 
    getContentPane().add(sim); 
     
    pack();
    setVisible(true);
  }
}
