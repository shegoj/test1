package com.rso.gt.server.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.jfree.io.FileUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rso.common.dao.ApplicationStatusDao;
import com.rso.gt.frontend.Helper;
import com.rso.gt.server.def.ServerInt;
import com.rso.gt.server.impl.GTServer;
import com.rso.gt.services.message.ProbeProxy;

public  class ReadConfigFile {
	/**
	 * Read configuration information from properties file
	 * only one instance of  servers are kept;
	 */
	
	
	static ArrayList  <ServerInt> servers;
	
	private static void config (String xmlConfig) {
		
		// parse xml configuration file.
		
		servers = new ArrayList<ServerInt>();
		File xmlfile = null;
		
		try  {
			//FileUtils utls_;
			//xmlfile = FileUtils.toFile(ReadConfigFile.class.getResourceAsStream("/config.xml"));
			/*FileUtilities file_;
				System.out.println("here we go");
				System.out.println(FileUtilities.findFileOnClassPath("/config.xml").getAbsoluteFile());
				xmlfile = FileUtilities.findFileOnClassPath("config.xml");
				*/
				//xmlfile = new File(xmlConfig.toURI()); 
			//	if (!xmlfile.exists())
			//		throw new Exception("Configuration file is not found");
				
				// continue
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(ReadConfigFile.class.getResourceAsStream(xmlConfig));
			 
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();
				
				// read root element
				NodeList nList = doc.getElementsByTagName("group");
				
				// now loop over individual group 
				ProbeProxy pb = new ProbeProxy();
				
				//
				for (int temp = 0; temp < nList.getLength(); temp++) {
					
						Node nNode = nList.item(temp);
						
						String groupName = nNode.getAttributes().item(0).getTextContent();
			 
						NodeList serverList = nNode.getChildNodes(); // list of servers from XML
						
						for ( int counter =0; counter < serverList.getLength() ;++counter) 
						{
							
							if (serverList.item(counter).getNodeName().compareToIgnoreCase("server") ==0 ) {
				
								// instantiate GT server instance;
								ServerInt gtServer = new GTServer();
								gtServer.setGroupName(groupName); // set the server group Name
								System.out.println("itemCount: " + serverList.item(counter));
								
								gtServer.setConfigInfo(setServerInfo(serverList.item(counter)));
								servers.add(gtServer);
								
								// setting uptime infor for the server.. this needs  to be moved somewhere else
							}
						}

				}
				
				// close of prober connection
				
				try 
				{
						pb.sendMessage("bye.");
						pb.closeConnection();
				}
				catch (Exception ex){}
				
				
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		
	}
	public static synchronized ArrayList<ServerInt> getCOnfigInfo (String xml) {
		
		if (servers == null) 
		{
			config(xml);
			return servers;
		}
		else
			return servers;		
	}
	
	public static void main (String args[]) {
		
		String xmlConfig = "C:\\development\\java\\workspace\\orgidMissionControl\\MissonControl\\config.xml";	
/*		 ServerInt tt= ReadConfigFile.getCOnfigInfo(xmlConfig).get(0);
		 ServerInt tt2= ReadConfigFile.getCOnfigInfo(xmlConfig).get(1);
		 
		 tt.extractInfo();
		 System.out.println(tt.getIPAddress() + ": " +  tt.getGroupName() + " : " + tt.getServerName());*/
		
	}
	
	private static Map setServerInfoq (Node serverNode) {
		
		//
		System.out.println("setting server node...." );
		Map <String, String> data = new HashMap<String, String>(); 
		String tempNodeName="";
		
		String name ="";
		
		NodeList serverNodeInfo =  (NodeList) serverNode.getChildNodes();	
		for ( int i =0 ; i < serverNodeInfo.getLength(); ++i ) {		 
			Node node = serverNodeInfo.item(i);
			if (node.getNodeName().compareTo("#text") !=0) { // read value 			
				tempNodeName = node.getNodeName();				
				if (tempNodeName.compareToIgnoreCase("apps") == 0) { // apps group
					
					NodeList appNodes = node.getChildNodes();
					for (int j = 0; j < appNodes.getLength() ; ++j) {
						
						if (appNodes.item(j).getNodeName().compareTo("#text") !=0) 
							data.put(appNodes.item(j).getTextContent() , appNodes.item(j).getTextContent());
							System.out.println("putting " + appNodes.item(j).getTextContent() );
					}
				}
				else
				{
					data.put(node.getNodeName() , node.getTextContent());
					name =node.getTextContent();
					//System.out.println("--> " + node.getNodeName()  + " " + node.getTextContent());
				}
			}
		
		}
		
		//getNameandIP(serverNode);
		return data;
	}
	
	private static Map setServerInfo (Node serverNode) {
		
		//
		System.out.println("setting server node...." );
		Map <String, String> data = new HashMap<String, String>(); 
		String tempNodeName="";
		
		String result = null;
		
		NodeList serverNodeInfo =  (NodeList) serverNode.getChildNodes();	
		for ( int i =0 ; i < serverNodeInfo.getLength(); ++i ) 
		{		 
			Node node = serverNodeInfo.item(i);
			if (node.getNodeName().compareTo("#text") !=0) 	
			{
				if (result == null)
					result = node.getTextContent();
				else
					result = result + ":" +node.getTextContent();
			}
		}
		
		String IP= "";
		String name= "";
		
		String [] info = result.split(":");
		
		IP= info[0];
		name = info [1].toUpperCase();
		
		StringBuilder queryString = new StringBuilder("from ApplicationStatusDao b where b.serverName = '").append(name).append ("'");
		List <ApplicationStatusDao> result2 = (List <ApplicationStatusDao>) Helper.getDbHandler().retrieveData(queryString.toString());// where b.monthNum = " + month  + " and b.mount in (" + DIRS + ")" );
		
		for (int i = 0; i < result2.size() ;++ i)
		{
			data.put(result2.get(i).getApp(),result2.get(i).getApp());
		}
		
		data.put("IP", IP);
		data.put("name", name);
		//System.out.println("result is " + getNameandIP(serverNode));
		System.out.println("map size for " + name+ " is " + data.size());
		
		return data;
		
	}
	
}
