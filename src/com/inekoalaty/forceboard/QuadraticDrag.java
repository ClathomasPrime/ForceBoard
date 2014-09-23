package com.inekoalaty.forceboard;

import java.awt.Graphics2D;
import java.awt.Shape;

/**
 *
 * @author jamesthomas
 */
public class QuadraticDrag extends Drag{
	
	public QuadraticDrag (double coef){
		super(coef);
	}

	@Override
	public Vector forceOn(Mover p) {
		return p.velocity.multiply( (-1)*coefficient*p.velocity.magnitude() );
	}

	@Override
	public Shape display(Graphics2D g2) {
		return null;
	}
	
}