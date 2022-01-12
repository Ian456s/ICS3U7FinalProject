package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class changePassword implements ActionListener {
	JFrame f;
	public static String[][] accounts;
	private JTextArea username, password, newPassword;
	private JLabel backgroundLabel;
	private JButton changePassword;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 14);
	public static void main(String args[]) throws IOException {
		new changePassword();
	}
	changePassword() throws IOException {
		f = new JFrame("Password Change");
		accounts = Start.getAccounts();
		backgroundLabel = new JLabel();
		backgroundLabel.setSize(300, 300);
		changePassword = new JButton("Change Password");
		makeChangePassButton(changePassword);
		f.setSize(300, 300);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(backgroundLabel);
	}
	
	private void makeChangePassButton(JButton b) {
		b.setBounds(0, 200, 300, 30);
		b.addActionListener(this);
		b.setFont(buttonFont);
		b.setForeground(new Color(5, 60, 200));
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		backgroundLabel.add(b);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changePassword) {
			System.out.println("Changed Password");
		}
		
	}
}
