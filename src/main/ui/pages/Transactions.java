package main.ui.pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import main.ui.components.Header;
import main.api.ApiService;
import main.api.ApiSchema;
import main.api.ApiSchema.TransactionApiResponse;
import main.api.ApiSchema.TransactionData;

public class Transactions implements ActionListener {

	JFrame frame = new JFrame();
	JPanel contentPanel;
	JButton refreshButton;
	JPanel transactionList;
	JScrollPane scrollPane;

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
		transactionList = new JPanel();
		transactionList.setLayout(new BoxLayout(transactionList, BoxLayout.Y_AXIS));
		transactionList.setOpaque(false);
		transactionList.setBorder(new EmptyBorder(10, 40, 10, 40)); // 40px side spacing

		// Add loading indicator
		JLabel loadingLabel = new JLabel("Loading transactions...");
		loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionList.add(loadingLabel);

		scrollPane = new JScrollPane(transactionList);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(new Color(245, 247, 250));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		contentPanel.add(scrollPane, BorderLayout.CENTER);
		frame.add(contentPanel, BorderLayout.CENTER);
		
		// Fetch data when the page is loaded
		fetchTransactions();
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
			fetchTransactions();
		}
	}
	
	private void fetchTransactions() {
		// Show loading indicator
		transactionList.removeAll();
		JLabel loadingLabel = new JLabel("Loading transactions...");
		loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionList.add(loadingLabel);
		transactionList.revalidate();
		transactionList.repaint();
		
		// Use SwingWorker to fetch data in background
		SwingWorker<TransactionApiResponse, Void> worker = new SwingWorker<TransactionApiResponse, Void>() {
			@Override
			protected TransactionApiResponse doInBackground() throws Exception {
				ApiService service = new ApiService();
				TransactionApiResponse response = service.fetchAllTransactions();
				return response;
			}
			
			@Override
			protected void done() {
				try {
					TransactionApiResponse response = get();
					if (response != null && response.success) {
						displayTransactions(response.data);
					} else {
						// Show error message
						transactionList.removeAll();
						JLabel errorLabel = new JLabel("Failed to load transactions. Please try again.");
						errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
						errorLabel.setForeground(Color.RED);
						errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						transactionList.add(errorLabel);
						transactionList.revalidate();
						transactionList.repaint();
					}
				} catch (Exception e) {
					System.err.println("Error fetching transactions: " + e.getMessage());
					// Show error message
					transactionList.removeAll();
					JLabel errorLabel = new JLabel("Error loading transactions. Please try again.");
					errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
					errorLabel.setForeground(Color.RED);
					errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
					transactionList.add(errorLabel);
					transactionList.revalidate();
					transactionList.repaint();
				}
			}
		};
		
		worker.execute();
	}
	
	private void displayTransactions(TransactionData[] transactions) {
		transactionList.removeAll();
		
		if (transactions == null || transactions.length == 0) {
			JLabel noDataLabel = new JLabel("No transactions found.");
			noDataLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
			noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			transactionList.add(noDataLabel);
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a");
			
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

				JPanel top = new JPanel(new BorderLayout());
				top.setOpaque(false);
				top.add(typeLabel, BorderLayout.WEST);

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

				transactionList.add(card);
				transactionList.add(Box.createVerticalStrut(15)); // spacing between cards
			}
		}
		
		transactionList.revalidate();
		transactionList.repaint();
	}
}
