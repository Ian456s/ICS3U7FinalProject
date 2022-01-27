package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
/**
 * changePassword class to create a frame where the user can change their current password
 * @author Ian Tang
 *
 */
public class changePassword implements ActionListener {
	JFrame f; //frame
	public static String[][] accounts; //accounts 2D array
	private JTextArea username; //text area for username
	private JPasswordField password, newPassword; //password field 
	private JLabel backgroundLabel; //background label to hold the background and necessary buttons, etc.
	private JButton changePassword, done; //buttons to change password and close window
	private String fileName = "accounts.txt"; //file name
	private BufferedWriter out; //bufferedwriter to save data in text files
	int numOfUsers = Start.getUsers(); //number of users
	String curUser, curPass, enteredUser, enteredPass; //Strings for entered fields
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 14); //button font
	
	changePassword() throws IOException {
		f = new JFrame("Password Change"); //creating new frame
		accounts = Start.getAccounts(); //retrieving accounts
		curUser = Start.getUser(); //retrieving current username
		curPass = Start.getPass(); //retrieving current password 
		backgroundLabel = new JLabel(); //creating new JLabel for background 
		backgroundLabel.setSize(300, 300); //setting the size
		backgroundLabel.setBackground(Color.black); //setting default background color
		username = new JTextArea("Username"); //Instantiating new text area for username
		username.setBounds(80, 30, 140, 20); //setting bounds for username
		password = new JPasswordField("Password"); //Instantiating new text area for password
		password.setBounds(80, 70, 140, 20); //setting bounds
		newPassword = new JPasswordField("New Password"); //Instantiating new text area for new password
		newPassword.setBounds(80, 110, 140, 20); //settings bounds
		backgroundLabel.add(username); //adding username to background label
		backgroundLabel.add(password); //adding password to background label
		backgroundLabel.add(newPassword); //adding new password to background label
		done = new JButton("Close"); //Instantiating new JButton for closing the window
		done.setBounds(200, 200, 100, 20); //settings bounds
		backgroundLabel.add(done); //adding button to background label 
		makeButton(done); 
		changePassword = new JButton("Change Password"); //Instantiating new JButton to change password
		changePassword.setBounds(0, 150, 300, 30); //setting bounds
		makeButton(changePassword); 
		f.setSize(300, 300); //setting size of frame
		f.setLayout(null); 
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true); //enabling vision of frame
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.orange);
		f.add(backgroundLabel);
	}
	/**
	 * makeButton method to store some basic code to change the appearance of a button
	 * @param b
	 */
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
	/**
	 * actionPerformed method to keep track of different user inputs
	 */
	public void actionPerformed(@SuppressWarnings("exports") ActionEvent e) {
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
	
	/**
	 * changePass method to change the current user's password to their new entered password
	 * @throws IOException
	 */
	
	public void changePass() throws IOException {
		boolean foundAcc = false; //foundAcc boolean to keep track of whether the user's account has been found 
		enteredUser = username.getText(); //retrieving currently entered username
		enteredPass = password.getText(); //retrieving currently entered password
		for(int i = 0; i < numOfUsers; i++) {
			if(accounts[0][i].equals(enteredUser) && accounts[0][i].equals(curUser) && accounts[1][i].equals(enteredPass) && accounts[1][i].equals(curPass)) { //if current entered credentials are valid
				accounts[1][i] = newPassword.getText(); //change password
				saveUsers(); //save the data
				foundAcc = true; //found the account
			} 
		}
		if(!foundAcc) {
			JOptionPane.showMessageDialog(f, "Error: your entered username and password is incorrect!");//if the credentials are invalid then display error
		} else {
			JOptionPane.showMessageDialog(f, "Your password has been changed!");//if the credentials are valid then display confirmation
		}
	}

	/**
	 * saveUsers method to update the current text file with new data
	 * @throws IOException
	 */
	
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
