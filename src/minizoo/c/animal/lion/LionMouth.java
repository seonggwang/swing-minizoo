package minizoo.c.animal.lion;

import java.awt.Graphics2D;

import minizoo.c.Entity;

public class LionMouth extends Entity
{

	public LionMouth(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void visit(Graphics2D g2)
	{
		g2.setColor(getTintedColor(Lion.MOUTHCOLOR));
		g2.fillOval(0, 0, 70, 140);
	}
}
