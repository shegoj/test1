package com.rso.frontend.stats;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JViewport;

import com.rso.common.model.ActivityOverview;
import com.rso.gt.frontend.Helper;


/**
 * 
 * @author UC186742
 *
 *This class renders the control buttons used for selecting  CI statistics to display
 */
public class ControlPanel extends JPanel{
	
	ActivityOverview actOverview;
	
	//actionListener
	ControlPanelActions actListener;
	
	// server ns server group controls
	
	private ButtonGroup bGrp = new ButtonGroup();
	
	private JRadioButton jrServerGrp = new JRadioButton("Servr Group");
	private JRadioButton jrServer = new JRadioButton("Server");
	
	private JComboBox <String> jcServerGroup = new JComboBox<String>(); // 
	private JTextField jtServer = new JTextField(10);
	
	private JLabel  jlServerGroup = new JLabel("Server Group");
	private JLabel  jlServer = new JLabel("Server");
	
	// environment controls
	
	private JLabel  jlenVironment = new JLabel("Environment");
	private JComboBox<String>  jcEnvironment = new JComboBox<String>();
	
	//Date fields;
	JComboBox <String> jcYear = new JComboBox<String>(); // 
	JComboBox <String> jcMonth = new JComboBox<String>(); // 
	
	private JLabel  jlYear = new JLabel("Year");
	private JLabel  jlMonth = new JLabel("Month");
	
	private JButton	jView = new JButton("View");
	
	
	
	private final String[] months = { "January", "February", "March",
		      "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	
	private final String[] environment = { "PROD"};	
	
	// components e.g. cpu, memory,IO, sockets
	JComboBox <String> jcCompnents = new JComboBox<String>(); // 
	private JLabel  jlCompnents = new JLabel("Components");
	
	
	public ControlPanel(ActivityOverview actOverview) {
		// TODO Auto-generated constructor stub
		super();
		
		this.actOverview = actOverview;		
		actListener = new ControlPanelActions( this,actOverview);
	}
	
	
	public void init() 
	{	
		
		// add buttons to button group
		
		bGrp.add(jrServerGrp);
		bGrp.add(jrServer);
		
		for (int i = 0 ; i < months.length ; ++i)
			jcMonth.addItem(months[i].toUpperCase());
	
		for (int i = 2015 ; i <2020 ; ++i)
			jcYear.addItem(new Integer(i).toString());
		
		String serverGroup [] = getServerGroup();
		
		for (int i = 0 ; i < serverGroup.length ; ++i)
			jcServerGroup.addItem(serverGroup[i]);
	
		String component [] = getComponent();
		
		for (int i = 0 ; i < component.length ; ++i)
			jcCompnents.addItem(component[i]);
		
		for (int i = 0 ; i < environment.length ; ++i)
			jcEnvironment.addItem(environment[i]);		
		
		 Dimension size_ = new Dimension(10, 20);
		 Dimension control_space = new Dimension(20, 50);
		 
		 //  
		 Dimension vsize_ = new Dimension(5, 15);
		 Component horizontalGlue = new Box.Filler(size_, size_, size_);
		 
		 // put server group and server on one Vertical Group
		 Box bv = Box.createVerticalBox();
		 
		 bv.add(jrServerGrp);
		 bv.add(new Box.Filler(vsize_, vsize_, vsize_));
		 bv.add(jrServer);
		 
		 Box bv2 = Box.createVerticalBox();
		 
		 bv2.add(jcServerGroup);
		 bv2.add(new Box.Filler(vsize_, vsize_, vsize_));
		 bv2.add(jtServer);
		
		// now setting layout to Horiontal
		 Box bh = Box.createHorizontalBox();
		 
		 //bh.add(jlServerGroup);
		 bh.add(bv);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 bh.add(bv2);
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);
		 
		 
		 Box bv3 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv3.add (horizontalGlue);		 
		 bv3.add(jlenVironment);
		 horizontalGlue = new Box.Filler(vsize_, vsize_, vsize_);
		 bv3.add (horizontalGlue);
		 bh.add(bv3);
		 
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 Box bv4 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv4.add (horizontalGlue);		 
		 bv4.add(jcEnvironment);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv4.add (horizontalGlue);
		 bh.add(bv4);

		 //bh.add(bv4);
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);
		 
