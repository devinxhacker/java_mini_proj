package main.ui.pages;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.ui.components.Header;

public class Home implements ActionListener {
	
	JFrame frame = new JFrame();
	
	public Home() {
		
		frame.setTitle("Home");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.getContentPane().setBackground(Color.magenta);
		
		// header starts here
		Header header = new Header(frame);
		frame.add(header, BorderLayout.NORTH);
		// header ends here

		JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(180, 210, 255));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcomeText = new JLabel("Welcome to the Warehouse Management System");
        welcomeText.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subText = new JLabel("Efficiently manage your warehouse inventory with our comprehensive solution");
        subText.setFont(new Font("Serif", Font.PLAIN, 16));
        subText.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(welcomeText);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(subText);

        centerPanel.add(welcomePanel, BorderLayout.NORTH);

        // Cards Panel (from warehousetemp.java)
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(1, 3, 20, 10));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        cardsPanel.add(createCard("Warehouse Overview", "View all compartments and their contents at a glance", "View Warehouse"));
        cardsPanel.add(createCard("Send/Receive Items", "Process incoming and outgoing items", "Send/Receive Items"));
        cardsPanel.add(createCard("Transaction History", "Track all inventory movements and changes", "View Transactions"));

        centerPanel.add(cardsPanel, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);
    
	}
	
	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		Home obj = new Home();
		obj.show();

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	// Create the card panel (from warehousetemp.java)
    private JPanel createCard(String title, String description, String buttonText) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>");
        descLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton(buttonText);
        button.setBackground(new Color(30, 90, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 30));
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, buttonText + " clicked!"));

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(button);

        return card;
    }

}
