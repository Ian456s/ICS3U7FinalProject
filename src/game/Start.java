package game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Start extends JFrame implements ActionListener {
	/**
	 * Author: Ian Tang
	 * Date: January 4th, 2022
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private ImageIcon background;
	private JLabel myLabel;
	final static int MAX = 100000;
	private JButton login;
	private JButton register;
	private JTextArea userName, password;
	private String fileName = "accounts.txt";
	private BufferedReader in;
	private BufferedWriter out;
	private String[][] accounts = new String[2][100000];
	String[] users, passwords;
	int numOfUsers;
	public static String curUser;
	public static String curPass;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 14);

	public static void main(String[] args) throws IOException {
		new Start();
	}

	Start() throws IOException {
		in = new BufferedReader(new FileReader(fileName));

		//reading in usernames and passwords
		users = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		numOfUsers = users.length;
		for(int i = 0; i < numOfUsers; i++) {
			accounts[0][i] = users[i];
			accounts[1][i] = passwords[i];
		}
		background = new ImageIcon(this.getClass().getResource("/background.jpg"));
		myLabel = new JLabel(background);
		myLabel.setSize(1280, 972);
		f = new JFrame("Start");

		JLabel newPlayer = new JLabel("New to the game? Register now!");
		newPlayer.setBounds(555, 480, 300, 30);
		myLabel.add(newPlayer);
		userName = new JTextArea("Username");
		userName.setBounds(500, 350, 300, 30);
		password = new JTextArea("Password");
		password.setBounds(500, 390, 300, 30);
		login = new JButton("Login");
		login.setBounds(590, 440, 100, 30);
		makeButton(login);
		register = new JButton("Register");
		register.setBounds(590,520,100,30);
		makeButton(register);
		myLabel.add(userName);
		myLabel.add(password);
		myLabel.add(login);
		myLabel.add(register);
		f.setSize(1280,972);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(myLabel);
		in.close();
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
		out.close();
	}
	public static String getUser() {
		return curUser;
	}
	public static String getPass() {
		return curPass;
	}
	public void addUser() throws IOException {
		accounts[0][numOfUsers] = curUser;
		accounts[1][numOfUsers] = curPass;
		numOfUsers++;
		saveUsers();
	}

	public void makeButton(JButton b) {
		b.addActionListener(this);
		b.setFocusable(false);
		b.setFont(buttonFont);
		b.setForeground(Color.black);
		b.setBackground(Color.orange);
	}

	public void accessGranted() {
		JOptionPane.showMessageDialog(this, "Access Granted!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login) {
			curUser = userName.getText(); 
			curPass = password.getText();
			try {
				if(userCheck()) {
					accessGranted();
					login.setEnabled(false);
					new TitleScreen();
					f.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Error, you have not entered the correct username or password.");
				}
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException exc) {
					exc.printStackTrace();
				}

			} else if (e.getSource() == register) {
				curUser = userName.getText();
				curPass = password.getText();
				try {
					if(Arrays.asList(accounts[0]).contains(curUser)){
						JOptionPane.showMessageDialog(this, "Error: An account with this username already exists.");
					} else {
						addUser();
						accessGranted();
						register.setEnabled(false);
						new TitleScreen();	
						f.dispose();
					}

				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}

			}

		}
		private void printAccounts() {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < numOfUsers; j++) {
					System.out.print(accounts[i][j] + " ");
				}
				System.out.println();
			}
		}
		private boolean userCheck() {

			for(int j = 0; j < numOfUsers; j++) {
				if(curUser.equals(accounts[0][j]) && curPass.equals(accounts[1][j]))return true;
			}
			return false;
		}
	}

