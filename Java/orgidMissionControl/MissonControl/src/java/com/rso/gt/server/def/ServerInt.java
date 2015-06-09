package com.rso.gt.server.def;

import java.util.ArrayList;
import java.util.Map;

public interface ServerInt {
	
	// return server status
	ArrayList<String> getStatus ();
	
	// return server status
	void setStatus (String status);	
	
	// return server uptime
	String getUptime();
	
	void setUptime(String uptime);
	
	// get portal app status
	String getPortalAppStatus();

	// get web app status
	String getPortalWebStatus();
	
	// get GT app status
	String getGTAppStatus();

	// get GT web status
	String getGTWebStatus();
	
	String getserverGroup();
	
	void setServerName(String serverName);
	
	String getServerName();
	String getIPAddress();
	
	void setIPAddress(String IP);
	
	
	void setConfigInfo (Map map);
	
	void extractInfo ();
	
	Map<String, String> getServices();
	
	
	void setGroupName (String groupName);
	
	String getGroupName();
	
	void startPortalApp ();
	void stopPortalApp();

	void startPortalWeb ();
	void stopPortalWeb();

	void startGTApp ();
	void stopGTApp();

	void startGTWeb ();
	void stopGTWeb();	
	
	void refreshFrontend ();
	
	String getTreeInfo();
	void setTreeInfo(String treeInfo);

}
