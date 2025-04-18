package main.ui.pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ui.components.Header;

public class Transactions implements ActionListener {

    JFrame frame = new JFrame();
    JPanel contentPanel;
    JButton refreshButton;
    static List<TransactionData> transactionHistory = new ArrayList<>(); // Shared data source
    JPanel transactionListPanel; // To hold the transaction cards

    public Transactions() {
        frame.setTitle("Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.getContentPane().setBackground(new Color(245, 247, 250));

        // header starts here
        Header header = new Header(frame);
        frame.add(header, BorderLayout.NORTH);
        // header ends here

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(245, 247, 250));

        // Title + Refresh Panel
        JPanel titlePanel = new JPanel(null); // absolute positioning
        titlePanel.setPreferredSize(new Dimension(1280, 80));
        titlePanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Transaction History");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        Dimension titleSize = title.getPreferredSize();
        title.setBounds((1280 - titleSize.width) / 2, 20, titleSize.width, titleSize.height);

        refreshButton = new JButton("🔄 Refresh Data");
        refreshButton.setFocusPainted(false);
        refreshButton.setBackground(new Color(235, 240, 255));
        refreshButton.setForeground(new Color(43, 85, 222));
        refreshButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        refreshButton.setBorder(new LineBorder(new Color(43, 85, 222), 1));
        refreshButton.setPreferredSize(new Dimension(160, 40));
        refreshButton.setBounds(1280 - 160 - 40, 20, 160, 40); // aligned with right edge (40px padding)
        refreshButton.addActionListener(this);

        titlePanel.add(title);
        titlePanel.add(refreshButton);

        contentPanel.add(titlePanel, BorderLayout.NORTH);

        // Transaction List Panel
        transactionListPanel = new JPanel();
        transactionListPanel.setLayout(new BoxLayout(transactionListPanel, BoxLayout.Y_AXIS));
        transactionListPanel.setOpaque(false);
        transactionListPanel.setBorder(new EmptyBorder(10, 40, 10, 40)); // 40px side spacing

        populateTransactions(transactionListPanel); // Populate from shared history

        JScrollPane scrollPane = new JScrollPane(transactionListPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(245, 247, 250));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(contentPanel, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void addTransaction(String type, String itemName, int itemId, int quantity) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a");
        String timestamp = sdf.format(new Date());
        transactionHistory.add(new TransactionData(type, itemName, itemId, quantity, timestamp));
    }

    private void populateTransactions(JPanel panel) {
        panel.removeAll(); // Clear existing cards
        for (TransactionData t : transactionHistory) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setMaximumSize(new Dimension(1000, 90)); // Reduced max height to accommodate less info
            card.setBorder(new CompoundBorder(new EmptyBorder(10, 20, 10, 20), new LineBorder(Color.LIGHT_GRAY)));
            card.setBackground(t.type.equals("RECEIVE") ? new Color(235, 255, 235) : new Color(255, 235, 235));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel typeLabel = new JLabel(t.type);
            typeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            typeLabel.setForeground(t.type.equals("RECEIVE") ? new Color(0, 128, 0) : new Color(200, 0, 0));

            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Changed to FlowLayout to keep type centered if no timestamp
            top.setOpaque(false);
            top.add(typeLabel);
            // top.add(timestamp, BorderLayout.EAST); // Removed timestamp from top panel

            JLabel item = new JLabel("Item: " + t.itemName);
            JLabel id = new JLabel("Item ID: " + t.itemId);
            JLabel qty = new JLabel("Quantity: " + t.quantity);

            Font infoFont = new Font("SansSerif", Font.PLAIN, 16);
            item.setFont(infoFont);
            id.setFont(infoFont);
            qty.setFont(infoFont);

            JPanel details = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
            details.setOpaque(false);
            details.add(item);
            details.add(id);
            details.add(qty);

            card.add(top, BorderLayout.NORTH);
            card.add(details, BorderLayout.CENTER);

            panel.add(card);
            panel.add(Box.createVerticalStrut(15)); // spacing between cards
        }
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        Transactions obj = new Transactions();
        obj.show();

        // Example of adding transactions (for testing purposes)
        Transactions.addTransaction("RECEIVE", "Initial Stock", 101, 50);
        Transactions.addTransaction("SEND", "Sample Dispatch", 202, 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            populateTransactions(transactionListPanel);
            JOptionPane.showMessageDialog(frame, "Transaction history updated.");
        }
    }

    static class TransactionData {
        String type, itemName, timestamp;
        int itemId, quantity;

        TransactionData(String type, String itemName, int itemId, int quantity, String timestamp) {
            this.type = type;
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.timestamp = timestamp;
        }
    }
}