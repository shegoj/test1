package com.test.samples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Meter extends JFrame {
    
    public Meter() {
        init();
    }
 
    private void init() {
        this.setTitle("Draggable triangle example by www.javadb.com");
        TrianglePanel triangle = new TrianglePanel(new Point(300, 10), new Point(295, 200), new Point(310, 200));
        triangle.setPreferredSize(new Dimension(500, 500));
        triangle.setBackground(Color.BLUE);
        this.add(triangle);
        pack();
    }
    
    public static void main(String[] args) {
    	Meter frame = new Meter();
        frame.setVisible(true);
    }
    
    
}
    