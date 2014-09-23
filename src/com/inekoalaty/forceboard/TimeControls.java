package com.inekoalaty.forceboard;
/**
*
* @author jamesthomas
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.concurrent.*;
public class TimeControls extends JPanel{
	
	private JButton startButton;
	private JButton stopButton;
	private JLabel runningLabel;
	
	private JButton resetButton;
	private JButton resetMoversButton;
	
	private boolean isRunning;
	
	private static ScheduledThreadPoolExecutor executor;
	
	public TimeControls(){
		isRunning = false;
		
		setLayout( new BoxLayout(this, BoxLayout.X_AXIS) );
		
		TimeStartListener startListener = new TimeStartListener();
		startButton = new JButton("Start");
		startButton.addActionListener(startListener);
		this.add( startButton );
		
		TimeStopListener stopListener = new TimeStopListener();
		stopButton = new JButton("Stop");
		stopButton.addActionListener( stopListener );
		this.add( stopButton );
		
		runningLabel = new JLabel("     Stopped     ");
		this.add( runningLabel );
		
		add( Box.createHorizontalGlue() );
		
		ResetListener resetListener = new ResetListener();
		resetButton = new JButton("Reset");
		resetButton.addActionListener( resetListener );
		this.add( resetButton );
		
		ResetMoversListener resetMoversListener = new ResetMoversListener();
		resetMoversButton = new JButton("Reset Movers");
		resetMoversButton.addActionListener( resetMoversListener );
		this.add(resetMoversButton);
		
		//start();
	}
	
	private void start(){
		if( !isRunning ){
			//System.out.println("Time Started");

			TimeStart t = new TimeStart();
			executor = new ScheduledThreadPoolExecutor(1);
			executor.scheduleAtFixedRate( t
					, 0L, (long)(ForcePlot.dt*1000), TimeUnit.MILLISECONDS );

			isRunning = true;
			runningLabel.setText("Running");
		}
	}
	
	private class TimeStartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/*if( !isRunning ){
				System.out.println("Time Started");

				TimeStart t = new TimeStart();
				executor = new ScheduledThreadPoolExecutor(3);
				executor.scheduleAtFixedRate( t
						, 0L, (long)(ForcePlot.dt*1000000), TimeUnit.MICROSECONDS );
				
				isRunning = true;
				runningLabel.setText("Running");
			}*/
			start();
		}
		
	}
	private class TimeStopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if( isRunning ){
				//System.out.println("Time Stopped");
				executor.shutdown();
				isRunning = false;
				runningLabel.setText("Stopped");
			}
		}
		
	}
	private class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			//System.out.println("Resetting field");
			
			ForcePlot.reset();
		}
	}
	private class ResetMoversListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			//System.out.println("Resetting field");
			
			ForcePlot.resetMovers();
		}
	}
	
	private class TimeStart implements Runnable{
		
		//JComponent plot = ForceBoard.window.forcePlot;
		
		@Override
		public void run(){
			try{
				Window.forcePlot.evolve();
				Window.forcePlot.repaint();
			} catch (NullPointerException error){
				//System.out.println("Could not evolve");
			}
			//plot.repaint();
		}
	}
	
	
}