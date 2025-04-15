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

}
