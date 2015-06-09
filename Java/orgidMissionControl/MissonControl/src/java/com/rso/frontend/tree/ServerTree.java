package com.rso.frontend.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.rso.gt.frontend.Helper;
import com.rso.gt.frontend.frames.MyInternalFrame;
import com.rso.gt.server.def.ServerInt;
 
public class ServerTree  extends JPanel
                      implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    public JSplitPane splitPane;
    
    private int counter = 1;
    
    public JPanel secondWindow ;
    public JDesktopPane desktop;
    
    private  ServerTreeRenderer renderer;
    
    //ArrayList<JInternalFrame> frames = new ArrayList<JInternalFrame>();
    HashMap<String, JInternalFrame> frames = new HashMap<String, JInternalFrame>();
    
    
    ArrayList<ServerInt> servers;
 
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = true;
    private static String lineStyle = "Angled";
     
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
 
    public ServerTree() {
        super(new GridLayout(1,0));
        
        servers = (ArrayList<ServerInt>)Helper.getServers();
        
        // set frames ref 
        Helper.setFramesRef(frames);
        
         //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("ORGID Environments");
        createNodes(top);
        
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        renderer = new ServerTreeRenderer (tree);
        tree.setCellRenderer(renderer);
 
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
 
        if (playWithLineStyle) 
        {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
 
        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);
        
        // register click events:
        
        tree.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me) 
            {
              doMouseClicked(me);
            }
          });
 
        // testing
        
        secondWindow =  new JPanel(new BorderLayout());
        secondWindow.setBorder(new TitledBorder("Environment"));
        
        desktop = new JDesktopPane();
        

        
        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        secondWindow.add(desktop);
      
        
        
        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        //initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);
 
        //Add the scroll panes to a split pane.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeView);
        //splitPane.setBottomComponent(createFrame());
 
        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));
 
        //Add the split pane to this panel.
        add(splitPane);
        
        // refresh
        secondWindow.revalidate();
        secondWindow.repaint();  
        
        //  register desktop with helper class
        Helper.setTreeDesktopPane(desktop);
        Helper.setCanvas(this);
    }
    
    private void doMouseClicked(MouseEvent me) {
    	
    	try{
	    	TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
	        if (tp != null && tp.getPathCount() == 3) 
	        {
	          System.out.println(tp.toString() + " path count is " + tp.getPathCount());
	          
	          if (!frames.containsKey(tp.toString())) 
	          {
	        	  	splitPane.setBottomComponent(createFrame(tp.toString()));
	          }
	          else
	          {
	        	  MyInternalFrame frame_ = (MyInternalFrame) frames.get(tp.toString());
	        	  frame_.setIcon(false);
	        	  frame_.moveToFront();
	        	  frame_.setSelected(true);
	        	  frame_.setMaximizable(false);
	        	  frame_.requestFocus();
	        	  
	        	 // continue from here if (Helper.offlines.indexOf(tp.toS) > -1 || Helper.checked.indexOf(serverName) < 0)
	        	  String serverName = Helper.getServerNameFromTreeLabel(tp.toString());
	        	  if (Helper.offlines.indexOf(serverName) > -1 || Helper.checked.indexOf(serverName) < 0 )
	        		  frame_.setVisible(false);
	        	  else 
	        	  {
	        		  	System.out.println("clicking and calling " + serverName);
	        		  	frame_.refreshControls();
	        		  	frame_.revalidate();	        		  	
	        		  	frame_.repaint();
	        		  	frame_.setVisible(true);
	        		  	frame_.setMaximizable(false);
	        	  	}
	          }
	        }
	        else
	        	 System.out.println(tp.toString());
    	}
    	
    	catch  (Exception ex){}
      }
    
    //Create a new internal frame.
    protected JPanel  createFrame(String x) {
        MyInternalFrame frame = new MyInternalFrame(frames,x);
        //frame.setVisible(true); //necessary as of 1.3
   
        desktop.add(frame);
        secondWindow.revalidate();
        secondWindow.repaint();
        //frames.put(x, frame);
   
        try 
        {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {e.printStackTrace();}
        
        return secondWindow;
    }
 
    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
 
        if (node == null) return;
 
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) 
        {
            ServerInfo server = (ServerInfo)nodeInfo;
            if (DEBUG) 
            {
                System.out.print(server.serverDesc + ":  \n    ");
            }
        } 
        else 
        {
            displayURL(helpURL);
        }
        if (DEBUG) 
        {
            System.out.println(nodeInfo.toString());
        }
    }
 
    private class ServerInfo {
        public String serverDesc;
        public String serverIP;
 
        public ServerInfo(String desc, String ipaddress) 
        {
        	serverDesc = desc;
        	serverIP = ipaddress;
            }

    	public String getIP() {
    		return serverIP;
    	}
    
    	public String getDesc() {
    		return serverDesc;
    	}
 
        public String toString() {
            return getIP();
        }
    }

  private void displayURL(URL url) {
        try 
        {
            if (url != null) 
            {
                htmlPane.setPage(url);
            } 
	        else 
	        { //null url
		        htmlPane.setText("File Not Found");
		        if (DEBUG) 
		        {
		            System.out.println("Attempted to display a null URL.");
		        }
	        }
        }
        catch (IOException e) 
        {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }
 
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode server = null;
        
        Map <String, DefaultMutableTreeNode> mGroupList = new HashMap<String, DefaultMutableTreeNode>();
        
        
        for (int i =0 ; i < servers.size(); ++ i) 
        {
        
        	String serverGroupName = servers.get(i).getGroupName();
        	
        	category = mGroupList.get(serverGroupName);
        	
        	if (category == null) {
        		category = new DefaultMutableTreeNode(serverGroupName);
        		mGroupList.put(serverGroupName, category);    		
 
        	}
        	
	        top.add(category);
        	server = new DefaultMutableTreeNode(new ServerInfo (servers.get(i).getIPAddress(),servers.get(i).getServerName()));
        	
        	// to do online servers check  i.e populate Helper.offlines variable call getUptime method
        	servers.get(i).getUptime();
        	
        	// add info to category tree
        	category.add(server);
        }
    }
         
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    
    
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
 
        //Create and set up the window.
        JFrame frame = new JFrame("Envrionment List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
      //  frame.add(new CopyOfTree());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

