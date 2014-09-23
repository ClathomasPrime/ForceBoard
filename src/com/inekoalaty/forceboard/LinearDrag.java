package com.inekoalaty.forceboard;

import java.awt.Graphics2D;
import java.awt.Shape;

/**
 *
 * @author jamesthomas
 */
public class LinearDrag extends Drag{
	
	public LinearDrag( double coef){
		super(coef);
	}

	@Override
	public Vector forceOn(Mover p) {
		return p.velocity.multiply(-1*coefficient);
	}

	@Override
	public Shape display(Graphics2D g2) {
		return null;
	}
	
}