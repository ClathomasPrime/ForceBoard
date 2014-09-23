package com.inekoalaty.forceboard;


import java.awt.* ;
import java.awt.geom.*;
import javax.swing.JComponent;

/**
 *
 * @author jamesthomas
 */
public class Projectile extends Mover {
	
	private static double radius = 0.5;
	
	public Projectile(){
		super();
	}
	public Projectile( Vector v0){
		super(v0);
	}
	
	public Projectile( Vector r0, Vector v0){
		super(r0,v0);
	}
	public Projectile( Vector r0, Vector v0, double m, double c){
		super(r0, v0, m, c);
	}
	@Override
	public void evolve() {
		for(Force f : ForcePlot.forceList){
			applyForce( f.forceOn(this) );
		}
		move();
	}

	@Override
	public Shape display( Graphics2D g2) {
		Scale s = ForcePlot.scale;
		Vector p = position;
		Shape ellipse = new Ellipse2D.Double(s.xScale(p.x - radius/2) , s.yScale(p.y + radius/2),
									s.deltaXScale( radius ) , s.deltaYScale( radius ) );
		if( charge>0)
			g2.setColor(Color.BLUE);
		else if (charge<0)
			g2.setColor(Color.RED);
		else 
			g2.setColor(Color.BLACK);
		
		g2.draw(ellipse);
		
		g2.setColor(Color.BLACK);
		
		
		return ellipse;
	}
	
}