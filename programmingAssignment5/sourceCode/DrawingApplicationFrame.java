/*
* Title: Drawing Application Frame Class
* Author: James Taddei
* Date: 2023-10-16
* Dscription:
*   This class is class is a drawing application implemented as a GUI through
*   Swing.
*/

package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    // Create the panels.
    private final JPanel line1 = new JPanel();
    private final JPanel line2 = new JPanel();
    private final JPanel topPanel = new JPanel();

    // Create the widgets for the firstLine panel.
    private final JLabel shapeTitleLabel;
    private final JComboBox<String> shapesComboBox;
    private final JButton colorPicker1;
    private final JButton colorPicker2;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.BLACK;
    private final JButton undoButton;
    private final JButton clearButton;

    // Create the widgets for the secondLine panel.
    private final JLabel optionsLabel;
    private final JCheckBox filledCheckBox;
    private final JCheckBox gradientCheckBox;
    private final JCheckBox dashCheckBox;
    private final JLabel lineWidthLabel;
    private final JSpinner lineWidth;
    private final JLabel dashLengthLabel;
    private final JSpinner dashLength;

    // Variables for drawPanel.
    private final DrawPanel drawPanel = new DrawPanel();
    private final ArrayList<MyShapes> shapes = new ArrayList<>();

    // Create status label.
    private final JLabel statusLabel;
  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
        super("Java 2D Drawings"); // creates the GUI frame
                
        // firstLine widgets
        shapeTitleLabel = new JLabel("Shape:");
        final String[] choosableShapes = {"Line", "Oval", "Rectangle"};
        shapesComboBox = new JComboBox(choosableShapes);
        colorPicker1 = new JButton("1st Color...");
        colorPicker2 = new JButton("2nd Color...");
        undoButton = new JButton("Undo");
        clearButton = new JButton("Clear");
        line1.setBackground(Color.CYAN);
        line1.add(shapeTitleLabel);
        line1.add(shapesComboBox);
        line1.add(colorPicker1);
        line1.add(colorPicker2);
        line1.add(undoButton);
        line1.add(clearButton);
        
        // secondLine widgets
        optionsLabel = new JLabel("Options:");
        filledCheckBox = new JCheckBox("Filled");
        gradientCheckBox = new JCheckBox("Use Gradient");
        dashCheckBox = new JCheckBox("Dashed");
        lineWidthLabel = new JLabel("Line Width:");
        lineWidth = new JSpinner();
        dashLengthLabel = new JLabel("Dash Length:");
        dashLength = new JSpinner();
        line2.setBackground(Color.CYAN);
        line2.add(optionsLabel);
        line2.add(filledCheckBox);
        line2.add(gradientCheckBox);
        line2.add(dashCheckBox);
        line2.add(lineWidthLabel);
        line2.add(lineWidth);
        line2.add(dashLengthLabel);
        line2.add(dashLength);
        
        // Add 2 lines to topPanel.
        topPanel.setLayout(new GridLayout(2,1));
        topPanel.add(line1);
        topPanel.add(line2);

        // Add topPanel to North, drawPanel to Center, and statusLabel to South
        add(topPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        statusLabel = new JLabel("(0,0)");
        setBackground(Color.GRAY);
        add(statusLabel, BorderLayout.SOUTH);
        
        // Add listeners and event handlers.
        Color1ButtonHandler color1Handler = new Color1ButtonHandler();
        colorPicker1.addActionListener(color1Handler);
        Color2ButtonHandler color2Handler = new Color2ButtonHandler();
        colorPicker2.addActionListener(color2Handler);
        UndoButtonHandler undoHandler = new UndoButtonHandler();
        undoButton.addActionListener(undoHandler);
        ClearButtonHandler clearHandler = new ClearButtonHandler();
        clearButton.addActionListener(clearHandler);
    }
    
    // Class to handle the 'colorPicker1' button.
    private class Color1ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            color1 = JColorChooser.showDialog(DrawingApplicationFrame.this,
                    "Choose a color", color1);
            colorPicker1.setBackground(color1);
        }
    }
    
    // Class to handle the 'colorPicker2' button.
    private class Color2ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            color2 = JColorChooser.showDialog(DrawingApplicationFrame.this,
                    "Choose a color", color2);
            colorPicker2.setBackground(color2);
        }
    }
    
    // Class to handle the 'undoButton' button.
    private class UndoButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            // If the length of shapes is non-zero, removes the last shape
            // and repaints the drawPanel.
            if (shapes.size() >= 1)
            {
                shapes.remove(shapes.size()-1);
                drawPanel.repaint();
            }
        }
    }
    
    // Class to handle the 'clearButton' button.
    private class ClearButtonHandler implements ActionListener
    {
        @Override
        // Clears shapes and repaints the drawPanel.
        public void actionPerformed(ActionEvent event)
        {
            shapes.clear();
            drawPanel.repaint();
        }
    }


    // Creates a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
            super();
            setBackground(Color.WHITE);
            addMouseListener(new MouseHandler());
            addMouseMotionListener(new MouseHandler());
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            for (MyShapes shape : shapes)
            {
                shape.draw(g2d);
            }
        }

        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            @Override
            public void mousePressed(MouseEvent event)
            {
                // Variable declarations
                Paint paint = color1; // default (if no gradient) is color1
                BasicStroke stroke;
                int intLineWidth = (Integer) lineWidth.getValue();
                int intDashLength = (Integer) dashLength.getValue();
                float[] dashParamter = {intDashLength};
                String selectedShape = (String)
                        shapesComboBox.getSelectedItem();
                Point point = new Point(event.getX(), event.getY());
                
                // Sets the paint color.
                if (gradientCheckBox.isSelected())
                {
                    paint = new GradientPaint(0, 0, color1, 50, 50, color2,
                            true);
                }
                
                // Sets the stroke.
                if (dashCheckBox.isSelected())
                {
                    stroke = new BasicStroke(intLineWidth,
                            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10,
                            dashParamter, 0);
                } else
                {
                    stroke = new BasicStroke(intLineWidth,
                            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
                
                // Creates and adds the selected shape.
                switch (selectedShape)
                {
                    case "Line":
                        shapes.add(new MyLine(point, point, paint, stroke));
                        break;
                    case "Oval":
                        shapes.add(new MyOval(point, point, paint, stroke,
                                filledCheckBox.isSelected()));
                        break;
                    case "Rectangle":
                        shapes.add(new MyRectangle(point, point, paint, stroke,
                                filledCheckBox.isSelected()));
                        break;
                }
            }

            @Override
            // Updates the shape to the new cursor location.
            public void mouseDragged(MouseEvent event)
            {
                Point endPoint = new Point(event.getX(), event.getY());
                MyShapes shape = shapes.get(shapes.size() - 1);
                shape.setEndPoint(endPoint);
                drawPanel.repaint();
            }

            @Override
            // Updates the status label to the new cursor location.
            public void mouseMoved(MouseEvent event)
            {
                statusLabel.setText(String.format(
                        "(%d,%d)", event.getX(), event.getY()));
            }
        }
    }
}
