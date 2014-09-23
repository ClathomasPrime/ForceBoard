package com.inekoalaty.forceboard;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 *asdfasdf
 * @author jamesthomas
 */
public class ConstantGravity extends ForceField {
	
	private Vector acceleration;
	
	public ConstantGravity(){
		acceleration = new Vector( 0 ,-9.8);
	}
	public ConstantGravity( double g){
		acceleration = new Vector( 0 , g );
	}
	public ConstantGravity( Vector g ){
		acceleration = g;
	}
	
	@Override
	public Vector forceOn(Mover p) {
		return acceleration.multiply( p.getMass() );
	}

	@Override
	public Shape display(Graphics2D g2) {
		return null;
	}
	
}