package com.inekoalaty.forceboard;


/**
 *
 * @author jamesthomas
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
public class PlaceForce extends JPanel{
	
		private JTextField constantGravityStrength;
		private JTextField dragCoefficient;
			private JRadioButton linearDrag;
			private JRadioButton quadraticDrag;
			
		private JRadioButton pointGravity;
		private JRadioButton pointAntiGravity;
		private JRadioButton pointMagicGravity;
		private JRadioButton pointCharge;
		private JRadioButton spring;
		
		private JTextField xPosition;
		private JTextField yPosition;
		
		private JTextField pointGravityConstant;
		private JTextField pointChargeStrength;
		private JTextField springConstant;
		private JTextField springRestDistance;
	
	public PlaceForce(){
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		Border fieldBorder = BorderFactory.createTitledBorder("Set Force Field");
		fieldPanel.setBorder(fieldBorder);
		fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			JPanel gravityPanel = new JPanel();
			gravityPanel.setLayout(new BoxLayout(gravityPanel, BoxLayout.X_AXIS));
				JLabel gravityLabel = new JLabel("ConstGrav:");
				gravityPanel.add( gravityLabel );

				constantGravityStrength = new JTextField("0.0", 4);
				constantGravityStrength.addFocusListener( new GravityListener() );
				gravityPanel.add( constantGravityStrength );

				JButton constantGravityButton = new JButton("->");
				constantGravityButton.setSize( new Dimension(50,50));
				constantGravityButton.addActionListener( new GravityListener() );
				gravityPanel.add( constantGravityButton );
			fieldPanel.add( gravityPanel);

			JPanel dragPanel = new JPanel();
			dragPanel.setLayout(new BoxLayout(dragPanel, BoxLayout.Y_AXIS));
				JPanel dragTextPanel = new JPanel();
				dragTextPanel.setLayout(new BoxLayout(dragTextPanel, BoxLayout.X_AXIS));
					JLabel dragLabel = new JLabel("DrafCoeff:");
					dragTextPanel.add( dragLabel );

					dragCoefficient = new JTextField("0.0", 3);
					dragCoefficient.addFocusListener( new GravityListener() );
					dragTextPanel.add( dragCoefficient );

					JButton dragCoefficientButton = new JButton("->");
					DragListener dragListener = new DragListener();
					dragCoefficientButton.addActionListener( dragListener );
					dragTextPanel.add( dragCoefficientButton );
				dragPanel.add(dragTextPanel);
				ButtonGroup dragTypeGroup = new ButtonGroup();
				JPanel dragTypePanel = new JPanel();
					linearDrag = new JRadioButton("linear",true);
					dragTypeGroup.add(linearDrag);
					dragTypePanel.add(linearDrag);

					quadraticDrag = new JRadioButton( "quadratic" );
					dragTypeGroup.add(quadraticDrag);
					dragTypePanel.add(quadraticDrag);
				dragPanel.add(dragTypePanel);
			fieldPanel.add(dragPanel);
		fieldPanel.setMaximumSize( fieldPanel.getPreferredSize() );
		this.add(fieldPanel);
		
		this.add(Box.createRigidArea(new Dimension(10,20)) );
		
		JPanel pointPanel = new JPanel();
		pointPanel.setLayout(new BoxLayout(pointPanel, BoxLayout.Y_AXIS));
		//pointPanel.setAlignmentX(LEFT_ALIGNMENT);
		Border pointBorder = BorderFactory.createTitledBorder("Set Force Point");
		pointPanel.setBorder(pointBorder);
		pointPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			pointGravity = new JRadioButton("Gravity Point",true);
			Window.placeableGroup.add(pointGravity);
			pointPanel.add( pointGravity);
			
			pointAntiGravity = new JRadioButton("Anti-Gravity Point");
			Window.placeableGroup.add(pointAntiGravity);
			pointPanel.add( pointAntiGravity );
			
			pointMagicGravity= new JRadioButton("Magic Gravity Point");
			Window.placeableGroup.add(pointMagicGravity);
			pointPanel.add( pointMagicGravity );
			
			pointCharge= new JRadioButton("Point Charge");
			Window.placeableGroup.add(pointCharge);
			pointPanel.add( pointCharge );
			
			spring = new JRadioButton("Spring");
			Window.placeableGroup.add(spring);
			pointPanel.add( spring );
			
			JPanel positionLabelPanel = new JPanel();
			positionLabelPanel.setLayout(new BoxLayout(positionLabelPanel, BoxLayout.X_AXIS));
				positionLabelPanel.add( new JLabel("Position:") );
				
				JButton placeButton = new JButton("->");
				placeListener placeListener = new placeListener();
				placeButton.addActionListener( placeListener );
				positionLabelPanel.add(placeButton);
			pointPanel.add(positionLabelPanel);
			JPanel positionPanel = new JPanel();
			positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.X_AXIS));
				
				positionPanel.add( new JLabel("X:"));
				xPosition = new JTextField("0.0",4);
				xPosition.setMaximumSize( xPosition.getPreferredSize() );
				positionPanel.add(xPosition);
				
				positionPanel.add( new JLabel("Y:"));
				yPosition = new JTextField( "0.0", 4 );
				yPosition.setMaximumSize( yPosition.getPreferredSize() );
				positionPanel.add(yPosition);
			pointPanel.add( positionPanel);
			
			pointPanel.add( new JLabel("Gravity Constant (G*M):") );
			pointGravityConstant = new JTextField("10.0",10);
			pointPanel.add(pointGravityConstant);
			pointGravityConstant.setMaximumSize( pointGravityConstant.getPreferredSize() );
			
			pointPanel.add( new JLabel("Point Charge (C*K):") );
			pointChargeStrength = new JTextField("0.0",10);
			pointPanel.add(pointChargeStrength);
			pointChargeStrength.setMaximumSize( pointChargeStrength.getPreferredSize() );
			
			pointPanel.add( new JLabel("Spring Constant:") );
			springConstant = new JTextField("10.0",10);
			pointPanel.add(springConstant);
			springConstant.setMaximumSize( springConstant.getPreferredSize() );
			
			pointPanel.add( new JLabel("Spring Rest Distance:") );
			springRestDistance = new JTextField("3.0",10);
			pointPanel.add(springRestDistance);
			springRestDistance.setMaximumSize( springRestDistance.getPreferredSize() );
		
		this.add(pointPanel);
		
		this.setMaximumSize( this.getPreferredSize() );
		
	}
	
	public void place( Vector posit){
		//System.out.println("Placing Force maybe");
		double grav = Double.parseDouble(pointGravityConstant.getText() );
		//System.out.println(pointGravity.isSelected());
		if ( pointGravity.isSelected() ){
			//System.out.println("Placing Gracity");
			ForcePlot.forceList.add( new PointGravity( posit, grav) );
		} else if (pointAntiGravity.isSelected() ){
			ForcePlot.forceList.add( new PointAntiGravity( posit, grav) );
		} else if (pointMagicGravity.isSelected() ){
			ForcePlot.forceList.add( new MagicGravity( posit, grav) );
		} else if (pointCharge.isSelected() ){
			double charge = Double.parseDouble(pointChargeStrength.getText() );
			ForcePlot.forceList.add( new PointCharge( posit, charge) );
		} else if (spring.isSelected() ){
			double restD = Double.parseDouble(springRestDistance.getText() );
			double constan = Double.parseDouble(springConstant.getText() );
			ForcePlot.forceList.add( new Spring( posit, constan, restD) );
		} else {
			//System.out.println("Must be placing mover");
		}
		Window.forcePlot.repaint();
	}
	
	/*public void place (Vector posit, Vector veloc){
		
	}*/
	
	public void resetFields(){
		constantGravityStrength.setText("0.0");
		dragCoefficient.setText("0.0");
	}
	
	private class GravityListener implements FocusListener, ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			resetGravity();
		}
		@Override
		public void focusGained(FocusEvent fe) {
			//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
		@Override
		public void focusLost(FocusEvent fe) {
			//resetGravity();
		}
		private synchronized void resetGravity(){
			//System.out.println( "Trying to replace gravity" );
			try {
				boolean foundGrav = false;
				for( Force f : ForcePlot.forceList){
					if ( f instanceof ConstantGravity ){
						foundGrav = true;
						//System.out.println( "Replace gravity" );
						ForcePlot.forceList.remove( f );
						ForcePlot.forceList.add( new ConstantGravity( Double.parseDouble(
								constantGravityStrength.getText()) ) );
					}
				}
				if ( !foundGrav ){
					ForcePlot.forceList.add( new ConstantGravity( Double.parseDouble(
							constantGravityStrength.getText()) ) );
				}
			} catch ( NumberFormatException er) {
				//System.out.println( "Error in gravity" );
			} catch ( java.util.ConcurrentModificationException er) {
				//System.out.println( "Conncurency in resetGravity error" );
			}
		}
	}
	private class DragListener implements ActionListener, ItemListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			resetDrag();
		}

		@Override
		public void itemStateChanged(ItemEvent ie) {
			resetDrag();
		}
		private synchronized void resetDrag(){
			try{
				//System.out.println("Reseting Drag");
				Drag drag;
				double coef = Double.parseDouble( dragCoefficient.getText() );
				if( linearDrag.isSelected() ){
					drag = new LinearDrag( coef );
				} else if ( quadraticDrag.isSelected() ) {
					drag = new QuadraticDrag( coef );
				} else {
					//throw new Error("Wow this wont work this way");
					drag = new LinearDrag(0);
				}
				boolean foundDrag = false;
				for( Force f : ForcePlot.forceList){
					if ( f instanceof Drag ){
						foundDrag = true;
						ForcePlot.forceList.remove( f );
						ForcePlot.forceList.add( drag );
					}
				}
				if ( !foundDrag ){
					ForcePlot.forceList.add( drag );
				}
			} catch (NumberFormatException num) {
				
			} catch ( java.util.ConcurrentModificationException mod){
				
			}
		}
		
	}
	
	private class placeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			try {
				place( new Vector( Double.parseDouble(xPosition.getText()),
						Double.parseDouble(yPosition.getText()) ) );
			} catch (NumberFormatException er){
				place( new Vector( 0,0 ) );
			}
		}
	}
	
	
}