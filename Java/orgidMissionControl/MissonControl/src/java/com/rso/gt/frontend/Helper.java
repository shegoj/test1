package com.rso.gt.frontend;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import com.rso.common.dao.GetDataObject;
import com.rso.common.dao.ServerDao;
import com.rso.frontend.tree.ServerTree;
import com.rso.gt.server.def.ServerInt;


public class Helper {

static List<ServerInt> localServersCopy;
private static final String PROXY_SERVER = "10.12.192.178";
private static final int PROXY_SERVER_PORT = 9999;

private static JDesktopPane treeDesktopPane;
private static JInternalFrame statsWindow;
//private static JSplitPane canvas ;
private static ServerTree mainCanvas ;

public static String offlines ="";
public static String checked ="";  //list of servers that have been checked.
public static int  PROXY_PROBE_INTVL = 1; // 1 minutes

// service status info
public static String CLAmAV_STATUS = "running";
public static String SERVICE_STATUS = "0"; 			
public static String PORTAL_APP = "Portal App";
public static String RTSL_APP = "RTSL APP"; 
public static String PORTAL_AUTH = "Portal  auth"; 
public static String PORTAL_WEB = "Portal Web";
public static String RTSL_WEB = "RTSL Web"; 
public static String USER="";

static  TrayIcon trayIcon;

static Map <String, JInternalFrame> framesRef;
private static HashMap<String, ArrayList<String>> serverGroup;

private static GetDataObject globalGB ;
private static JTabbedPane mainTab;

private static String lastCommand;
public static boolean canStartAndStop = false;

public static enum APP_STATUS {
	
	RUNNING,DOWN,STARTING, STOPPING
}


/** get server object using server name
 * 
 * @param serverId
 * @return
 */

static  
	{
		try 
		{
			ImageIcon icon = new ImageIcon(Helper.class.getResource("/greenIcon.jpg"));
			trayIcon = new TrayIcon(icon.getImage());
			trayIcon.setImageAutoSize(true);
			
			globalGB = new GetDataObject();
		}
	
		catch (Exception ex){ ex.printStackTrace();} 
	}
 public static ServerInt getServer(String serverId) 
 {
	 
	 for (int i =0 ; i < localServersCopy.size() ; ++i) 
	 {
		 System.out.println("checking..." + localServersCopy.get(i).getServerName() );
		 if (localServersCopy.get(i).getServerName().compareToIgnoreCase(serverId) == 0)
		 {
			  System.out.println("found " + localServersCopy.get(i).getServerName() );
			  return localServersCopy.get(i);	
		 }
				 
	 }
	 return null;
	 
 }
 /*
  * Get all copies of server objects
  */
 public static List<ServerInt> getServers() {
	 return localServersCopy;
 }
 public static void copyServerDtls(List<ServerInt> servers)
 {
	 localServersCopy = servers; 
 }
 
 public static void setFramesRef(Map <String, JInternalFrame> frames)
 {
	 framesRef = frames; 
 }
 
 public static Map <String, JInternalFrame> getFramesRef()
 {
	 return framesRef; 
 }
 
 public static String getPorxyServer() {
	 return PROXY_SERVER;
 }
 
 public static int getPorxyServerPort() {
	 return PROXY_SERVER_PORT;
 }
 
 
 public static String getServerNameFromTreeLabel (String treeLabel) {
	 
	 String [] treePathText = treeLabel.split(","); 
	// extract server info to get frame title and other information relating to the server
	 
     String serverName = treePathText[2].trim();
     serverName = serverName.substring(0,serverName.length()-1);
     
     return serverName;
    	
 }
 
 // set System Tray Information 
 
 public static void displayMessage(String message) 
 {
		
	 if (SystemTray.isSupported()) 
	 {
		 
         final SystemTray systemTray = SystemTray.getSystemTray();
		 trayIcon.displayMessage("ORGID Status Information",message,TrayIcon.MessageType.INFO);
		 System.out.println("displaying information now...." + message );
		 
		 try {
			 systemTray.add(trayIcon);
			 Thread.sleep(1000);
             trayIcon.displayMessage("ORG ID APP Status", message,TrayIcon.MessageType.INFO);
		 }
		 catch (Exception ex){}
	 }
		
 }
  
