/*
* Title: Programming Assignment 4 - Pizza Servings Calculator
* Author: James Taddei
* Date: 2023-10-01
* Dscription:
*   This program creates and displays a pizza servings calculator GUI.
*/

package programmingassignment4;

import javax.swing.JFrame;

public class ProgrammingAssignment4 {

    public static void main(String[] args) {
        // Creates and displays the pizza servings calculator GUI.
        PizzaServingsCalculatorFrame pizzaServingsCalculatorFrame = new
                PizzaServingsCalculatorFrame();
        pizzaServingsCalculatorFrame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        pizzaServingsCalculatorFrame.setSize(350, 300);
        pizzaServingsCalculatorFrame.setVisible(true);
    }
}
