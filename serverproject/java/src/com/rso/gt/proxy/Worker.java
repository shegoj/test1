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
	private static final String UPTIME_SCRIPT = "sh /root/capacityplanning/repository/serverproject/scripts/proxyUptime";
	private static final String ALLSTATUS_SCRIPT = "sh /root/capacityplanning/repository/serverproject/scripts/servicestatus.sh";
	
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
								  socket = null;
								  break;
							  }
							  else{ 
								  System.out.println("worker: " + line);
								  String rtn = processData(line);
								  System.out.println("value is: "+  rtn);
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
		
		try
		{
			String data [] = info.split(":");
				
			String source = data[0];
			String destination  = data[1];
			String command = data[2];
				
			switch (command)
			{
				
				case "uptime": // get server uptime
						System.out.println("permissions here " + canExecute("segun"));
						return "3 days";
						//return executeOSCommand(UPTIME_SCRIPT + " " + destination );
				case "auth": // get server uptime
						return authenticateUser (destination );
				case "status_all": // get status of all services
						return "clam not installed\n1\nRTSL APP\n1\nRTSL Web\n--end--";
						//return executeOSCommand(ALLSTATUS_SCRIPT + " " + destination );
				case "start":
						 return executeOSCommand("stop",destination );
				case "stop":
						 return executeOSCommand("stop",destination );
								
				default:		
						return command;
			}				
		}
		catch (Exception ex){ex.printStackTrace();}
		
		return "";
	}
	
	
	private String executeOSCommand(String command) {
		
		System.out.println("calling command " + command);
	        StringBuffer sb = new StringBuffer();
	    
		try
		{
		    Process p = Runtime.getRuntime().exec(command);
		    p.waitFor();
		 
		    BufferedReader reader = 
		         new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		    String line = "";	
		    //line =  reader.readLine();
		    System.out.println("line is " + line);

		    while ((line = reader.readLine())!= null) {
			sb.append(line + "\n");
		    }
			System.out.println("reutn value is " + sb.toString());
			return  sb.append("\n--end--").toString();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return sb.append("\n--end--").toString();
		}

	}


        private String executeOSCommand(String command, String details) {

                System.out.println("calling command " + details);

		String[] dtls = details.split("___");
               	String host = dtls [0];
               	String permission  = dtls [1];
		String service_  = dtls [2];

                StringBuffer sb = new StringBuffer();

                try
                {
		    // check if user has permission to execute
		   
		    System.out.println(command + " " +  host + " " + service_ + " permi " + permission ); 

		    if (!canExecute (permission))
	            {
			sb.append("Permission denied\n");		
			throw new Exception("Invalid permission");
		    }  		
		   
		    System.out.println(command + " " +  host + " " + service_ );

                    /*Process p = Runtime.getRuntime().exec(command + " " +  host + " " + service_ );
                    p.waitFor();

                    BufferedReader reader =
                         new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String line = "";  
                    //line =  reader.readLine();
                    System.out.println("line is " + line);

                    while ((line = reader.readLine())!= null) {
                        sb.append(line + "\n");
                    }
                        System.out.println("return value is " + sb.toString()); */
                        return  sb.append("\n--end--").toString();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        return sb.append("\n--end--").toString();
                }

        }
	
	private String  authenticateUser (String userDetails) {
	
		System.out.println ("in hehe "+ userDetails);
		String user = null; 
		String password = null;
		
		String[] dtls = userDetails.split("___");
		user = dtls [0];
		password = dtls [1];
	
		Users users = new Users();
		
		String result = users.mymap.get(user);
		String perm = users.myperm.get (user);
		System.out.println("authentication module " + result + " perm " + perm);

		if (result == null || result.compareToIgnoreCase(dtls [1]) != 0)
			return "false \n--end--";
		else
		{
			if ( perm != null)
				return "true admin \n--end--";
			else
				 return "true \n--end--";
		}
	}

       private	boolean  canExecute (String userId) {

                Permissions permissions = new Permissions();
		String permissionId = "execute";

                String result = permissions.mymap.get(userId);

                if (result == null || result.indexOf(permissionId) < 0)

                        return  false;//"false \n--end--";
                else
                        return true;// "true \n--end--";

        }

}
