package com.rso.gt.proxy;

import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;



/***

Class loads user list and permissions

**/

public class Users {

	HashMap<String,String> mymap; // map stores user details
	HashMap<String,String> myperm; // map stores perm details
	final String CONFIGLOC = "/root/capacityplanning/repository/serverproject/user.properties";
	final String PERMISSIONSLOC = "/root/capacityplanning/repository/serverproject/permissions.properties";
	
//class constructor
	public Users () {
		
		 mymap = new HashMap<String, String>();
		 myperm = new HashMap<String, String>();
		 Properties properties = new Properties();
		 Properties properties2 = new Properties();
		 

		    try
		    {
			File file = new File(CONFIGLOC);
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream); fileInputStream.close();		
			
			// load permission info
			File file2 = new File(PERMISSIONSLOC);
                        FileInputStream fileInputStream2 = new FileInputStream(file2);
                        properties2.load(fileInputStream2); fileInputStream2.close();	
		    }
		    catch (Exception e) {
				e.printStackTrace();

		    }
		    
		    for (String key : properties.stringPropertyNames()) {
		        String value = properties.getProperty(key);
		        mymap.put(key, value);
		    }	

                    for (String key : properties2.stringPropertyNames()) {
                        String value = properties2.getProperty(key);
			System.out.println("perm: " + key  + ":" + value);
                        myperm.put(key, value);
                    }
	}
	
	 public static void main (String args []) {
		
		Users users = new Users();

		System.out.println(users.mymap.size());
	}
} 
