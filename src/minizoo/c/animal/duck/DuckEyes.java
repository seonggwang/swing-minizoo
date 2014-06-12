package minizoo.c.animal.duck;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import minizoo.c.Entity;

public class DuckEyes extends Entity 
{

	public DuckEyes(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void visit(Graphics2D g2)
	{
		g2.setColor(getTintedColor(Duck.EYECOLOR));
		g2.fill(new Ellipse2D.Double(0,0,30,30));
	}
}
