package com.inekoalaty.forceboard;


/**
 *
 * @author jamesthomas
 */
abstract class ForcePoint implements Placeable, Force{
	
	protected Vector position;
	
	public ForcePoint(){
		this( new Vector(0,0) );
	}
	public ForcePoint( Vector r0){
		position = r0;
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}
	
	@Override
	public double distanceTo(Placeable p) {
		return position.distanceTo( p.getPosition() );
	}
	
	abstract public Vector forceFrom( Mover m);
}