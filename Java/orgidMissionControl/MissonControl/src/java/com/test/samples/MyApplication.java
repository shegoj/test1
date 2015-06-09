package com.test.samples;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class MyApplication extends JFrame {

public MyApplication() {
  JButton button = new JButton("Do It!");
  ActionListener doIt = new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
      doProcessing();
    }
  };
  ActionListener cursorDoIt = new CursorController().createListener(this, doIt);
  button.addActionListener(cursorDoIt);
  this.add(button);
  this.setVisible(true);
}


private void doProcessing() {
	for (int i= 0 ; i < 30000; ++i) {
		
		i=i;
	}
}
  private  final class CursorController {

	  public final  Cursor busyCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	  public final  Cursor defaultCursor = Cursor.getDefaultCursor();

	private CursorController() {}

	public  ActionListener createListener(final Component component, final ActionListener mainActionListener) {
	  ActionListener actionListener = new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
	      try {
	        component.setCursor(busyCursor);
	        System.out.println("was here");
	        Thread.sleep(1000);
	        mainActionListener.actionPerformed(ae);
	      }
	      catch  (Exception ex){}
	      finally {
	        component.setCursor(defaultCursor);
	      }
	    }
	  };
	  return actionListener;
	 }
	}
  
  
  public static void main (String args[]) {
	  new MyApplication();
  }
}


