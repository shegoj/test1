package com.rso.gt.proxy;


	
	import java.io.*;
	import java.net.*;
	
	public class ClientTest {
	    public static void main(String[] args) {
	// declaration section:
	// smtpClient: our client socket
	// os: output stream
	// is: input stream
	        Socket smtpSocket = null;  
	        DataOutputStream os = null;
	        DataInputStream is = null;
	// Initialization section:
	// Try to open a socket on port 25
	// Try to open input and output streams
	        try {
	            smtpSocket = new Socket("10.12.192.178", 9999);
	            os = new DataOutputStream(smtpSocket.getOutputStream());
	            is = new DataInputStream(smtpSocket.getInputStream());
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: hostname");
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to: hostname");
	        }
	// If everything has been initialized then we want to write some data
	// to the socket we have opened a connection to on port 25
	    if (smtpSocket != null && os != null && is != null) {
	            try {
	            	os.writeBytes("Hello world\n");
					String senderOK = is.readLine();
					System.out.println(senderOK);
	            }
	            catch (Exception ex){}
	    }
	   }
}
