package com.inekoalaty.forceboard;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;

/**
 *
 * @author jamesthomas
 */
public class PlaceMover extends JPanel{
	
	private JRadioButton ballButton;
	private JRadioButton rocketButton;
	
	private JTextField xPosition;
	private JTextField yPosition;
	
	private JTextField projectileAngle;
	private JTextField projectileSpeed;
	private JTextField rocketForce;
	private JTextField projectileMass;
	private JTextField projectileCharge;
	
	public PlaceMover(){
		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		JPanel moverPanel = new JPanel();
		moverPanel.setLayout(new BoxLayout(moverPanel, BoxLayout.Y_AXIS));
		Border moverBorder = BorderFactory.createTitledBorder("Set Mover");
		moverPanel.setBorder(moverBorder);
			//AddBallListener startListener = new AddBallListener();
			ballButton = new JRadioButton("Launch Ball");
			Window.placeableGroup.add( ballButton);
			moverPanel.add( ballButton );
			
			rocketButton = new JRadioButton("Launch Rocket");
			Window.placeableGroup.add( rocketButton);
			moverPanel.add( rocketButton );
			
			JPanel positionLabelPanel = new JPanel();
			positionLabelPanel.setLayout(new BoxLayout(positionLabelPanel, BoxLayout.X_AXIS));
				positionLabelPanel.add( new JLabel("Position:") );
				
				JButton launchButton = new JButton("<-");
				LaunchListener launchListener = new LaunchListener();
				launchButton.addActionListener( launchListener );
				positionLabelPanel.add(launchButton);
			moverPanel.add(positionLabelPanel);
			JPanel positionPanel = new JPanel();
			positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.X_AXIS));
				
				positionPanel.add( new JLabel("X:"));
				xPosition = new JTextField("0.0",1);
				positionPanel.add(xPosition);
				
				positionPanel.add( new JLabel("Y:"));
				yPosition = new JTextField( "0.0", 1 );
				yPosition.setSize(4,4);
				positionPanel.add(yPosition);
			moverPanel.add( positionPanel);
			
			JLabel angleLabel = new JLabel("Angle:");
			//angleLabel.setAlignmentX(LEFT_ALIGNMENT);
			moverPanel.add( angleLabel );
			projectileAngle = new JTextField("1.0",10);
			moverPanel.add(projectileAngle);
			projectileAngle.setMaximumSize( projectileAngle.getPreferredSize() );
			
			JLabel speedLabel = new JLabel("Speed:");
			//speedLabel.setAlignmentX(LEFT_ALIGNMENT);
			moverPanel.add( speedLabel );
			projectileSpeed = new JTextField("4.0",10);
			moverPanel.add(projectileSpeed);
			projectileSpeed.setMaximumSize( projectileSpeed.getPreferredSize() );
			
			moverPanel.add( new JLabel("(Rocket) Force:") );
			rocketForce = new JTextField("10.0",10);
			moverPanel.add(rocketForce);
			rocketForce.setMaximumSize( rocketForce.getPreferredSize() );
			
			moverPanel.add( new JLabel("Mass:") );
			projectileMass = new JTextField("1.0",10);
			moverPanel.add(projectileMass);
			projectileMass.setMaximumSize( projectileMass.getPreferredSize() );
			
			moverPanel.add( new JLabel("Charge:") );
			projectileCharge = new JTextField("0",10);
			moverPanel.add(projectileCharge);
			projectileCharge.setMaximumSize( projectileCharge.getPreferredSize() );
			
		moverPanel.setMaximumSize( moverPanel.getPreferredSize() );
		
		add(moverPanel);
		
	}
	
	public void place( Vector posit){
		double a0 = Double.parseDouble(projectileAngle.getText());
		double s0 = Double.parseDouble(projectileSpeed.getText());
		place( posit, Vector.fromPolar(a0, s0) );
	}
	public void place( Vector posit, Vector veloc){
		double m;
		double c;
		try {
			m = Double.parseDouble( projectileMass.getText() );
			c = Double.parseDouble( projectileCharge.getText() );
		} catch( NumberFormatException er) {
			m = 1.0;
			c = 0.0;
		}
		if ( ballButton.isSelected() ){
			ForcePlot.moverList.add( new Projectile(posit, veloc, m, c) );
		} else if ( rocketButton.isSelected() ){
			double strength;
			try {
				strength = Double.parseDouble( rocketForce.getText() );
			} catch (NumberFormatException er){
				strength = 10.0;
			}
			ForcePlot.moverList.add( new Rocket(posit, veloc, m, c, strength) );
		}
		Window.forcePlot.repaint();
	}
	
	public void placeVelocityMultiple( Vector posit, Vector veloc){
		place( posit, veloc.multiply( Double.parseDouble( projectileSpeed.getText()) ) );
	}
	
	private class LaunchListener implements ActionListener {

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