		 Box bv5 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv5.add (horizontalGlue);		 
		 bv5.add(jlCompnents);
		 horizontalGlue = new Box.Filler(vsize_, vsize_, vsize_);
		 bv5.add (horizontalGlue);
		 bh.add(bv5);
		 
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 Box bv6 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv6.add (horizontalGlue);		 
		 bv6.add(jcCompnents);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv6.add (horizontalGlue);
		 bh.add(bv6);
		 
		 
		/* horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(jlCompnents);
		 bh.add(horizontalGlue);
		 
		 bh.add(jcCompnents);
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);
		 */
		 
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);
		 
		 Box bv7 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv7.add (horizontalGlue);
		 bv7.add(jlYear);
		 horizontalGlue = new Box.Filler(vsize_, vsize_, vsize_);
		 bv7.add (horizontalGlue);
		 bh.add(bv7);
		 
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 Box bv8 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv8.add (horizontalGlue);		 
		 bv8.add(jcYear);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv8.add (horizontalGlue);
		 bh.add(bv8);

		/* bh.add(jlYear);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 

		 bh.add(jcYear);
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);
		 */
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);

		 Box bv9 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv9.add (horizontalGlue);
		 bv9.add(jlMonth);
		 horizontalGlue = new Box.Filler(vsize_, vsize_, vsize_);
		 bv9.add (horizontalGlue);
		 bh.add(bv9);
		 
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 Box bv10 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv10.add (horizontalGlue);
		 bv10.add(jcMonth);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv10.add (horizontalGlue);
		 bh.add(bv10);
		 
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);

		 Box bv11 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv11.add (horizontalGlue);
		 bv11.add(jView);
		 horizontalGlue = new Box.Filler(vsize_, vsize_, vsize_);
		 bv11.add (horizontalGlue);
		 bh.add(bv11);
		 
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		/* Box bv12 = Box.createVerticalBox();
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv12.add (horizontalGlue);
		 bv12.add(jcMonth);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bv12.add (horizontalGlue);
		 bh.add(bv12);
		 
		 
		/* 
		 bh.add(jlMonth);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 
		 bh.add(jcMonth);
		 horizontalGlue = new Box.Filler(size_, size_, size_);
		 bh.add(horizontalGlue);
		 */
		 
		 horizontalGlue = new Box.Filler(control_space, control_space, control_space);
		 bh.add(horizontalGlue);

		 this.add(bh);
		 
		 
		 // enable actions
		 setAction();
	}
	
	private String[] getServerGroup() {
		
	//	String serverGroup [] =  {"Web APPS", "ORGID APP", "ORGID AUTH" , "DATABASE", "RTSL APPS", "RTSL WEB"};
		/*OrgID App           |
		|  OrgID App           |
		|  OrgID App           |
		|  OrgID App           |
		|  OrgID Auth          |
		|  OrgID Auth          |
		|  OrgID Deploy        |
		|  OrgID Prod Database |
		|  OrgID Prod Database |
		|  OrgID Web           |
		|  OrgID Web           |
		|  rtsl App            |
		|  rtsl App            |
		|  rtsl Database       |
		|  rtsl Web            |
		|  rtsl Web  
		*/   
		String serverGroup []  = Helper.getServerGroups().keySet().toArray(new String [0]);
		
		return serverGroup;
	}
	
	private String[] getComponent() {
		
		String components [] =  {"CPU", "Memory", "IO", "Network Traffic", "Storage"};
		
		return components;
	}
	
	
	
	private void setAction () {
	
		jrServerGrp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				jcServerGroup.setEnabled(true);
				jtServer.setEditable(false);
				
			}
		});
		
		
		jrServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				jcServerGroup.setEnabled(false);
				jtServer.setEditable(true);
				
			}
		});
		
		jView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int month = jcMonth.getSelectedIndex();
				String year =  jcYear.getSelectedItem().toString();
				String resource_type =  jcCompnents.getSelectedItem().toString();
				String serverGrp = jcServerGroup.getSelectedItem().toString();
				
				month++; // cater for zero index
				
				// get  list of servers in the Server group
				String servers =  Helper.getServerListFromMemory(serverGrp);
				
				StringBuilder queryString = new StringBuilder();
				queryString.append("from CpuMemoryDao m where m.year = ").append(year).append (" and m.resource_type = '").append(resource_type)
				.append("' and m.monthNum <= ").append( month)
				.append(" and m.serverName in (").append (servers).append(")")
				.append(" order by m.monthNum asc ");
				
				actOverview.renderBarChartCPU( resource_type + " UTILIZATION --" + serverGrp.toUpperCase(), "Month", "%",queryString.toString() );
				actOverview.renderlineGraph(resource_type + " UTILIZATION --" + serverGrp.toUpperCase());
				
			}
		});
	}
}
