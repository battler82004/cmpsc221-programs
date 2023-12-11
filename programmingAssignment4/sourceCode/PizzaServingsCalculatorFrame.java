/*
* Title: Pizza Servings Calculator Class
* Author: James Taddei
* Date: 2023-10-01
* Dscription:
*   This class is a pizza servings calculator implemented as a GUI through
*   Swing.
*/

package programmingassignment4;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;

// Class for the pizza servings calculator GUI.
public class PizzaServingsCalculatorFrame extends JFrame {
    // Class variable declarations
    private final JLabel titleLabel;
    private final JLabel resultantLabel;
    private final JLabel promptLabel;
    private final JTextField pizzaSizeField;
    private final JPanel line2 = new JPanel();
    private final JButton calculateServings;
    
    public PizzaServingsCalculatorFrame() {
        // Constructor
        
        // Creates the GUI frame.
        super("Pizza Servings Calculator");
        setLayout(new GridLayout(4, 1)); // sets layout to 4 row grid
        
        // Creates and adds the 'titleLabel' label. (Pos 1)
        titleLabel = new JLabel("Pizza Servings Calculator", JLabel.CENTER);
        titleLabel.setToolTipText("Program title");
        titleLabel.setFont(new Font("SERIF", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        add(titleLabel);
        
        // Creates and adds 'promptLabel' and 'pizzaSizeField' via the 'line2'
        // panel. (Pos 2)
        promptLabel = new JLabel("Enter the size of the pizza in inches: ");
        pizzaSizeField = new JTextField(4);
        line2.add(promptLabel);
        line2.add(pizzaSizeField);
        add(line2);
        
        // Creates and adds the 'calculateServings' button. (Pos 3)
        calculateServings = new JButton("Calculate Servings");
        add(calculateServings);
        
        // Creates and adds the 'resultantLabel' label. (Pos 4)
        resultantLabel = new JLabel("", JLabel.CENTER);
        resultantLabel.setToolTipText("Output of the servings calculator");
        add(resultantLabel);
        
        // Creates a handler for the 'calculateServings' button.
        ButtonHandler handler = new ButtonHandler();
        calculateServings.addActionListener(handler);
    }
    
    private void setResultantLabel(String size, double servings) {
        // Sets the text of 'resultantLabel' based on the inputted size and
        // number of servings.
        
        String result = String.format("A %s inch pizza will serve %.2f people.",
                size, servings); // Creates the string to be displayed
        resultantLabel.setText(result);
    }
    
    // Class to handle clicking the 'calculateServings' button.
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Updates the 'resultantLabel' based on the inputted data once the
            // 'calculateServings' button is pressed.
            
            String sizeAsString = pizzaSizeField.getText();
            double size = Double.parseDouble(sizeAsString);
            double servings = Math.pow(size / 8, 2);
            setResultantLabel(sizeAsString, servings);
        }
    }
}