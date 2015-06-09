package com.rso.gt.proxy;

import java.net.ServerSocket;
import java.net.Socket;


/**
 * 
 * @author UC186742
 *
 *This class is the main class a client connects to to communicate with a server on the network
 */
public class MainProxy {
	

	static ServerSocket echoServer;
	static int counter = 1;
	static int PROXY_PORT = 9999;
		
	public static void listen () {

		try 
		{
			echoServer = new ServerSocket(PROXY_PORT);	

			System.out.println("server is Listening on port " + PROXY_PORT);
				
			while (true) 
			{
		
				Socket sock = echoServer.accept();
				new Thread( new Worker(sock)).start();
			}
		}
		
		catch (Exception ex){}
		
	}
	
	public static void closeConnection() {
		
		try 
		{
			 if (echoServer != null && !echoServer.isClosed()) 
			 {
				 echoServer.close();
				 echoServer = null;
			 }
		}
		
		catch (Exception ex){}
	}
	
	public static void main (String args[]) {
		
		new MainProxy().listen();
	}

}
