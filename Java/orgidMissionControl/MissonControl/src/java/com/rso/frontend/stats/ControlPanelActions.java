package com.rso.frontend.stats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rso.common.model.ActivityOverview;

/**
 * 
 * @author UC186742
 * 
 * Action class for Control Panel Objects
 *
 */
public class ControlPanelActions implements ActionListener {
	
	ActivityOverview actOverview;
	ControlPanel controlPanel;
	
	public ControlPanelActions (ControlPanel controlPanel, ActivityOverview overview)
	{
		this.actOverview = overview;
		this.controlPanel = controlPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		System.out.println("yes!!!");
		System.out.println(actOverview.getMeterRecordCount());
		
		int month = controlPanel.jcMonth.getSelectedIndex();
		String year =  controlPanel.jcYear.getSelectedItem().toString();
		String resource_type =   controlPanel.jcCompnents.getSelectedItem().toString();
		
		month++; // cater for zero index
		
		String queryString = "from CpuMemoryDao m where m.year = " + year + " and m.resource_type = '"
				+ resource_type  + "' and m.monthNum <= " + month  + " order by m.monthNum asc ";
		
		actOverview.renderBarChartCPU( resource_type + " Util", "Month", "%",queryString );
		actOverview.renderlineGraph(resource_type + " Util");
		
		
		
	}

}
