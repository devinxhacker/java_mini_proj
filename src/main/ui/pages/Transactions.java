package main.ui.pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;

import main.ui.components.Header;

public class Transactions implements ActionListener {

	JFrame frame = new JFrame();
	JPanel contentPanel;
	JButton refreshButton;

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
		JPanel transactionList = new JPanel();
		transactionList.setLayout(new BoxLayout(transactionList, BoxLayout.Y_AXIS));
		transactionList.setOpaque(false);
		transactionList.setBorder(new EmptyBorder(10, 40, 10, 40)); // 40px side spacing

		addSampleTransactions(transactionList); // mock data

		JScrollPane scrollPane = new JScrollPane(transactionList);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(new Color(245, 247, 250));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		contentPanel.add(scrollPane, BorderLayout.CENTER);
		frame.add(contentPanel, BorderLayout.CENTER);
	}

	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Transactions obj = new Transactions();
		obj.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refreshButton) {
			JOptionPane.showMessageDialog(frame, "Refreshing data (backend hook needed)");
		}
	}

	private void addSampleTransactions(JPanel panel) {
		List<TransactionData> transactions = new ArrayList<>();
		transactions.add(new TransactionData("RECEIVE", "Smartphone", 2, 5, "4/16/2025, 10:48:30 PM"));
		transactions.add(new TransactionData("SEND", "Camera", 1, 3, "4/16/2025, 10:48:30 PM"));
		transactions.add(new TransactionData("RECEIVE", "Laptop", 7, 10, "4/17/2025, 9:15:00 AM"));

		for (TransactionData t : transactions) {
			JPanel card = new JPanel();
			card.setLayout(new BorderLayout());
			card.setMaximumSize(new Dimension(1000, 120));
			card.setBorder(new CompoundBorder(new EmptyBorder(10, 20, 10, 20), new LineBorder(Color.LIGHT_GRAY)));
			card.setBackground(t.type.equals("RECEIVE") ? new Color(235, 255, 235) : new Color(255, 235, 235));
			card.setAlignmentX(Component.CENTER_ALIGNMENT);

			JLabel typeLabel = new JLabel(t.type);
			typeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
			typeLabel.setForeground(t.type.equals("RECEIVE") ? new Color(0, 128, 0) : new Color(200, 0, 0));

			JLabel timestamp = new JLabel(t.timestamp);
			timestamp.setFont(new Font("SansSerif", Font.PLAIN, 14));
			timestamp.setForeground(Color.DARK_GRAY);

			JPanel top = new JPanel(new BorderLayout());
			top.setOpaque(false);
			top.add(typeLabel, BorderLayout.WEST);
			top.add(timestamp, BorderLayout.EAST);

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
	}

	class TransactionData {
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
