package com.inekoalaty.forceboard;

import java.awt.*;

/**
 *
 * @author jamesthomas
 */
interface Placeable {
	
	public Vector getPosition();
	
	public double distanceTo (Placeable p);
	
	public Shape display(Graphics2D g2);
}