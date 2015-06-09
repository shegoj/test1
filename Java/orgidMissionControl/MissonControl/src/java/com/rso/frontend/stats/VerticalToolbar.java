package com.rso.frontend.stats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.rso.gt.frontend.Helper;
import com.rso.gt.frontend.login.LoginDialog;

public class VerticalToolbar  {
	
	private final static int ICON_WIDTH = 50;
	private final static int ICON_HEIGHT = 50;
	
	private ControlPanel cp;
	
	JToolBar toolbar;

	public VerticalToolbar () 
	{
		toolbar = new JToolBar(JToolBar.VERTICAL);
		
		String icons [] = {"/cpu.jpg", "/memory.jpg", "/cap1.jpg","/bars.jpg", "/broswer.jpg", "/hazard.jpg"};
		String titles [] = {"CPU   ", "MEMORY   ", "STATS   ","OVERVIEW  ", "Data Up BROWSER INFO   ", "Alerts"};
		
		try
		{
			
			for (int i = 0 ; i < icons.length; ++i)
			{
				JButton but = new JButton( new ImageIcon(Helper.getImage(icons[i]).getScaledInstance(ICON_WIDTH, ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH)));
				but.setToolTipText(titles[i]);
				but.setVerticalTextPosition(SwingConstants.BOTTOM);
				but.setHorizontalTextPosition(SwingConstants.CENTER);
				but.setText(titles[i].substring(0,5));
				
						
				// temporarily disable some of the buttons
				if (i < 2)
					but.setEnabled(false);
					
				
				toolbar.add(but);
				but.addActionListener(toolbarAction);
			}
		}
		catch (Exception ex) {ex.printStackTrace();}
	}
	
	public JToolBar getToolBar()
	{
		return toolbar;
	}
	
	public void setControlPanel(ControlPanel cp)
	{
		this.cp = cp;
	}
	
	
	ActionListener toolbarAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton but = (JButton) e.getSource();
			JTabbedPane tab = Helper.getTab();
			
			String title = but.getText();
			
			switch (title) 
			{
			case "Alert":
					tab.setSelectedIndex(3);
					cp.setVisible(false);
					break;
					
			case "OVERV":
					tab.setSelectedIndex(0);
					cp.setVisible(false);
					break;
				
			case "STATS":
					tab.setSelectedIndex(2);
					cp.setVisible(true);
					break;
				
			case "Data ":
					tab.setSelectedIndex(1);
					cp.setVisible(false);
					break;				
			}

		}
	};
	//tp.setSelectedIndex(0);
		
	
}
