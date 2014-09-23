package com.inekoalaty.forceboard;

import javax.swing.JRadioButton;

/**
 *
 * @author jamesthomas
 */
abstract class PlaceButton extends JRadioButton{
	
	abstract public Placeable place();
	
	public PlaceButton ( String str, boolean bool){
		super(str,bool);
	}
	
	public PlaceButton ( String str){
		super(str);
	}
	
}