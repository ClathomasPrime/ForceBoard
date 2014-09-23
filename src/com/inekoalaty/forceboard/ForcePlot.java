package com.inekoalaty.forceboard;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author jamesthomas
 */
public class ForcePlot extends JComponent{
	
	//public static ButtonGroup placeableGroup = new ButtonGroup();
	
	public static volatile LinkedList<Force> forceList = new LinkedList<Force>();
	public static volatile LinkedList<Mover> moverList = new LinkedList<Mover>();
	
	private static GraphicPlacer graphicPlacer = new GraphicPlacer(new Vector(0,0),false);
	
	public static boolean isRunning = false;
	
	public static Scale scale = new Scale();
	
	public static double dt = 0.002;
	
	
	
	public ForcePlot () {
		PlaceListener placeListener = new PlaceListener();
		this.addMouseListener( placeListener );
		this.addMouseMotionListener( placeListener );
		
		forceList.add( new ConstantGravity(0.0D) );
		forceList.add( new LinearDrag(0.0D) );
		
		//forceList.add( new PointGravity( new Vector(0,0), 20 ) );
		//moverList.add( new Projectile( new Vector(0,3), new Vector(3,0) ) );
	}
	
	private class PlaceListener extends MouseMotionAdapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent me) {
			//System.out.println("Mouse Clicked");
			
			/*System.out.println( me.getX()+","+me.getY() ) ; 
			/System.out.println( scale.xAntiScale( me.getX() )+","+ scale.yAntiScale( me.getY() ) ); */
			
			/*ForceBoard.window.placeForce.place( new Vector( scale.xAntiScale( me.getX() ), 
					scale.yAntiScale( me.getY() ) ));
			ForceBoard.window.placeMover.place( new Vector( scale.xAntiScale( me.getX() ), 
					scale.yAntiScale( me.getY() ) ));*/
			
			//Vector click = new Vector( scale.xAntiScale( me.getX() ), 
			//		scale.yAntiScale( me.getY() ) ) ;
			//graphicPlacer = new GraphicPlacer( click ,false);
			
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent me) {
			//System.out.println("Mouse Pressed");
			Vector click = new Vector( scale.xAntiScale( me.getX() ), 
					scale.yAntiScale( me.getY() ) ) ;
			graphicPlacer = new GraphicPlacer( click );
			//graphicPlacer.setCurrent(click);
			
			repaint();
		}
		
		@Override
		public void mouseDragged( MouseEvent me ){
			//System.out.println("Mouse Dragged");
			Vector click = new Vector( scale.xAntiScale( me.getX() ), 
					scale.yAntiScale( me.getY() ) ) ;
			graphicPlacer.setCurrent(click);
			
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent me) { 
			//System.out.println("Mouse Released");
			//System.out.println(graphicPlacer.getSpeed());
			graphicPlacer.disable();
			repaint();
			
			if ( graphicPlacer.getSpeed() < 0.6 ){
				graphicPlacer.disable();
				Window.placeForce.place( graphicPlacer.getPosition() );
				Window.placeMover.place( graphicPlacer.getPosition() );
				repaint();
			} else {
				Window.placeForce.place( graphicPlacer.getPosition() );
				Window.placeMover.placeVelocityMultiple( graphicPlacer.getPosition() ,
						graphicPlacer.getVelocity() );
				repaint();
			}
		}

		
		@Override
		public void mouseEntered(MouseEvent me) { }

		@Override
		public void mouseExited(MouseEvent me) { }
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g ;
		
		//System.out.println("Painting");
		
		g2.draw( ForcePlot.scale.yAxis() );
		g2.draw( ForcePlot.scale.xAxis() );
		g2.draw( ForcePlot.scale.boundary() );
		
		for(Force f : ForcePlot.forceList){
			try{
				//g2.draw( f.display() );
				//System.out.println("Painting a force");
				f.display(g2);
			} catch (NullPointerException error){
				
			}
		}
		for ( Mover m : ForcePlot.moverList ){
			try{
				//g2.draw( m.display( ) );
				//System.out.println("Painting a mover");
				m.display(g2);
			} catch (NullPointerException error){
				
			}
		}
		
		graphicPlacer.draw(g2);
		
	}
	
	private static class GraphicPlacer{
		private Vector start;
		private Vector current;
		private boolean display;
		
		private double radius = 0.6;
		public GraphicPlacer( Vector point){
			start = point;
			current = point;
			display = true;
		}
		public GraphicPlacer(Vector point, boolean disp){
			start = point;
			display = disp;
		}
		public void disable(){
			display = false;
		}
		public void enable(){
			display = true;
		}
		public void setCurrent( Vector point){
			current = point;
		}
		public void draw( Graphics2D g2 ){
			if( display){
				g2.setColor(Color.ORANGE);
				
				Vector p = start;
				Scale s = scale;
				
				Shape ellipse = new Ellipse2D.Double(s.xScale(p.x - radius/2) , s.yScale(p.y + radius/2),
						s.deltaXScale( radius ) , s.deltaYScale( radius ) );
				g2.draw( ellipse );
				
				Shape line = new Line2D.Double(s.xScale(start.x), s.yScale(start.y),
						s.xScale(current.x),s.yScale(current.y) );
				g2.draw(line);
				
				g2.setColor(Color.BLACK);
			}
		}
		public Vector getPosition(){
			return start;
		}
		public double getSpeed(){
			return Vector.between(start, current).magnitude();
		}
		public double getAngle(){
			return Vector.between(start, current).positiveAngle();
		}
		public Vector getVelocity(){
			return Vector.between(current ,start);
		}
	}
	
	public void evolve(){
		for( Mover m : ForcePlot.moverList ){
			m.evolve();
		}
		/*
		for( Force f : ForcePlot.forceList ){
			f.evolve();
		}*/
	}
	
	public static void reset(){
		forceList = new LinkedList<Force>();
		moverList = new LinkedList<Mover>();
		
		Window.placeForce.resetFields();
		
		Window.forcePlot.repaint();
	}
	public static void resetMovers(){
		moverList = new LinkedList<Mover>();
		
		Window.forcePlot.repaint();
	}
}