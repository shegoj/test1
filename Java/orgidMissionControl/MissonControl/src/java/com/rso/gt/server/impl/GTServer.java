package com.rso.gt.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;

import com.rso.common.dao.ApplicationStatusDao;
import com.rso.common.dao.GetDataObject;
import com.rso.common.dao.StorageInfo;
import com.rso.frontend.charts.Meter;
import com.rso.gt.frontend.Helper;
import com.rso.gt.frontend.frames.MyInternalFrame;
import com.rso.gt.server.def.ServerInt;

public class GTServer implements ServerInt{

	private Map <String, String> serverDetails = new HashMap<String, String>();
	private String serverName, IPaddress,groupName;
	private Map <String, String> services = new HashMap<String,String>();
	
	private ArrayList<String> serviceStatus  = new ArrayList<String>();
	
	private String uptime;
	private String [] statusInfo; // 0- clamAV, 1- Tomcat status, 2 Tomcat Type, 3 Apache status, 4 Apache type, 5
	
	private String tracking ="";
	private String treeInfo ="";
	

	
	@Override
	public ArrayList<String> getStatus() {
		// TODO Auto-generated method stub
		return serviceStatus;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		//this.status = status;
		GetDataObject db = new GetDataObject();
		
		serviceStatus.clear(); // clear  the list
		ArrayList<String> data= new ArrayList<String>();
		
		StringBuilder queryString = new StringBuilder("from ApplicationStatusDao b where b.serverName = '").append(this.getServerName()).append ("'");
		List <ApplicationStatusDao> result = (List <ApplicationStatusDao>) db.retrieveData(queryString.toString());// where b.monthNum = " + month  + " and b.mount in (" + DIRS + ")" );
		
		
		statusInfo = data.toArray(new String[result.size()]);
		
		for (int i =0 ; i < result.size(); ++i )
		{
			System.out.println("settign status :found " + result.get(i).getApp());	
			System.out.println(this.getServerName() + "\n==================");			
			System.out.println("next onnnnn");
			
			String statusVal= null;	
			
			statusVal = result.get(i).getApp() + ":" + result.get(i).getStatus().toUpperCase().trim();
			serviceStatus.add(statusVal);
			System.out.println((i) + " Adding status " + statusVal + " app " + result.get(i).getApp() +  "status is " + result.get(i).getStatus());
			
			//check status of last command
			
			System.out.println("status check " + Helper.getLastCommand() + " "  + this.getServerName());
			
			if (Helper.getLastCommand() !=null && Helper.getLastCommand().length() > 0)
			{
				String command_ [] = Helper.getLastCommand().split(":");
				
				if ( command_ [0].compareToIgnoreCase(this.getServerName()) == 0 &&
						command_ [1].compareToIgnoreCase(result.get(i).getApp()) == 0 &&
						command_ [2].compareToIgnoreCase(result.get(i).getStatus()) != 0)
				{
					System.out.println("command is " + Helper.getLastCommand() ) ;
					Helper.displayMessage(result.get(i).getApp() + " is " +  result.get(i).getStatus() +   "  on "  + this.getServerName());
					Helper.setLastCommand(null, null, null);
				}
				
			}
				
		}	
	}

	@Override
	public String getUptime() {
			// TODO Auto-generated method stub
		
		System.out.println(serverName + " my uptime is " + uptime);
		if (uptime != null) 
		{
		 	Helper.offlines = Helper.offlines.replace(this.serverName, "");
		 	
			if (Helper.checked.indexOf(getServerName()) < 0){
				Helper.checked = Helper.checked + "," + this.serverName;
				
			}
		 	
		 	return uptime;
		}
		else
		{
			if (Helper.offlines.indexOf(getServerName()) < 0) 
			{
				Helper.offlines = Helper.offlines + "," + this.serverName;
				Helper.checked = Helper.checked + "," + this.serverName;
				
			}
			return "server is offline:";
		}

	}
	
	@Override
	public void setUptime(String uptime) {
		// TODO Auto-generated method stub
		
		System.out.println("uptime valur received is " + uptime);
		if (uptime != null && uptime.trim().length() > 0)
			this.uptime = uptime;
	}

	@Override
	public String getPortalAppStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPortalWebStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGTAppStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGTWebStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startPortalApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopPortalApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPortalWeb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopPortalWeb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGTApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopGTApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGTWeb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopGTWeb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getserverGroup() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public  void setGroupName(String groupName) {
		// TODO Auto-generated method stub
		this.groupName = groupName;
	}
	
	@Override
	public  String getGroupName() {
		// TODO Auto-generated method stub
		return this.groupName;
	}

	@Override
	public void setConfigInfo(Map map) {
		this.serverDetails = map;
		extractInfo();
		
	}

	@Override
	public void extractInfo() {

		for(Map.Entry<String, String> dtls  : serverDetails.entrySet()){   
			System.out.println("Key is " + dtls.getKey());
            switch (dtls.getKey().trim()) {
				case "IP":
							this.setIPAddress(dtls.getValue());
							break;
				case "name":
							this.setServerName(dtls.getValue());  
							break;
							
				// list of services
				// <!-- Portal APP 1, GT APP 2, Portal WebSvr 3, ClamAV 4, scheduler 5 -->
							
				case "CLAMAV":	
							services.put("ClamAV",   "ClamAV        ");
							//tracking = tracking + "0:";
							break;
							
				case "ORGID WEB":	
					 		services.put("ORGID WEB",   "ORGID WEB    ");
					 		//tracking = tracking + "3:";
					 		break;	

				case "RTSL APP":	
							services.put("RTSL APP",    "RTSL APP     ");
					 		//tracking = tracking + "3:";
					 		break;
				case "RTSL WEB":	
					services.put("RTSL WEB",    "RTSL WEB     ");
			 		//tracking = tracking + "3:";
			 		break;
			 		
				case "ORGID APP":	
					services.put("ORGID APP",    "ORGID APP     ");
			 		//tracking = tracking + "3:";
			 		break;
					 		
				default:	
						services.put("No APP",    "NO APP");
							//tracking = tracking + "3:";
			 		break;					 	
            }
		}
	}

	@Override
	public void setServerName(String serverName) {
		// TODO Auto-generated method stub
		this.serverName = serverName;
		
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return this.serverName;
	}

	@Override
	public String getIPAddress() {
		// TODO Auto-generated method stub
		return this.IPaddress;
	}

	@Override
	public void setIPAddress(String IPaddress) {
		// TODO Auto-generated method stub
		
		this.IPaddress = IPaddress;
		
	}

	@Override
	public Map<String, String> getServices() {
		// TODO Auto-generated method stub
		return services;
	}

	@Override
	public void refreshFrontend() {
		// TODO Auto-generated method stub
		try{
			Map <String, JInternalFrame> frames  =Helper.getFramesRef();
			if (frames != null) {
				MyInternalFrame frame_ = (MyInternalFrame)frames.get(this.treeInfo);
				if (frame_ != null ) {
				  	frame_.refreshControls();
				  	frame_.setVisible(true);
				  	frame_.revalidate();	        		  	
				  	frame_.repaint();
				}
			}
		}
		
		catch (Exception ex) {ex.printStackTrace();}
	}

	@Override
	public String getTreeInfo() {
		// TODO Auto-generated method stub
		return this.treeInfo;
	}

	@Override
	public void setTreeInfo(String treeInfo) {
			this.treeInfo = treeInfo;
		
	}

}
