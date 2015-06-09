package com.rso.frontend.charts;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class LineChart {  
	
private ChartPanel chartPanel;
private JFreeChart lineChartObject;
private final int width = 700;
private final int height = 350;
	
    public LineChart (String mainTitle, String xTitle, String yTitle,  DefaultCategoryDataset dataset )
    { 
         try 
         {
        	 
        	 lineChartObject=ChartFactory.createLineChart(mainTitle,xTitle,yTitle,dataset,PlotOrientation.VERTICAL,true,true,false);                
        	 chartPanel = new ChartPanel(lineChartObject);
        	 chartPanel.setPreferredSize(new Dimension(width, height));
	      
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