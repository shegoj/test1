package com.rso.gt.frontend.frames.adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.property.Getter;

import com.rso.common.dao.ApplicationStatusDao;
import com.rso.common.dao.GetDataObject;
import com.rso.gt.frontend.Helper;
import com.rso.gt.server.impl.GTServer;
import com.rso.gt.services.message.ProbeProxy;

public class ExecutionButtons implements ActionListener{
	
	private String buttonType;
	private String serviceName;
	private String IPaddress;
	private String serverName;

	
	private JFrame frame;
	
	public static final String START_BUTTON ="Start";
	public static final String STOP_BUTTON ="Stop";	
	
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		if (! canStartStop()) 
		{
	       JOptionPane.showMessageDialog(frame,
	               "You do not have the permission to perform this task",
	           "Permission Denied",
	               JOptionPane.ERROR_MESSAGE);
	      
			return;
	    }
		
	   int selectedOption = JOptionPane.showConfirmDialog(frame, "continue?", "Confirm Action " + serviceName , JOptionPane.YES_NO_OPTION);
	   
	   if (selectedOption == JOptionPane.NO_OPTION)
		   return;
		
		((JButton)evt.getSource()).setEnabled(false); // disable for safety reasons...
	
		// update database.. do this in a new thread to free the UI.
		new Thread() { public void run() {
			updateDBdata () ;
		}
			
		}.start();
		
		
	}
	
	private void updateDBdata () 
	{

		GetDataObject db = new GetDataObject();
		System.out.println("type is " + buttonType );
    	String status =  (buttonType.compareToIgnoreCase(START_BUTTON) ==  0) ? "Starting" :  "Stopping";
    		
    	
    	List <ApplicationStatusDao> result = (List <ApplicationStatusDao>) db.retrieveData("from ApplicationStatusDao m where m.serverName = '" + serverName + "' and m.app = '" + serviceName + "'" );
    	
    	ApplicationStatusDao dao = result.get(0);
    	GTServer server = (GTServer) Helper.getServer(serverName);
    	

    	
    	dao.setStatus(status);
    	
    	db.getSession().beginTransaction();
    	db.getSession().update(dao);
    	db.getSession().flush();
    	
    	Helper.setLastCommand(serverName, serviceName, status);
    	Helper.displayMessage(serviceName + " is " + status + " on " + serverName);
    	
    	// update UI
    	server.setStatus(null);
    	server.refreshFrontend(); 
	}
			
	public void setType(String buttonType) 
	{
		this.buttonType = buttonType;
	}
	
	public void setFrame (JFrame frame)
	{
		this.frame = frame;
	}
	
	public void setService (String serviceName)
	{
		this.serviceName = serviceName.trim();
		System.out.println("service set to " + serviceName);
	}
	
	public void SetLocationIP (String IPaddress)
	{
		this.IPaddress = IPaddress;
	}
	
	public void setServerName (String serverName)
	{
		this.serverName = serverName;
	}
	
	public String getServerName ()
	{
		return this.serverName ;
	}
	
	private boolean canStartStop ()
	{
		return Helper.canStartAndStop;
	}

}
