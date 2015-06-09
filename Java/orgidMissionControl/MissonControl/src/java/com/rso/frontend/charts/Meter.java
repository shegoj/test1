package com.rso.frontend.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public  class Meter extends JPanel implements ChangeListener
{

	JSlider slider;
	DefaultValueDataset dataset;
	private ChartPanel chartpanel;

	public static JFreeChart createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i)
	{
		DialPlot dialplot = new DialPlot();
		dialplot.setDataset(valuedataset);
		dialplot.setDialFrame(new StandardDialFrame());
		dialplot.setBackground(new DialBackground());
		DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
		dialtextannotation.setFont(new Font("Dialog", Font.PLAIN, 8));
		dialtextannotation.setRadius(0.69999999999999996D);
		dialplot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialplot.addLayer(dialvalueindicator);
		StandardDialScale standarddialscale = new StandardDialScale(d, d1, -120D, -300D, 10D, 4);
		standarddialscale.setMajorTickIncrement(d2);
		standarddialscale.setMinorTickCount(i);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);
		dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
		DialCap dialcap = new DialCap();
		dialplot.setCap(dialcap);
		return new JFreeChart(s, dialplot);
	}

	public void stateChanged(ChangeEvent changeevent)
	{
		dataset.setValue(new Integer(slider.getValue()));
	}

	public Meter(String generalTitle, String stat, double defaultValue)
	{
		super(new BorderLayout());
		dataset = new DefaultValueDataset(defaultValue);
		JFreeChart jfreechart = createStandardDialChart(generalTitle, stat, dataset, 0D, 100D, 10D, 4);
		
		// set a white background	
		Paint p = new GradientPaint(0, 0, Color.WHITE, 300, 300, Color.WHITE, true);
		jfreechart.setBackgroundPaint(p);
		
		
		
		DialPlot dialplot = (DialPlot)jfreechart.getPlot();
		StandardDialRange standarddialrange = new StandardDialRange(80D, 100D, Color.red);
		standarddialrange.setInnerRadius(0.52000000000000002D);
		standarddialrange.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange);
		StandardDialRange standarddialrange1 = new StandardDialRange(40D, 80D, Color.orange);
		standarddialrange1.setInnerRadius(0.52000000000000002D);
		standarddialrange1.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange1);
		StandardDialRange standarddialrange2 = new StandardDialRange(10D, 40D, Color.green);
		standarddialrange2.setInnerRadius(0.52000000000000002D);
		standarddialrange2.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange2);
		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);
		dialplot.removePointer(0);
		org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
		pointer.setFillPaint(Color.yellow);
		dialplot.addPointer(pointer);
		//dialplot.setBackgroundPaint();
		//dialplot.setBackgroundPaint();
		//dialPlo
		

		chartpanel = new ChartPanel(jfreechart);
		chartpanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				//chartpanel.setPreferredSize(new Dimension(20, 40));
				//chartpanel.revalidate();
			}
		});
		
		chartpanel.setPreferredSize(new Dimension(200,200));
		chartpanel.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		slider = new JSlider(-40, 60);
		slider.setMajorTickSpacing(10);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		slider.setVisible(false);
		add(chartpanel);
		add(slider, "South");
		//this.setBackground(Color.WHITE);
		//chartpanel.setBackground(Color.WHITE);
		//chartpanel.setVisible(false);
	}
	
	public ChartPanel getChartPanel ()
	{
		return chartpanel;
	}
}
