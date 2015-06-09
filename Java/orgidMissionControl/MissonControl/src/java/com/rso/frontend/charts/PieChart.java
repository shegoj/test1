package com.rso.frontend.charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.Locale;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
public class PieChart {  
	
private ChartPanel chartPanel;
private JFreeChart lineChartObject;
private final int width = 700;
private final int height = 350;
	
    public PieChart (String mainTitle, String xTitle, String yTitle,  PieDataset dataset )
    { 
         try 
         {
        	 
        	 lineChartObject=ChartFactory.createPieChart(mainTitle,dataset,true,true,false); 

        	 
        	 chartPanel = new ChartPanel(lineChartObject);
        	 chartPanel.setPreferredSize(new Dimension(width, height));
        	 
        	 // set background to white
     		lineChartObject.setBackgroundPaint(Color.white);
     		lineChartObject.getPlot().setBackgroundPaint(Color.white);
     		lineChartObject.getPlot().setNoDataMessage("No data");
     		
	      
         }
         catch (Exception i)
         {
             System.out.println(i);
         }
     }
    
    public ChartPanel getPanel() 
    {
    	return chartPanel;
    }
    
    public void resetPlot(DefaultCategoryDataset dataset,String mainTitle) 
    {
    	  
    	lineChartObject.getCategoryPlot().setDataset(dataset);
    	lineChartObject.setTitle(mainTitle);
    }
 }