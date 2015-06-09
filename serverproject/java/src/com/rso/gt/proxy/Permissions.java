package com.rso.gt.proxy;

import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;



/***

Class loads user list and permissions

**/

public class Permissions {

	HashMap<String,String> mymap; // map stores user details
	final String CONFIGLOC = "/root/capacityplanning/repository/serverproject/permissions.properties";
	
//class constructor
	public Permissions () {
		
		 mymap = new HashMap<String, String>();
		 Properties properties = new Properties();
		 

		    try 
		    {
			File file = new File(CONFIGLOC);
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream); fileInputStream.close();		
		     // properties.load(Users.class.getResourceAsStream(CONFIGLOC));
		    }
		    catch (Exception e) {
				e.printStackTrace();

		    }
		    
		    for (String key : properties.stringPropertyNames()) 
		    {
		        String value = properties.getProperty(key);
		        mymap.put(key, value);
		    }	

	}
	
	 public static void main (String args []) {
		
		Permissions permissions  = new Permissions ();

		System.out.println(permissions.mymap.size());
	}
} 
