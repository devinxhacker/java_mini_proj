package main.ui.pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.ui.components.Header;
import java.util.HashMap;
import java.util.Map;

public class SendReceive implements ActionListener {

    JFrame frame = new JFrame();
    private JTextField textField;
    private JComboBox<String> comboBox;
    private static int nextItemId = 1; // Simple way to generate unique IDs
    private static Map<String, Integer> itemIds = new HashMap<>();

    public SendReceive() {
        frame.setTitle("Send/Receive");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Initialize item IDs
        itemIds.put("Laptop", nextItemId++);
        itemIds.put("TV", nextItemId++);
        itemIds.put("Smart Phones", nextItemId++);
        itemIds.put("Air Conditioners", nextItemId++);
        itemIds.put("Dress", nextItemId++);

        // header starts here
        Header header = new Header(frame);
        frame.getContentPane().add(header, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(350, 68, 574, 508);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Send/Receive Items");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(139, 48, 264, 49);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Select Item");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(228, 120, 101, 23);
        panel_1.add(lblNewLabel_1);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select an item...", "Laptop", "TV", "Smart Phones", "Air Conditioners", "Dress"}));
        comboBox.setBounds(111, 153, 351, 31);
        panel_1.add(comboBox);

        JLabel lblNewLabel_1_1 = new JLabel("Enter Quantity");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(228, 239, 101, 23);
        panel_1.add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setToolTipText("Enter Quantity");
        textField.setBackground(SystemColor.controlHighlight);
        textField.setBounds(111, 272, 351, 31);
        panel_1.add(textField);
        textField.setColumns(10);

        JButton btnReceiveItems = new JButton("Receive Items");
        btnReceiveItems.setForeground(new Color(255, 255, 255));
        btnReceiveItems.addActionListener(this); // Use the main ActionListener
        btnReceiveItems.setSelectedIcon(null);
        btnReceiveItems.setIcon(null);
        btnReceiveItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnReceiveItems.setBackground(new Color(0, 128, 0));
        btnReceiveItems.setActionCommand("RECEIVE"); // Set an action command
        btnReceiveItems.setBounds(300, 358, 162, 31);
        panel_1.add(btnReceiveItems);

        JButton btnSendItems = new JButton("Send Items");
        btnSendItems.setForeground(new Color(255, 255, 255));
        btnSendItems.setSelectedIcon(null);
        btnSendItems.setIcon(null);
        btnSendItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSendItems.setBackground(new Color(255, 0, 0));
        btnSendItems.setActionCommand("SEND"); // Set an action command
        btnSendItems.addActionListener(this); // Use the main ActionListener
        btnSendItems.setBounds(111, 358, 169, 31);
        panel_1.add(btnSendItems);
        // header ends here
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SendReceive obj = new SendReceive();
        obj.show();

        // To see some initial transactions when Transactions page opens
        Transactions.addTransaction("RECEIVE", "Initial Laptop Stock", 1, 10);
        Transactions.addTransaction("SEND", "Demo TV Sent", 2, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RECEIVE")) {
            performTransaction("RECEIVE");
        } else if (e.getActionCommand().equals("SEND")) {
            performTransaction("SEND");
        }
    }

    private void performTransaction(String type) {
        String selectedItem = (String) comboBox.getSelectedItem();
        String quantityText = textField.getText();

        if (selectedItem.equals("Select an item...")) {
            JOptionPane.showMessageDialog(frame, "Please select an item.");
            return;
        }

        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(frame, "Quantity must be a positive number.");
                return;
            }

            Integer itemId = itemIds.get(selectedItem);
            if (itemId != null) {
                Transactions.addTransaction(type, selectedItem, itemId, quantity);
                JOptionPane.showMessageDialog(frame, "Item " + selectedItem + " (" + quantity + ") " + type + "ed successfully!");
                textField.setText(""); // Clear the quantity field
                comboBox.setSelectedIndex(0); // Reset the combo box
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Item ID not found for " + selectedItem);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity. Please enter a number.");
        }
    }
}