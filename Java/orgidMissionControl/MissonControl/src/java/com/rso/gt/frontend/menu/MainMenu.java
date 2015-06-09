package com.rso.gt.frontend.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.rso.common.model.ActivityOverview;
import com.rso.frontend.stats.ControlPanel;
import com.rso.frontend.stats.StatsTab;
import com.rso.frontend.stats.VerticalToolbar;
import com.rso.frontend.tree.ServerTree;
import com.rso.gt.frontend.Helper;

public class MainMenu extends JMenuBar implements ActionListener, ItemListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame mainWindow;
	ActivityOverview actOverview = new ActivityOverview();
	
	String [] controlItems = new  String [] { "Enables Start/Stop", "AutoRefresh", "Enable Window Nofication","ORGID  STATS","Exit" };
	char[] shortcuts = { 'E','A', 'N', 'S','X' };
	
	public MainMenu (JFrame mainWindow) 
	{
		this.mainWindow = mainWindow;
		
		JMenu controlMenu = new JMenu("Controls");		
		for ( int i = 0 ; i < controlItems.length -2 ; ++i) 
		{
			JMenuItem item  = new JCheckBoxMenuItem(controlItems[i]);
			item.addActionListener(this);
			controlMenu.add(item);
		}
		
		// add stats menu
		JMenuItem statItem  = new JMenuItem(controlItems [3], shortcuts [3]);
		controlMenu.add(statItem);
		statItem.addActionListener(this);
		add(controlMenu);
		
		// add exit menu
		JMenuItem item  = new JMenuItem(controlItems [4], shortcuts [4]);
		controlMenu.add(item);
		item.addActionListener(this);
		add(controlMenu);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		System.out.println("Action executed");
		JMenuItem item = (JMenuItem) e.getSource();
	
        
		JInternalFrame itFrame = Helper.getStatsWindow();
		
		String itemLabel = item.getText();
		
		switch (itemLabel)
		{
			case "ORGID  STATS":
			
				if (itFrame == null )
				{
					
					try
					{
						// set busy cursor
						mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
						itFrame = new JInternalFrame("Metrics", true, true,true);
						Container contentPane = itFrame.getContentPane();
						contentPane.setLayout(new BorderLayout());
			 
						VerticalToolbar jt = new VerticalToolbar();//.getToolBar();
						
						// SET THE CONTROLS
						ControlPanel  cp = new ControlPanel(actOverview);
						cp.init();
						contentPane.add(cp, BorderLayout.NORTH);
						contentPane.add(jt.getToolBar(), BorderLayout.WEST);
						jt.setControlPanel(cp);
						
						StatsTab demo = new StatsTab(actOverview, cp);
						contentPane.add(demo, BorderLayout.CENTER);
						
				        JDesktopPane desktop = Helper.getTreeDesktopPane();
				        
				        ServerTree canvas = Helper.getCanvas();
				        canvas.desktop.add(itFrame);
				        
				        JPanel canvasWindow = canvas.secondWindow;
				        canvasWindow.revalidate();
				        canvasWindow.repaint();
				        
						Helper.getCanvas().splitPane.setBottomComponent(canvasWindow);
				        itFrame.setSize(desktop.getWidth(), desktop.getHeight());
						itFrame.setVisible(true);
					}
					
					finally 
					{
						// resetting back the cursor
						mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
		
				}
				else 
				{
					try
					{
						itFrame.setSelected(true);
					}
					catch (Exception ex)
					{
						// do nothing
						ex.printStackTrace();
					}
					itFrame.requestFocus();
				}
				break;
				
				case "Exit":
					   int selectedOption = JOptionPane.showConfirmDialog(mainWindow, "Exit Application?", "Confirm Action", JOptionPane.YES_NO_OPTION);
					   
					   if (selectedOption == JOptionPane.YES_OPTION)
						   System.exit(0);
					   break;
					   
				default:
				       JOptionPane.showMessageDialog(mainWindow,
				               "Menu Item is not currently in use",
				               "Not in Use",
				               JOptionPane.INFORMATION_MESSAGE);				
		    }	
	}

}
