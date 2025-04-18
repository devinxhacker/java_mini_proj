package main.ui.pages;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.ui.components.Header;

public class SendReceive implements ActionListener {
	
	JFrame frame = new JFrame();
	private JTextField textField;
	
	public SendReceive() {
		
		frame.setTitle("Send/Receive");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select an item...", "Laptop", "TV", "Smart Phones", "Air Conditioners", "Dress"}));
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
		btnReceiveItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReceiveItems.setSelectedIcon(null);
		btnReceiveItems.setIcon(null);
		btnReceiveItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReceiveItems.setBackground(new Color(0, 128, 0));
		btnReceiveItems.setBounds(300, 358, 162, 31);
		panel_1.add(btnReceiveItems);
		
		JButton btnSendItems = new JButton("Send Items");
		btnSendItems.setForeground(new Color(255, 255, 255));
		btnSendItems.setSelectedIcon(null);
		btnSendItems.setIcon(null);
		btnSendItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSendItems.setBackground(new Color(255, 0, 0));
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

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
