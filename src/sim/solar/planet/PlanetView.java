package sim.solar.planet;

import java.awt.Graphics;
import java.awt.Color;

public class PlanetView  {
  private final static Color lineColor = new Color(30, 120, 30);
    
  public void paint(Graphics g, int centerPosition, int x, int y, int planetSize, Color color) {
    g.setColor(color);
    g.fillOval( x+centerPosition, y+centerPosition, planetSize,  planetSize);
    g.setColor( lineColor  );
    g.drawLine( centerPosition, centerPosition, x+centerPosition, y+centerPosition); 
  }
}

