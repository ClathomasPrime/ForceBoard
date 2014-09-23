package com.inekoalaty.forceboard;

/**
*
* @author jamesthomas
*/
abstract class Drag extends ForceField{
	
	protected double coefficient;
	
	public Drag( double coef){
		coefficient = coef;
	}
	
}