  public static void setTreeDesktopPane (JDesktopPane pane)
  {
	  treeDesktopPane = pane;
  }

  public static JDesktopPane getTreeDesktopPane ()
  {
	  return treeDesktopPane;
  }
  public static void setStatsWindow (JInternalFrame window)
  {
	  statsWindow = window;
  }

  public static JInternalFrame getStatsWindow ()
  {
	  return statsWindow;
  }
  
  public static void setCanvas (ServerTree cCanvas)
  {
	  mainCanvas = cCanvas;
  }

  public static ServerTree getCanvas ()
  {
	  return mainCanvas;
  }
  
  /**
   * fetch servergroups and corresponding server names into a HashSet/ArrayList
   */
  private static void setServerGroups()
  {
	  GetDataObject db = new GetDataObject();
	  serverGroup = new HashMap<String, ArrayList<String>>();
	  
	  String query = "from ServerDao";
	  List <ServerDao> result = (List <ServerDao>) db.retrieveData("from ServerDao m order by m.groupName ");
	  
	  for (int i =0 ; i < result.size(); ++i )
	  {
			
		  ServerDao serverDao = result.get(i);
		  
		  String server = serverDao.getServerId();
		  String serverGrp = serverDao.getGroupName();
		  
		  if (serverGroup.containsKey(serverGrp))
		  {
			  serverGroup.get(serverGrp).add(server);
		  }
		  else
		  {
			  ArrayList<String> list = new ArrayList<String>();
			  list.add(server);
			  serverGroup.put(serverGrp, list);
			  System.out.println(serverGrp);
		  }
	  }
  }
  
  public static HashMap<String, ArrayList<String>> getServerGroups()
  {
	  	if (serverGroup == null)
	  		setServerGroups();
	  	
		return serverGroup;
  }
  
  public static String getServerListFromMemory (String serverGrp) 
  {
	  
	  StringBuilder bs = new StringBuilder();
	  
	  ArrayList<String> servers = serverGroup.get(serverGrp);
	  
	  if (servers != null && servers.size() > 0)
	  {
		  for  (int i = 0 ; i <servers.size(); ++i)
		  {
			  bs.append("'").append(servers.get(i)).append("'").append(",");
		  }
	  }
	  
	  if (bs.length() > 0 ) 
		  bs.deleteCharAt(bs.length() -1);
	  // remove last comma if added
	  
	  return bs.toString();
  }
  
  public static void main (String args [])
  {
	  HashMap<String, ArrayList<String>> made = getServerGroups();
	  String mg [] = (made.keySet().toArray(new String [0]));
	 System.out.println("values " + getServerListFromMemory((mg [4])));
	  
  }
  
  public static GetDataObject getDbHandler()
  {
	  return globalGB;
  }
  
  public static int getdataLocfromList (String lookup, List<String> list)
  {
  	for (int i =0 ; i < list.size(); ++ i)
  	{
  		System.out.println("comparing " + lookup + " and " + list.get(i)) ;
  		if  (list.get(i).compareToIgnoreCase(lookup) == 0)
  			 return i;
  	}
  	
  	return -1;
  }
  
  public static void setTab (JTabbedPane mainTabObj)
  {
	  Helper.mainTab = mainTabObj;
  }
  
  public static JTabbedPane getTab ()
  {
	  return Helper.mainTab ;
	 
  }
  
  public static void setLastCommand (String server, String service, String status)
  {
	  if (server == null)
	  {
		  lastCommand = null;
		  return;
	  }
	
	  	
	  lastCommand = server + ":" + service + ":" + status;
  }
  
  public static String getLastCommand ()
  {
	  return lastCommand;
  }
  
  public static Image getImage (String location)
  {
	  	Image img = null;
		try
		{
		  	Toolkit tk = Toolkit.getDefaultToolkit();
			URL url = Helper.class.getResource(location);
			img = tk.createImage(url);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return img;
  }
  
}
