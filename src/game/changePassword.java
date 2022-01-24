package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class changePassword implements ActionListener {
	JFrame f;
	public static String[][] accounts;
	private JTextArea username, password, newPassword;
	private JLabel backgroundLabel;
	private JButton changePassword, done;
	private String fileName = "accounts.txt";
	private BufferedWriter out;
	int numOfUsers = Start.getUsers();
	String curUser, curPass, enteredUser, enteredPass;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 14);
	
	
	changePassword() throws IOException {
		f = new JFrame("Password Change");
		accounts = Start.getAccounts();
		curUser = Start.getUser();
		curPass = Start.getPass();
		backgroundLabel = new JLabel();
		backgroundLabel.setSize(300, 300);
		backgroundLabel.setBackground(Color.black);
		username = new JTextArea("Username");
		username.setBounds(80, 30, 140, 20);
		password = new JTextArea("Password");
		password.setBounds(80, 70, 140, 20);
		newPassword = new JTextArea("New Password");
		newPassword.setBounds(80, 110, 140, 20);
		backgroundLabel.add(username);
		backgroundLabel.add(password);
		backgroundLabel.add(newPassword);
		done = new JButton("Close");
		done.setBounds(200, 200, 100, 20);
		backgroundLabel.add(done);
		makeButton(done);
		changePassword = new JButton("Change Password");
		changePassword.setBounds(0, 150, 300, 30);
		makeButton(changePassword);
		f.setSize(300, 300);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.orange);
		f.add(backgroundLabel);
	}

	private void makeButton(JButton b) {
		
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
			try {
				changePass();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == done) {
			f.dispose();
		}

	}

	public void changePass() throws IOException {
		boolean foundAcc = false;
		enteredUser = username.getText();
		enteredPass = password.getText();
		System.out.println(enteredUser + enteredPass + curUser + curPass);
		for(int i = 0; i < numOfUsers; i++) {
			if(accounts[0][i].equals(enteredUser) && accounts[0][i].equals(curUser) && accounts[1][i].equals(enteredPass) && accounts[1][i].equals(curPass)) {
				accounts[1][i] = newPassword.getText();
				saveUsers();
				foundAcc = true;
			} 
		}
		if(!foundAcc) {
			JOptionPane.showMessageDialog(f, "Error: your entered username and password is incorrect!");
		} else {
			JOptionPane.showMessageDialog(f, "Your password has been changed!");
		}
	}

	public void saveUsers() throws IOException {
		out = new BufferedWriter(new FileWriter(fileName));
		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts[0][i] + " ");
		}
		out.newLine();

		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts[1][i] + " ");
		}
		out.newLine();
		
		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts[2][i] + " ");
		}
		out.newLine();
		out.close();
	}
}
