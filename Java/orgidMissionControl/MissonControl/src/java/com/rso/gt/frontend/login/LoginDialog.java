package com.rso.gt.frontend.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.rso.gt.frontend.Helper;
import com.rso.gt.services.message.ProbeProxy;

public class LoginDialog extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnRegister;
    private boolean succeeded;
    
	ProbeProxy pb = new ProbeProxy();

    public LoginDialog(Frame parent) {
        super(parent, "ORGID Mission Control Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        BufferedImage myPicture =  null;
        try {
        	System.out.println(LoginDialog.class.getResource("/italian.png").toURI());
        	myPicture = ImageIO.read(new File(LoginDialog.class.getResource("/italian.png").toURI()));
        }
        catch (Exception ex){ex.printStackTrace();}
        
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
        
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
       // cs.ipady = 30;
        cs.weighty = 3.0;
        panel.add(picLabel, cs);


        //cs.ipady = 0;
        cs.weighty = 1.0;
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
       // cs.insets = new Insets(10,0,0,0);  //top padding
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(10);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(10);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	loginAction();
            }
        });
        
       
       btnLogin.addKeyListener (new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              int key = e.getKeyCode();
              if (key == KeyEvent.VK_ENTER) {
            	  	loginAction();
              }
            }});
        
        btnRegister = new JButton("Registration");
        btnRegister.addActionListener(new ActionListener() {
        	
        	public void actionPerformed (ActionEvent e) {
        		 JOptionPane.showMessageDialog(LoginDialog.this,
                         "Send an email to GRC- service Ops",
                         "Registration",
                         JOptionPane.INFORMATION_MESSAGE);
        	}});
			

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
        bp.add(btnRegister);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
    
    
   private void loginAction () {

       System.out.println("user11 name set to " + getUsername());
	   String rtn = pb.sendMessage("one:"+ getUsername() + "___" + getPassword() + ":auth");
       if (rtn.indexOf("true") > -1) {
           succeeded = true;
           Helper.USER = getUsername();
           System.out.println("user name set to " + getUsername());
    	   System.out.println(pb.sendMessage("bye."));
           dispose();
       } else {
           JOptionPane.showMessageDialog(LoginDialog.this,
                   "Invalid usernamessss or password",
                   "Login",
                   JOptionPane.ERROR_MESSAGE);
           // reset username and password
           tfUsername.setText("");
           pfPassword.setText("");
           succeeded = false;

       }
   }
    	
  }