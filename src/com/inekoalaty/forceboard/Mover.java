package com.inekoalaty.forceboard;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jamesthomas
 */
public abstract class Mover implements Placeable{
	
	protected Vector velocity;
	protected Vector position;
	protected double mass;
	protected double charge;
	
	public Mover(){
		this( new Vector(0,0), new Vector(0,0) );
	}
	public Mover( Vector v0){
		this( new Vector(0,0), v0 );
	}
	public Mover ( Vector r0, Vector v0){
		this( r0, v0, 1.0, 0);
	}
	public Mover (Vector r0, Vector v0, double m, double c){
		//System.out.println("Mover created");
		position = r0;
		velocity = v0;
		mass = m;
		charge = c;
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}
	
	public double getMass(){
		return mass;
	}
	public double getCharge(){
		return charge;
	}

	@Override
	public double distanceTo(Placeable p) {
		return position.distanceTo( p.getPosition() );
	}
	
	public void applyForce( Vector f){
		Vector dP = f.multiply(ForcePlot.dt);
		Vector P = getMomentum().sum(dP);
		velocity = P.multiply(1/mass);
	}
	
	
	public abstract void evolve();
	
	public void move(){
		position = position.sum( velocity.multiply(ForcePlot.dt) );
	}
	
	public Vector getMomentum(){
		return velocity.multiply(mass);
	}
	
}