package com.rso.gt.services.message;

import java.util.List;

import com.rso.gt.frontend.Helper;
import com.rso.gt.server.def.ServerInt;

public class Pinger  implements Runnable{
	
/**
 * 
 * This class creates a thread which requests for servers and services information from the Proxy server at intervals.
 * 
 * 
 */

ProbeProxy pb = new ProbeProxy();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Pinger starts...");
		boolean LOOPON = true;
		List<ServerInt> servers = Helper.getServers();
		
		
		//continue forever
		while (LOOPON) {
			
			for (int i = 0 ; i < servers.size() ;++ i) 
			{ 
				ServerInt server = servers.get(i);
				String IP = server.getIPAddress();
				
				if (IP != null) 
				{
					System.out.println("Pinger sends  + " + "aa:" + IP + ":uptime");
					server.setUptime("3 days");
					server.getUptime();
					 if (Helper.offlines.indexOf(server.getServerName()) < 0)  
					 {
						 server.setStatus(pb.sendMessage("aa:" + IP + ":status_all" ));
						 server.refreshFrontend(); 
					 } 	
					 else
						 Helper.displayMessage(server.getServerName() + " cannot be accessed!");						
				}
			}
			try 
			{
				Thread.sleep(Helper.PROXY_PROBE_INTVL * 1000 * 60);
			}
			
			catch (Exception ex){}
			
		}
		
	}

}
