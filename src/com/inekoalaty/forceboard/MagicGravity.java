package com.inekoalaty.forceboard;


import java.awt.*;
import java.awt.geom.*;


public class MagicGravity extends ForcePoint {
	
	private double radius = 1;
	
	private double massTimesG;
	private double mass;
	
	public MagicGravity() {
	}

	public MagicGravity(Vector r0) {
		super(r0);
	}
	public MagicGravity( Vector r, double mTG){
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
		g2.setColor(new Color(250,0,250));
		g2.fill(ellipse);
		g2.setColor( Color.BLACK );
		
		return ellipse;
	}

	@Override
	public Vector forceOn(Mover p) {
		Vector btw = Vector.between(p.position, position);
		return btw.direction().multiply( p.getMass()*massTimesG );
	}
	
	@Override
	public void evolve() {
		
	}
	
	@Override
	public Vector forceFrom(Mover m) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}