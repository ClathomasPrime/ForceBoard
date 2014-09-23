package com.inekoalaty.forceboard;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

class PointGravity extends ForcePoint {
	
	private double radius = 1;
	
	private double massTimesG;
	private double mass;
	
	public PointGravity( Vector r, double mTG){
		super(r);
		massTimesG = mTG;
		mass = 1;
	}

	@Override
	public Shape display(Graphics2D g2) {
		Scale s = ForcePlot.scale;
		Vector p = position;
		Shape ellipse = new Ellipse2D.Double(s.xScale(p.x - radius/2) , s.yScale(p.y + radius/2),
									s.deltaXScale( radius ) , s.deltaYScale( radius ) );
		g2.setColor(Color.BLACK);
		g2.fill(ellipse);
		
		return ellipse;
	}

	@Override
	public Vector forceOn(Mover p) {
		Vector btw = Vector.between(p.position, position);
		if ( btw.magnitude() >= 0.01) {
			return btw.direction().multiply( p.getMass()*massTimesG / 
				( btw.magnitude()*btw.magnitude() ) );
		}
		else {
			//System.out.println("Distance too small for gravity to be reasonable");
			return new Vector( 0.0, 0.0);
		}
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
		Vector btw = Vector.between(position, m.position);
		return btw.direction().multiply( m.getMass()*massTimesG / 
				( btw.magnitude()*btw.magnitude() ) );
	}
	
}