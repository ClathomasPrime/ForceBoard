package com.inekoalaty.forceboard;

import java.awt.geom.*;

/**
 *
 * @author jamesthomas
 */
public class Scale {
	private double xScale = 50;
	private double yScale = 50;
	private double width = 600;
	private double height = 600;

	public Scale(){
		
	}

	public Vector scale(Vector p){
		return new Vector( xScale(p.x), yScale(p.y) );
	}
	public double xScale(double x){
		return width/2 + xScale*x;
	}
	public double xAntiScale(double x){
		return (x-width/2)/xScale;
	}
	public double yScale(double y){
		return height/2 - yScale*y;
	}
	public double yAntiScale(double y){
		return -(y-height/2)/yScale;
	}
	public double deltaXScale(double x){
		return xScale*x;
	}
	public double deltaYScale(double y){
		return 1*yScale*y;
	}
	public Line2D.Double yAxis(){
		return new Line2D.Double(xScale(0), 0,
								xScale(0), height);
	}
	public Line2D.Double xAxis(){
		return new Line2D.Double(0, yScale(0),
								width, yScale(0) );
	}
	public Rectangle2D.Double boundary(){
		return new Rectangle2D.Double(0,0,width,height);
	}
	
}