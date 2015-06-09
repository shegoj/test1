package com.rso.gt.frontend.frames.adapters;

import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class WindowInternalFrameAdapter  extends InternalFrameAdapter{
	
	String title;
	Map<String, JInternalFrame> frames;
	// what to do when closing
	public void internalFrameClosing(InternalFrameEvent e) {
		
			frames.remove(this.title);
	 	}
	//
	public WindowInternalFrameAdapter(Map<String, JInternalFrame> frames, String title) {
		
		super();
		this.title = title;
		this.frames = frames;
	}
}

