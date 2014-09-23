package com.inekoalaty.forceboard;

import java.awt.Shape;
import java.awt.*;
/**
 *
 * @author jamesthomas
 */
interface Force {
	
	abstract public Vector forceOn( Mover p);
	
	public Shape display( Graphics2D g2);
	
	public void evolve();
}