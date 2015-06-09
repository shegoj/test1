package com.rso.frontend.stats;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import com.rso.common.model.ActivityOverview;

public class Statistics  extends JFrame{
	
	
	//
	private Statistics ()  
	{
		
		final JInternalFrame itFrame = new JInternalFrame("test", true, true,true);
				Container contentPane = itFrame.getContentPane();
				contentPane.setLayout(new BorderLayout());

		        // Overview Class initialiser
		        ActivityOverview actOverview = new ActivityOverview();
				
				// SET THE CONTROLS
				ControlPanel  cp = new ControlPanel(actOverview);
				VerticalToolbar jt = new VerticalToolbar();//.getToolBar();
				
				jt.setControlPanel(cp);
				cp.init();
				contentPane.add(cp, BorderLayout.NORTH);
				contentPane.add(jt.getToolBar(), BorderLayout.WEST);
				
				StatsTab mainTab = new StatsTab(actOverview, cp);
				contentPane.add(mainTab, BorderLayout.CENTER);

								
				add(itFrame);
				itFrame.setSize(300, 300);
				itFrame.setVisible(true);
	}
	
	public static void main (String args[]) 
	{
		
		Statistics st = new Statistics();
		st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		st.setVisible(true);
		st.setSize(1200,800);
		
		
	}

}
