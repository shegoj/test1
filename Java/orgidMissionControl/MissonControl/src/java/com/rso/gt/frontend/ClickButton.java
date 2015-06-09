package com.rso.gt.frontend;

import javax.swing.JButton;

public class ClickButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClickButton (String caption)
	{
		super(caption);
	}
	
	private String label;
	

	public void setLabel2(String label)
	{
		this.label = label;
	}
	
	public String getLabel2 () 
	{
		return label;
	}

}
