/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Sum Calculator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JTextField input1 = new JTextField(10);
        JTextField input2 = new JTextField(10);
        JButton calculateBtn = new JButton("calculate Sum");
        JLabel resultLabel = new JLabel ("Result: ");
        
        calculateBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    double num1 = Double.parseDouble(input1.getText());
                    double num2 = Double.parseDouble(input2.getText());
                    double sum = num1 + num2;
                    resultLabel.setText("Result: " + sum);
                } catch (NumberFormatException ex) {
                    
                 JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
                }
            }
        });
        
        frame.add(new JLabel("Number 1:"));
        frame.add(input1);
        frame.add(new JLabel("Number 2:"));
        frame.add(input2);
        frame.add(calculateBtn);
        frame.add(resultLabel);
        frame.setVisible(true);       
    }   
}
