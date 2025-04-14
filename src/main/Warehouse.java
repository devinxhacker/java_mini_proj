package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Warehouse implements ActionListener {
	
	JFrame frame = new JFrame();
	
	Warehouse() {
		
		frame.setTitle("Warehouse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		
		// header starts here
		JPanel header = new JPanel();
		header.setBackground(Color.cyan);
		header.setPreferredSize(new Dimension(1280, 40));
		header.setLayout(new BorderLayout());
		header.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JButton title = new JButton("Warehouse Management System");
		title.setBorderPainted(false);
		title.setContentAreaFilled(false);
		title.setFocusPainted(false);
		title.setBorder(new EmptyBorder(0, 30, 0, 0));
		title.setFont(title.getFont().deriveFont(20f));
		title.addActionListener(e -> {
			Home newFrame = new Home();
			newFrame.show();
			frame.dispose();
		});
		header.add(title, BorderLayout.WEST);
		
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBackground(Color.cyan);
		navigationPanel.setBorder(new EmptyBorder(2, 0, 0, 30));
		
		JButton warehouseButton = new JButton("Warehouse");
		warehouseButton.addActionListener(e -> {
			Warehouse newFrame = new Warehouse();
			newFrame.show();
			frame.dispose();
		});
		
		JButton sendReceiveButton = new JButton("Send/Receive");
		sendReceiveButton.addActionListener(e -> {
			SendReceive newFrame = new SendReceive();
			newFrame.show();
			frame.dispose();
		});
		
		JButton transactionsButton = new JButton("Transactions");
		transactionsButton.addActionListener(e -> {
			Transactions newFrame = new Transactions();
			newFrame.show();
			frame.dispose();
		});

		navigationPanel.add(warehouseButton);
		navigationPanel.add(Box.createHorizontalStrut(20));
		navigationPanel.add(sendReceiveButton);
		navigationPanel.add(Box.createHorizontalStrut(20));
		navigationPanel.add(transactionsButton);

		header.add(navigationPanel, BorderLayout.EAST);
		
		frame.add(header, BorderLayout.NORTH);
		// header ends here
		
		
		// main panel starts here
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setBackground(Color.yellow);
		
		
		// title + refresh button starts here
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setBackground(Color.yellow);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(1080, 60));
		topPanel.setBackground(Color.yellow);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		
		JLabel panelTitle = new JLabel("Warehouse Compartments");
		panelTitle.setForeground(Color.blue);
		panelTitle.setFont(panelTitle.getFont().deriveFont(25f));
		panelTitle.setBorder(new EmptyBorder(0, 40, 0, 0));
		topPanel.add(panelTitle, BorderLayout.WEST);
		
		
		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBackground(Color.yellow);
		buttonWrapper.setBorder(new EmptyBorder(10, 0, 0, 40));
		
		JButton refreshButton = new JButton("Refresh");
		buttonWrapper.add(refreshButton);
		topPanel.add(buttonWrapper, BorderLayout.EAST);
		
		wrapperPanel.add(topPanel);
		content.add(wrapperPanel, BorderLayout.NORTH);
		// title + refresh button ends here
		
		
		// compartments start here
        JPanel wrapperCompartment = new JPanel();
        wrapperCompartment.setBackground(Color.yellow);
        
		JPanel compartments = new JPanel();
		compartments.setLayout(new GridLayout(0, 3, 20, 20));
		compartments.setPreferredSize(new Dimension(1080, 550));
		compartments.setBackground(Color.yellow);
		compartments.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		
		// individual card
		compartments.add(createCompartment("Electronics", 500, 221));
		compartments.add(createCompartment("Appliances", 500, 90));
		compartments.add(createCompartment("Furniture", 500, 103));
		compartments.add(createCompartment("Clothing", 500, 290));
		compartments.add(createCompartment("Books", 500, 320));

	
		wrapperCompartment.add(compartments);
		content.add(wrapperCompartment, BorderLayout.SOUTH);
		// compartments end here
		
		frame.add(content, BorderLayout.CENTER);
	}
	
	public JPanel createCompartment(String compartmentName, int totalCapacity, int spaceUsed) {
		
		JPanel card = new JPanel();
		card.setBackground(Color.pink);
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		JLabel compartmentNameLabel = new JLabel(compartmentName);
		compartmentNameLabel.setFont(compartmentNameLabel.getFont().deriveFont(20f));
		compartmentNameLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(compartmentNameLabel);
		
		card.add(Box.createVerticalStrut(20));
		
		JLabel totalCapacityLabel = new JLabel("Total Capacity: " + totalCapacity);
		totalCapacityLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(totalCapacityLabel);
		
		card.add(Box.createVerticalStrut(20));
		
		JLabel spaceUsedLabel = new JLabel("Space Used: " + spaceUsed);
		spaceUsedLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(spaceUsedLabel);
		
		card.add(Box.createVerticalStrut(20));
		
		JLabel spaceAvailableLabel = new JLabel("Available Space: " + (totalCapacity - spaceUsed));
		spaceAvailableLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(spaceAvailableLabel);
		
		card.add(Box.createVerticalStrut(20));
		
		JProgressBar spaceBar = new JProgressBar(0, totalCapacity);
		spaceBar.setValue(spaceUsed);
		spaceBar.setStringPainted(true);
		spaceBar.setForeground(Color.green);
		spaceBar.setMaximumSize(new Dimension(200, 20));
		spaceBar.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(spaceBar);
		
		card.add(Box.createVerticalStrut(20));
		
		JButton detailsButton = new JButton("View details");
		detailsButton.setAlignmentX(Container.CENTER_ALIGNMENT);
		card.add(detailsButton);
		
		return card;
	}
	
	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		Warehouse obj = new Warehouse();
		obj.show();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
