package com.rso.frontend.charts;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
	
	
	ChartPanel chartPanel;
	JFreeChart chart;
	
	public BarChart (String mainTitle, String xTtile, String yTitle,  DefaultCategoryDataset dataset )
	{

		
	  chart = ChartFactory.createBarChart(mainTitle,xTtile, yTitle, dataset, PlotOrientation.VERTICAL, true, true, false);
	  chartPanel = new ChartPanel(chart);
	  chartPanel.setPreferredSize(new Dimension(700, 350));
	  //add(chartPanel);
	  chartPanel.setBackground(Color.WHITE);
	  chart.getPlot().setNoDataMessage("No data available");
		        
	}
	
public ChartPanel getChartPanel() {
	
	return chartPanel;
}

public void resetPlot(DefaultCategoryDataset dataset) {
	
	  chart.getCategoryPlot().setDataset(dataset);
	  //TextTitle my_Chart_title=new TextTitle("Title With new Font", new Font ("Verdana", Font.PLAIN , 17));
	  chart.setTitle("");
	}
}

