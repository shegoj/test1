package com.rso.gt.frontend.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rso.gt.frontend.Helper;
import com.rso.gt.services.message.ProbeProxy;

public class LoginDailog2 extends JDialog {
	
	
private static final long serialVersionUID = 1L;

/** This is the dialog box which manages connection to the proxy server
 * 
 * written by Olusegun Ojewale
 * 		26/03/2015	
 */

// password field
JPasswordField passwordField = new JPasswordField(10);

// username field
JTextField tusernameField = new JTextField(10);

// login button
JButton loginButton = new JButton("Login");


// proxy connector object
ProbeProxy pb = new ProbeProxy();


// boolean flag to set/check if authentication succeeds
boolean succeeded;

public LoginDailog2 (JFrame parent) {
	
		// call super class
		super (parent, "ORGID Mission Control Login", true);
		
	    JPanel panel = new JPanel();    
	    panel.setLayout(new BorderLayout());
	    
	    JPanel loginControls = new JPanel(new GridBagLayout());
	    GridBagConstraints cs = new GridBagConstraints();
	    
	    cs.fill = GridBagConstraints.HORIZONTAL;
	    
	    cs.gridx = 0;
	    cs.gridy = 0;
	    cs.gridwidth = 2;
	    
	    loginControls.add(new JLabel("User ID"),cs);
	    
	    cs.insets = new Insets(0,60,0,0);  
	    cs.gridx = 1;
	    cs.gridy = 0;
	    
	    loginControls.add(tusernameField,cs); 
	
	    cs.gridx = 0;
	    cs.gridy = 1;
	    cs.gridwidth = 2;
	    cs.insets = new Insets(20,0,0,0); 
	    
	    loginControls.add(new JLabel("Password"),cs);
	    
	    cs.insets = new Insets(20,60,0,0);  
	    cs.gridx = 1;
	    cs.gridy = 1;
	    
	    loginControls.add(passwordField,cs);   
	    
	   // cs.gridx = 0;
	   cs.gridy = 2;
	    
	  loginControls.add(loginButton,cs);   
	  
	   // cs.gridx = 0;
	  
	  // add key bunch Icon
	   cs.gridy = 3;
	  loginControls.add(new JLabel(new ImageIcon(Helper.getImage("/lock.png").getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH))),cs);
	  
	   // display application version	
	   cs.gridy = 4; 
	   JLabel jVersion = new JLabel();
	   jVersion.setFont(new Font("Dialog", Font.ITALIC, 12));
	   jVersion.setForeground(Color.BLUE);
	   jVersion.setText(".              Version 0.01"); // cheat a little right alignment ;-)
	   jVersion.setAlignmentX(RIGHT_ALIGNMENT);
	   
	   loginControls.add(jVersion,cs); 	  
	  // register listeners
	  
	  loginButton.addActionListener( new ActionListener() {
		 
	      public void actionPerformed(ActionEvent e) {
	    	  loginAction();
	      }
	  });
	  
	  loginButton.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
				System.out.println("key pressed");
				loginAction();
			
		}
	});
	  
	  
	  // configuration controls
	  
	 /* JPanel  configControls = new JPanel(new GridBagLayout());
	  
	  GridBagConstraints cs2 = new GridBagConstraints();
	  
	  cs2.fill = GridBagConstraints.HORIZONTAL;
	  cs2.ipadx = 20;
	  
	  cs2.gridx = 0;
	  cs2.gridy = 0;
	  cs2.gridwidth = 2;
	  
	  configControls.add(new JButton("configure>>"));
	  
	 // cs.insets = new Insets(20,0,0,0);  
	  cs2.gridx = 1;
	  cs2.gridy = 0;
	  cs2.ipadx = 20;
	  
	  JLabel a = new JLabel("Proxy server");
	  configControls.add(a);
	  a.setVisible(false);
	  
	 // cs.insets = new Insets(20,0,0,0);  
	  cs2.ipadx = 20;
	  cs2.gridx = 2;
	  cs2.gridy = 0;
	  
	 // configControls.add(new JTextField("10.12.197.10", 10));
	    
	   */

	
		try 
		{
			panel.add(new JLabel(new ImageIcon(Helper.getImage("/italian.png"))),BorderLayout.NORTH);
			//panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(LoginDialog.class.getResource("italian.png").toURI())))),BorderLayout.NORTH);
			panel.add(loginControls,BorderLayout.EAST);
			
			panel.add(new JLabel(new ImageIcon(Helper.getImage("/Thomson1.png"))),BorderLayout.CENTER);
			
			//panel.add(configControls,BorderLayout.SOUTH);
		
			//panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(LoginDialog.class.getResource("/italian.png").toURI())))),BorderLayout.SOUTH);
		 }
		    catch (Exception ex){ ex.printStackTrace();}
		  //  panel.add(new Label("Hello"),BorderLayout.CENTER);
	    
	    
	    this.add(panel);
	    
	    this.setSize(500, 500);
        //pack();
        setResizable(false);
       // setLocationRelativeTo(parent);
        
        this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) { System.exit(0); }
		});
	}
	
	/**
	 * Get username
	 * @return
	 */
	public String getUsername() {
	    return tusernameField.getText().trim();
	}
	
	/**
	 * get password
	 * @return
	 */
	public String getPassword() {
	    return new String(passwordField.getPassword());
	}
	
	public boolean isSucceeded () {
		
		return succeeded;
	}
	
	private void loginAction () 
	{
		
		boolean invalidLogin = false;
		
		if (getUsername() == null || getUsername().length() == 0 || getPassword() == null ||  getPassword().length() == 0 )
		{
			invalidLogin = true;
		}
		else
		{
		
			// get data and send over to server for authentication....
			String rtn = null;
			try
			{
				rtn = pb.sendMessage("one:"+ getUsername() + "___" + getPassword() + ":auth");
				
				if (rtn == null || rtn.length() ==  0)
					throw new Exception("cannot access server");
			}
			
			catch (Exception issueConnectionToServer)
			{
				// most likely cannot connect to server backend...
			    JOptionPane.showMessageDialog(LoginDailog2.this,
		                "Login Error. Contact Administrator to ensure backend is up and running",
		                "Login Error",
		                JOptionPane.ERROR_MESSAGE);
			    return;
			}
			
		    if (rtn.indexOf("true") > -1) 
		    {
		       succeeded = true;
		       
		       if (rtn.indexOf("admin") > -1) // admin rights
		    	   Helper.canStartAndStop = true;
		      
	           Helper.USER = getUsername();
	           System.out.println("user name set to " + getUsername());
		 	   	//System.out.println(pb.sendMessage("bye."));
		       dispose();
		        
		    } 
		    else 
		    	invalidLogin = true;
		}
	    
		
	    if ( invalidLogin)
	    {
	        JOptionPane.showMessageDialog(LoginDailog2.this,
	                "Invalid username or password",
	                "Login",
	                JOptionPane.ERROR_MESSAGE);
	        
	        // reset username and password
	        tusernameField.setText("");
	        passwordField.setText("");
	        succeeded = false;
	    }

	}
}