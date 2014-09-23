package com.inekoalaty.forceboard;

/**
 *
 * @author jamesthomas
 */
import java.applet.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
public class Window extends JFrame{
	
	public static PlaceForce placeForce;
	public static PlaceMover placeMover;
	public static ForcePlot forcePlot;
	public static TimeControls timeControls;
	
	public static ButtonGroup placeableGroup = new ButtonGroup();
	
	public static Window window;
	
	public Window(){
		this.setSize(1100,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("ForceBoard");
		this.setLayout( new BorderLayout(0,10) );
		this.setBackground(UIManager.getColor("Panel.background"));
		
		//placeableGroup = new ButtonGroup();
		
		forcePlot = new ForcePlot();
		this.add(forcePlot, BorderLayout.CENTER);
		
		placeForce = new PlaceForce();
		//placeForce.setSize(placeForce.getPreferredSize());
		this.add(placeForce, BorderLayout.WEST );
		
		placeMover = new PlaceMover();
		this.add(placeMover, BorderLayout.EAST );
		
		timeControls = new TimeControls();
		this.add(timeControls, BorderLayout.SOUTH);
		
		setVisible(true);
		//System.out.println("window fin of constr");
		
	}
	
	//@Override
	public void init(){
		//System.out.println("Init in window");
		//ForceBoard.window = new Window();
	}
	
}