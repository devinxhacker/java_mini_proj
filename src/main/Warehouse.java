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
		frame.getContentPane().setBackground(Color.magenta);
		
		// header starts here
		JPanel header = new JPanel();
		header.setBackground(Color.cyan);
		header.setPreferredSize(new Dimension(1280, 40));
		header.setLayout(new BorderLayout() );
		
		
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
