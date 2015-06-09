package com.test.samples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
 
/**
*
* @author www.javadb.com
*/
public class TrianglePanel extends JPanel implements MouseListener, MouseMotionListener {
 
    private Polygon polygon;
 
    int lastXPos = 0;
    int lastYPos = 0;
    boolean draggable = false;
 
    public TrianglePanel(Point p1, Point p2, Point p3) {
        polygon = new Polygon();
        polygon.addPoint(p1.x, p1.y);
        polygon.addPoint(p2.x, p2.y);
        polygon.addPoint(p3.x, p3.y);
 
        lastXPos = this.getX();
        lastYPos = this.getY();
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
 
    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);

    	 Graphics2D g2d = (Graphics2D) g;
     	AffineTransform saveTransform = g2d.getTransform();
     	 g2d.setStroke(new BasicStroke(4));
     	
     	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
     	        RenderingHints.VALUE_ANTIALIAS_ON);
     	try {
            AffineTransform scaleMatrix = new AffineTransform();
            scaleMatrix.scale(0.5, 0.5);
            g2d.setTransform(scaleMatrix);
	     	g2d.setColor(Color.red);
            g2d.drawPolygon(polygon);

	     	g2d.fillPolygon(polygon);
	     	g2d.translate(50, 0);
	     	g2d.rotate(Math.PI / 6);
     	}
     	catch (Exception ex) { ex.printStackTrace();}
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
        draggable = false;
        if (polygon.contains(e.getPoint())) {
            draggable = true;
            lastXPos = e.getX();
            lastYPos = e.getY();
        }
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
        polygon = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
    }
 
    @Override
    public void mouseEntered(MouseEvent e) {
    }
 
    @Override
    public void mouseExited(MouseEvent e) {
    }
 
    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggable) {
            int xPos = e.getX();
            int[] xPoints = polygon.xpoints;
            for (int i = 0; i < xPoints.length; i++) {
                xPoints[i] = xPoints[i] - (lastXPos - xPos);
            }
            lastXPos = xPos;
 
            int yPos = e.getY();
            int[] yPoints = polygon.ypoints;
            for (int i = 0; i < yPoints.length; i++) {
                yPoints[i] = yPoints[i] - (lastYPos - yPos);
            }
            lastYPos = yPos;
            
            repaint();
        }
    }
 
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}