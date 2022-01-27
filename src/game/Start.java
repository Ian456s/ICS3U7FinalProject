package game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
/**
 * Start method to open new frame for users to register or sign in
 * @author Ian Tang
 * Date: January 4th, 2021
 */
public class Start extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JFrame f; //frame initiation
	private ImageIcon background;
	private JLabel backgroundLabel;
	final static int MAX = 100000;
	private JButton login;
	private JButton register;
	private JButton changePassword;
	private JLabel userLabel, passLabel;
	private JTextArea userName; 
	private JPasswordField password;
	private String fileName = "accounts.txt";
	private BufferedReader in;
	private BufferedWriter out;
	public static String[][] accounts = new String[3][100000];
	String[] users, passwords, scores;
	static int numOfUsers;
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
		scores = in.readLine().split(" ");
		numOfUsers = users.length;
		for(int i = 0; i < numOfUsers; i++) {
			accounts[0][i] = users[i];
			accounts[1][i] = passwords[i];
			accounts[2][i] = scores[i];
		}
		background = new ImageIcon(this.getClass().getResource("/Backgrounds/background.jpg"));
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(1280, 972);
		f = new JFrame("Our Lost Friend - Login");

		JLabel newPlayer = new JLabel("New to the game? Register now!");
		newPlayer.setBounds(555, 480, 300, 30);
		backgroundLabel.add(newPlayer);
		userName = new JTextArea();
		userName.setBounds(500, 355, 300, 20);
		userLabel = new JLabel("Username: ");
		userLabel.setBounds(418, 350, 300, 30);
		userLabel.setFont(buttonFont);
		password = new JPasswordField();
		password.setBounds(500, 395, 300, 20);
		passLabel = new JLabel("Password: ");
		passLabel.setBounds(420, 390, 300, 30);
		passLabel.setFont(buttonFont);
		login = new JButton("Login");
		login.setBounds(590, 440, 100, 30);
		makeButton(login);
		register = new JButton("Register");
		register.setBounds(590,520,100,30);
		makeButton(register);
		backgroundLabel.add(userName);
		backgroundLabel.add(password);
		backgroundLabel.add(userLabel);
		backgroundLabel.add(passLabel);
		f.setSize(1280,972);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(backgroundLabel);
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
		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts[2][i] + " ");
		}
		out.newLine();
		out.close();
	}
	public static int getUsers() {
		return numOfUsers;
	}
	public static String getUser() {
		return curUser;
	}
	public static String[][] getAccounts(){
		return accounts;
	}
	public static String getPass() {
		return curPass;
	}
	public void addUser() throws IOException {
		accounts[0][numOfUsers] = curUser;
		accounts[1][numOfUsers] = curPass;
		accounts[2][numOfUsers] = "0";
		numOfUsers++;
		saveUsers();
	}

	public void makeButton(@SuppressWarnings("exports") JButton b) {
		b.addActionListener(this);
		b.setFocusable(false);
		b.setFont(buttonFont);
		b.setForeground(Color.black);
		b.setBackground(Color.orange);
		backgroundLabel.add(b);
	}

	public void accessGranted() {
		JOptionPane.showMessageDialog(this, "Access Granted!");
	}

	@SuppressWarnings("deprecation")
	public void register() throws FontFormatException {
		curUser = userName.getText().trim();
		curPass = password.getText().trim();
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

	@SuppressWarnings("deprecation")
	public void login() throws FontFormatException {
		curUser = userName.getText(); 
		curPass = password.getText();
		try {
			if(userCheck()) {
				accessGranted();
				login.setEnabled(false);
				new TitleScreen();
				f.dispose();
			} else if(!Arrays.asList(accounts[0]).contains(curUser)){
				JOptionPane.showMessageDialog(this, "Error, your username does not have an account linked to it!");
			} else {
				JOptionPane.showMessageDialog(this, "Error, you have not entered the correct username or password.");
			}
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException exc) {
			exc.printStackTrace();
		}
	}

	public void actionPerformed(@SuppressWarnings("exports") ActionEvent e) {
		if(e.getSource() == login) {
			try {
				login();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				System.out.println("Error, please move the files bgm.wav and OurLostFriend.wav to the \"game\" folder within \"bin\".");
			}
		} else if (e.getSource() == register) {
			try {
				register();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				System.out.println("Error, please move the files bgm.wav and OurLostFriend.wav to the \"game\" folder within \"bin\".");
			}
		} else if(e.getSource() == changePassword) {
				changePassword();
		}

	}
	private void changePassword() {
		try {
			new changePassword();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
		
	private boolean userCheck() {

		for(int j = 0; j < numOfUsers; j++) {
			if(curUser.equals(accounts[0][j]) && curPass.equals(accounts[1][j]))return true;
		}
		return false;
	}
}

