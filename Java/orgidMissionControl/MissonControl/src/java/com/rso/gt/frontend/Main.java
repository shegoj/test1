package com.rso.gt.frontend;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rso.frontend.tree.ServerTree;
import com.rso.gt.frontend.frames.ORGIDPanel;
import com.rso.gt.frontend.login.LoginDailog2;
import com.rso.gt.frontend.menu.MainMenu;
import com.rso.gt.server.config.ReadConfigFile;
import com.rso.gt.server.def.ServerInt;
import com.rso.gt.services.message.Pinger;

public class Main extends JFrame {
	
	 public static void main(String s[ ]) {
	      JFrame frame = new JFrame("RSO Mission Control");
	      
	      int FRAMEWIDTH = 1300;
	      int FRAMEHEIGHT = 1050;
	      
	      String osType = System.getProperty("os.name");

	      // check if OS is NOT Windows 
	      if (osType.toUpperCase().indexOf("WINDOWS") < 0 ) 
	      {
	    	  // display message and exit
	    	  
	    	  JOptionPane.showMessageDialog(frame,"OS Version Not Supported", osType + " Not Supported",JOptionPane.INFORMATION_MESSAGE);
	    	  System.exit(0);
	      }
	      
	      // launch the log in page
	      
	       // LoginDialog loginDlg = new LoginDialog(frame);
	      	LoginDailog2 loginDlg = new LoginDailog2(frame);
            loginDlg.setLocation(400, 300);	      	
            loginDlg.setVisible(true);
            
            // add Window Listener to ensure the application is stopped when the exit button is clicked. 
            loginDlg.addWindowListener(new WindowAdapter() 
            {
            	  public void windowClosed(WindowEvent e)
            	  {
            	    System.out.println("jdialog window closed event received");
            	  }
            	 
            	  public void windowClosing(WindowEvent e)
            	  {
            	    System.out.println("jdialog window closing event received");
            	  }
            	});

            // if logon successfully
            if(loginDlg.isSucceeded())
            {
            	
			      // read configuration file
			      String xmlConfig = "/config.xml";	   
			      System.out.println(Main.class.getResource(xmlConfig).toString());
			      ArrayList<ServerInt> servers = ReadConfigFile.getCOnfigInfo( xmlConfig); 
			      
			      // copy serverList into Helper class
			      Helper.copyServerDtls(servers);
			      
			      // instantiate tree object
			      frame.getContentPane().add(new ServerTree());
			      
			      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      frame.setJMenuBar(new MainMenu(frame));
			      frame.pack();
			      
			      ORGIDPanel col = new ORGIDPanel("[ORGID Environments, QA, Cw3]");
		
			      frame.add(col, BorderLayout.EAST); // Add col
			      frame.repaint();
			      frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
			      frame.setVisible(true);
			      
			      (new Thread(new Pinger())).start();
            } 
	   } 
}
