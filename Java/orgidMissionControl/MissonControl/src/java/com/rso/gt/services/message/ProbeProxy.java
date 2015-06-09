package com.rso.gt.services.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import com.rso.gt.frontend.Helper;

public class ProbeProxy {
	
    Socket socket = null;  
    DataOutputStream os = null;
    DataInputStream is = null;
    
    public ProbeProxy() {
    	
    	int retries = 1;
    	int wait = 5; // 10 seconds
    	int totalAllowedRetries= 3;
    	 
    	boolean retry = true;
    	
    	while (retry)
    	{
    		try 
	    	{
	    			socket = new Socket(Helper.getPorxyServer(), Helper.getPorxyServerPort());
	    			os = new DataOutputStream(socket.getOutputStream());
	    			retry = false;
	    			
	    	}
	    	catch (ConnectException ex) 
	    	{
	    		System.out.println("Connection failed.  "+ wait + " seconds wait before retrying...");
	    		if (retries < totalAllowedRetries) 
	    		{
	    			retries ++;
	    			try 
	    			{
	    				Thread.sleep(wait * 1000);
	    			}
	    			catch(Exception e){}
	    		}
	    		else
	    		{
	    			retry = false;
	    		}
	    	}
	    	catch (Exception ex) {ex.printStackTrace();}
    	}
    }
	
	public String sendMessage (String message) {
	try {
		   os = new DataOutputStream(socket.getOutputStream());
           is = new DataInputStream(socket.getInputStream());
           
           StringBuilder sb = new StringBuilder();
           
           os.writeBytes(message + "\n");       
           String rtnMsg = "";
           
          
           while ((rtnMsg = is.readLine())!= null && rtnMsg.compareToIgnoreCase("--end--") != 0 ) {
               sb.append(rtnMsg + "\n");
           }        
           return  sb.toString();   
       } 	catch (Exception ex) 
       		{ 	ex.printStackTrace();
       			return ""; 
       		}
	}
	
	public void closeConnection() {
		
		try 
		{
			os.close();
			os = null;
			
			is.close();
			is =null;
			
			socket.close();
			socket = null;			
		}
		
		catch (Exception ex){}
	}
	
	public static void main (String args[])throws Exception {
		
		ProbeProxy pb = new ProbeProxy();
		System.out.print(pb.sendMessage("one:10.54.11.31:uptime"));
		System.out.print(pb.sendMessage("one:10.54.154.9:status_all"));
		System.out.println(pb.sendMessage("bye."));
		pb.closeConnection();
	}
}
