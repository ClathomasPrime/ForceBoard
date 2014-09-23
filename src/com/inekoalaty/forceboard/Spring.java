package com.inekoalaty.forceboard;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author jamesthomas
 */
public class Spring extends ForcePoint{
	
	private double radius = 1;
	
	private double constant;
	private double restDistance;
	
	public Spring( Vector r, double constan, double rDist){
		super(r);
		constant = constan;
		restDistance = rDist;
	}

	@Override
	public Shape display(Graphics2D g2) {
		Scale s = ForcePlot.scale;
		Vector p = position;
		Shape ellipse = new Ellipse2D.Double(s.xScale(p.x - radius/2) , s.yScale(p.y + radius/2),
											 s.deltaXScale( radius ) , s.deltaYScale( radius ) );
		Shape inner = new Ellipse2D.Double(s.xScale(p.x - radius/4) , s.yScale(p.y + radius/4),
											 s.deltaXScale( radius/2 ) , s.deltaYScale( radius/2 ) );
		Area elAr = new Area( ellipse);
		Area inAr = new Area( inner);
		
		elAr.subtract(inAr);
		
		g2.setColor(new Color(100,0,200));
		g2.fill(elAr);
		
		for( Mover m : ForcePlot.moverList){
			//System.out.println("drawing spring lines");
			g2.draw( new Line2D.Double( s.xScale(position.x), s.yScale(position.y), 
					s.xScale(m.position.x), s.yScale(m.position.y)) );
		}
		
		g2.setColor( Color.BLACK );
		
		return ellipse;
	}

	@Override
	public Vector forceOn(Mover p) {
		double dist = distanceTo( p );
		double deltaX = dist-restDistance;
		double mag = -deltaX*constant;
		Vector dir = Vector.between(position, p.position).direction();
		return dir.multiply(mag);
	}

	@Override
	public void evolve() {
		/*for(Force f : ForcePlot.forceList){
			applyForce( f.forceOn(this) );
		}*/
		//for(Mover m : ForcePlot.moverList){
		//	applyForce( forceFrom(m) );
		//}
		//move();
	}

	@Override
	public Vector forceFrom(Mover m) {
		return new Vector( 0, 0);
	}
	
}