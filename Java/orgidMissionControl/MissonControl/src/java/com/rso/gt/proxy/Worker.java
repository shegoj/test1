package com.rso.gt.proxy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * 
 * @author Olusegun Ojewale
 * 
 * Frontend connects to this class and execute remote command on it behalf. 
 *
 */
public class Worker implements Runnable {
	
	// constructor accepts socket connection created by server at the point where a client connects.
	Socket socket;
	
	
	//list of scripts to call
	
	// uptime script
	private static final String UPTIME_SCRIPT = "/root/capacityplanning/repository/serverproject/scripts/proxyUptime";
	
	public Worker (Socket socket) {
		this.socket = socket;
	}
	
	public void executeCommand() {
		
		  String line;
		  DataInputStream is;
		  PrintStream os;
		  
		  try {

				 System.out.println("connected");
				 is = new DataInputStream(socket.getInputStream());
				 os = new PrintStream(socket.getOutputStream());
				 
				 // As long as we receive data, echo that data back to the client.
				while (true) {
							  line = is.readLine();
							  if (line .compareToIgnoreCase("bye.") ==0 ){
								  os.println("bye"); 
								  socket.close();
								  break;
							  }
							  else{ 
								  System.out.println("worker: " + line);
								  String rtn = processData(line);
								  os.println(rtn); 
							  }
			           }
			        }   
			    catch (IOException e) {
			           System.out.println(e);
			        }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		executeCommand();
	}
	
	
	private String processData(String info) {
		
		try{
				String data [] = info.split(":");
				
				String source = data[0];
				String destination  = data[1];
				String command = data[2];
				
				switch (command) {
				
				case "uptime": // get server uptime
								return executeOSCommand(UPTIME_SCRIPT + " " + destination );
				default:		
								return command;
				}				
			}
		catch (Exception ex){}
		
		return "";
	}
	
	
	private String executeOSCommand(String command) {

		command = "echo %date%";
	    StringBuffer sb = new StringBuffer();
	    
		try{
			Process p = Runtime.getRuntime().exec(command);
		    p.waitFor();
		 
		    BufferedReader reader = 
		         new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		    String line = "";	
		    while ((line = reader.readLine())!= null) {
			sb.append(line + "\n");
		    }
			return sb.toString();
		}
		catch (Exception ex) {ex.printStackTrace();	return sb.toString();}

	}

}
