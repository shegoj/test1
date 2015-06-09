package com.rso.gt.services.message;


/**
 * 
 * @author Olusegun Ojewale
 * 
 * This interface defines client messenger for sending  information to the server
 *
 */
public interface ClientMessangerInf {
	
	String getSource();
	int messageType ();
	String message(); 
	String getMessageId();

}
