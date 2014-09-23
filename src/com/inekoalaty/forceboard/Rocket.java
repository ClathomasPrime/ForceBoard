package com.inekoalaty.forceboard;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Rocket extends Mover {
	
	private double rocketForce;
	
	private static double width = 0.2;
	private static double height = 0.8;
	
	/*private LinkedList<Vector> pastPoints = new LinkedList<Vector>();
	private int pointAdderTicker = 0;*/
	
	public Rocket(){
		this(new Vector(0,0), new Vector(0,0.1) ,10);
		rocketForce = 10;
	}
	public Rocket( Vector v0){
		this(new Vector(0,0),v0,10);
	}
	public Rocket( Vector r0, Vector v0){
		this(r0,v0,10);
	}
	public Rocket (Vector r0, Vector v0, double m, double c, double rForce){
		super(r0,v0,m,c);
		rocketForce = rForce;
}
	public Rocket( Vector r0, Vector v0, double rForce){
		super(r0,v0);
		rocketForce = rForce;
	}
	
	@Override
	public void evolve() {
		for(Force f : ForcePlot.forceList){
			applyForce( f.forceOn(this) );
		}
		rocketThrust();
		move();
		
		/*pointAdderTicker += 1;
		pointAdderTicker %= 10;
		if(pointAdderTicker == 0){
			pastPoints.add( position );
		}*/
	}
	
	public void rocketThrust(){
		applyForce( velocity.direction().multiply( rocketForce ) );
	}

	@Override
	public Shape display(Graphics2D g2) {
		Scale s = ForcePlot.scale;
		Vector p = position;
		
		double theta = velocity.positiveAngle() - Math.PI/2;
		double phi = Math.atan(height/width);
		Point2D p1,p2,p3,p4,p5;
		double r = Math.sqrt(width*width+height*height);
		
		p1 = new Point2D.Double(s.xScale( position.x + r*Math.cos(phi+theta) ), 
				s.yScale( position.y + r*Math.sin(phi+theta) ) );
		p2 = new Point2D.Double(s.xScale( position.x + r*Math.cos(Math.PI-phi+theta) ), 
				s.yScale( position.y + r*Math.sin(Math.PI-phi+theta) ) );
		p3 = new Point2D.Double(s.xScale( position.x + r*Math.cos(Math.PI+phi+theta) ), 
				s.yScale( position.y + r*Math.sin(Math.PI+phi+theta) ) );
		p4 = new Point2D.Double(s.xScale(position.x +  r*Math.cos(2*Math.PI-phi+theta) ), 
				s.yScale( position.y + r*Math.sin(2*Math.PI-phi+theta) ) );
		p5 = new Point2D.Double(s.xScale( position.x - 1.3*height*Math.sin(theta) ), 
				s.yScale( position.y + 1.3*height*Math.cos(theta) ));
				
		GeneralPath rect = new GeneralPath();
		rect.moveTo( p1.getX(), p1.getY() );
		rect.lineTo( p4.getX(), p4.getY() );
		rect.lineTo( p3.getX(), p3.getY() );
		rect.lineTo( p2.getX(), p2.getY() );
		rect.lineTo( p5.getX(), p5.getY() );
		rect.closePath();
		
		if( charge>0)
			g2.setColor(Color.BLUE);
		else if (charge<0)
			g2.setColor(Color.RED);
		else 
			g2.setColor(Color.BLACK);
		g2.draw(rect);
		
		/*for( Vector v : pastPoints){
			try {
				g2.draw(new Ellipse2D.Double(v.x,v.y,3,3) );
			} catch ( java.util.ConcurrentModificationException er){
				System.out.println("conc mod");
			}
		}*/
		
		g2.setColor(Color.BLACK);
		
		return rect;
	}
	
	public double getAngle(){
		return velocity.angle();
	}
	
}