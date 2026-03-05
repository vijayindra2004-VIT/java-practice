package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShoppingCartGUI extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel grandTotalLabel;
    private double runningTotal = 0.0;

    public ShoppingCartGUI() {

        setTitle("Shopping Cart View");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        String[] columns = {"Product Name","Unit Price","Quantity","Subtotal"};
        tableModel = new DefaultTableModel(columns,0);
        cartTable = new JTable(tableModel);

        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2,4,5,5));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField qtyField = new JTextField();
        JButton addButton = new JButton("Add To Cart");

        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(new JLabel(""));

        inputPanel.add(nameField);
        inputPanel.add(priceField);
        inputPanel.add(qtyField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        grandTotalLabel = new JLabel("Grand Total: 0.0");
        add(grandTotalLabel, BorderLayout.SOUTH);

        addButton.addActionListener((ActionEvent e) -> {
            try {

                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(qtyField.getText());

                double subtotal = price * qty;

                tableModel.addRow(new Object[]{name, price, qty, subtotal});

                updateGrandTotal(subtotal);

                nameField.setText("");
                priceField.setText("");
                qtyField.setText("");

            } catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter valid price and quantity");

            }
        });
    }

    private void updateGrandTotal(double subtotal) {
        runningTotal += subtotal;
        grandTotalLabel.setText("Grand Total: " + runningTotal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShoppingCartGUI().setVisible(true);
        });
    }
}