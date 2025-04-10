package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home implements ActionListener {
	
	JFrame f = new JFrame();
	
	Home() {
		
		f.setTitle("My second"); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.getContentPane().setBackground(Color.cyan);
	}
	
	public void show() {
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		Home obj = new Home();
		JButton button1 = new JButton("click me");
		JButton button2 = new JButton("don't click me");
		
		obj.f.add(button1);
		button1.addActionListener(obj);
		
		obj.f.add(button1);
		
		obj.show();

	}
	
	public void actionPerformed(ActionEvent e) {
		
		JOptionPane.showMessageDialog(null, "you pressed buton");
	}

}
