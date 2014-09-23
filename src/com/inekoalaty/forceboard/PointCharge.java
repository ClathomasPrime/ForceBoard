package com.inekoalaty.forceboard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author jamesthomas
 */
public class PointCharge extends ForcePoint {

	private double radius = 1;
	private double chargeTimesK;
	
	//private double mass;
	
	public PointCharge( Vector r, double charge){
		super(r);
		chargeTimesK = charge;
		//mass = 1;
	}

	@Override
	public Shape display(Graphics2D g2) {
		Scale s = ForcePlot.scale;
		Vector p = position;
		Shape ellipse = new Ellipse2D.Double(s.xScale(p.x - radius*0.45) , s.yScale(p.y + radius*0.45),
									s.deltaXScale( radius*0.9 ) , s.deltaYScale( radius*0.9 ) );
		Shape ring = new Ellipse2D.Double(s.xScale(p.x - radius*0.5) , s.yScale(p.y + radius*0.5),
									s.deltaXScale( radius*1.0 ) , s.deltaYScale( radius*1.0 ) );
		if ( chargeTimesK>0 )
			g2.setColor(Color.BLUE);
		else if ( chargeTimesK<0 )
			g2.setColor(Color.RED);
		else 
			g2.setColor(Color.BLACK);
		g2.fill(ellipse);
		g2.draw(ring);
		g2.setColor(Color.BLACK);
		
		return ellipse;
	}

	@Override
	public Vector forceOn(Mover p) {
		Vector btw = Vector.between(p.position, position);
		if ( btw.magnitude() >= 0.01) {
			return btw.direction().multiply( -p.getCharge()*chargeTimesK / 
				( btw.magnitude()*btw.magnitude() ) );
		}
		else {
			//System.out.println("Distance too small for electrostatic force to be reasonable");
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
		return btw.direction().multiply( m.getCharge()*chargeTimesK / 
				( btw.magnitude()*btw.magnitude() ) );
	}
	
}