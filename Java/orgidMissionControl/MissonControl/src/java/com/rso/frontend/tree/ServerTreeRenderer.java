package com.rso.frontend.tree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import com.rso.gt.frontend.Helper;

public class ServerTreeRenderer  extends DefaultTreeCellRenderer{

private JTree tree;
private Icon offlineIcon = UIManager.getIcon("OptionPane.errorIcon");
//private Icon saveIcon =  new ImageIcon("C:\\development\\java\\workspace\\orgidMissionControl\\MissonControl\\src\\greenIcon.jpg");// UIManager.getIcon("FileView.computerIcon");
private Icon onlineIcon =  UIManager.getIcon ("FileView.computerIcon");
private Icon checked =   UIManager.getIcon ("FileView.computerIcon"); // list of servers that have been checked.


public ServerTreeRenderer(JTree tree) {
	
	super();
	// set local variable tree to ref external tree
	this.tree = tree;
	
	
}


@Override
public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean isLeaf, int row, boolean focused) {
	
    Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused);
    
 /*   TreePath tp = tree.getPathForLocation(tree.getX(), tree.getY());
    if (tp != null && tp.getPathCount() < 3){
    	setIcon(onlineIcon);
    	return c;
    }*/
    if  (Helper.checked.indexOf(this.getText()) < 1)
        setIcon(checked);
    else if (Helper.offlines.indexOf(this.getText()) > -1)
        setIcon(offlineIcon);
    else
       setIcon(onlineIcon);
    
    tree.revalidate();
    tree.repaint();
    
    return c;
	
}

}