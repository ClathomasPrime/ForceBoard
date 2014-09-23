package com.inekoalaty.forceboard;

/**
 *
 * @author jamesthomas
 */
//mport java.math.*;
//import java.lang.Math;
public class Vector {
	public double x;
	public double y;
	
	public Vector( double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo( Vector p){
		return Math.sqrt( Math.pow(x - p.x , 2 ) + 
						  Math.pow(y - p.y, 2) );
	}
	
	public double magnitude(){
		return Math.sqrt( this.x*this.x + this.y*this.y);
	}
	public Vector direction(){
		return this.multiply( 1/this.magnitude() );
	}
	
	public Vector sum (Vector a){
		return new Vector( x+ a.x, y+a.y);
	}
	
	public Vector multiply( double d){
		return new Vector( x*d, y*d);
	}
	
	public static Vector sum( Vector a, Vector b){
		return a.sum(b) ;
	}
	
	public double angle(){
		if ( x != 0 )
			return Math.atan(y/x);
		else{
			return Math.PI/2;
		}
	}
	
	public double positiveAngle(){
		if ( x > 0 ){
			return Math.atan(y/x);
		} else if ( x < 0){
			return Math.PI + Math.atan(y/x);
		} else{
			return Math.PI/2;
		}
	}
	
	public boolean equals(Vector v){
		return (x==v.x) && (y==v.y);
	}
	
	public static Vector fromPolar (double angle, double magnitude){
		return new Vector( Math.cos(angle)*magnitude, 
						   Math.sin(angle)*magnitude  );
	}
	public static Vector between( Vector v1, Vector v2){
		return new Vector( v2.x-v1.x,v2.y-v1.y);
	}
}