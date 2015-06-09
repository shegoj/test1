package com.rso.frontend.charts;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Year;

import com.rso.common.dao.BandwidthUtil;
import com.rso.common.dao.GetDataObject;
 
public class BarChart3D {
 
    public ChartPanel createCategoryChart(String title, String xAxis, String yAxis,DefaultCategoryDataset  categoryDataset)
    {
 

        JFreeChart chart = ChartFactory.createBarChart3D
                     (title, // Title
                       xAxis,              // X-Axis label
                       yAxis,// Y-Axis label
                      categoryDataset,         // Dataset
                      PlotOrientation.VERTICAL,
                      true,                     // Show legend
                      true,
                      false
                     );
        //return chart;
        
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 250));
        //add(chartPanel);
        chartPanel.setBackground(Color.WHITE);
       
        
        return chartPanel;
      }
    
  
}
