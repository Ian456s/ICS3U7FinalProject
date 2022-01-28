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
	//declaration of global variables
	private static final long serialVersionUID = 1L;
	private static JFrame f; 
	private ImageIcon background; 
	private JLabel backgroundLabel;
	final static int MAX = 100000;
	private JButton login;
	private JButton register;
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
		in = new BufferedReader(new FileReader(fileName)); //instantiating BufferedReader to read in usernames, passwords and scores
		//reading in usernames and passwords 
		users = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		scores = in.readLine().split(" ");
		numOfUsers = users.length;
		for(int i = 0; i < numOfUsers; i++) {
			accounts[0][i] = users[i];
			accounts[1][i] = passwords[i];
			accounts[2][i] = scores[i];
		} //storing data into 2D String array 
		background = new ImageIcon(this.getClass().getResource("/Backgrounds/background.jpg")); //instantiating background
		backgroundLabel = new JLabel(background); //instantiating background label
		backgroundLabel.setSize(1280, 972); //setting size
		f = new JFrame("Our Lost Friend - Login"); //instantiating frame
		JLabel newPlayer = new JLabel("New to the game? Register now!"); //instantiating JLabel
		newPlayer.setBounds(555, 480, 300, 30); //setting bounds
		backgroundLabel.add(newPlayer); //adding label to background label
		userName = new JTextArea(); //instantiating username text area
		userName.setBounds(500, 355, 300, 20); //setting bounds
		userLabel = new JLabel("Username: "); //instantiating new label for username
		userLabel.setBounds(418, 350, 300, 30); //setting bounds
		userLabel.setFont(buttonFont); //setting font for userlabel
		password = new JPasswordField(); //instantiating new password field 
		password.setBounds(500, 395, 300, 20); //setting bounds
		passLabel = new JLabel("Password: "); //instantiating new label for password
		passLabel.setBounds(420, 390, 300, 30); //setting bounds
		passLabel.setFont(buttonFont); //setting font
		login = new JButton("Login"); //instantiating new JButton
		login.setBounds(590, 440, 100, 30); //setting bounds
		makeButton(login); 
		register = new JButton("Register"); //instantiating new JButton 
		register.setBounds(590,520,100,30); //setting bounds
		makeButton(register);
		backgroundLabel.add(userName); //adding username field
		backgroundLabel.add(password); //adding password field
		backgroundLabel.add(userLabel); //adding username label
		backgroundLabel.add(passLabel); //adding password label
		f.setSize(1280,972); //setting frame size
		f.setLayout(null); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true); //enabling visibility
		f.setLocationRelativeTo(null); //centering
		f.add(backgroundLabel);
		in.close();
	}
	/**
	 * saveUsers function to save data by writing accounts array data into the textfile
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
	
	/**
	 * getUsers method to retrieve number of users registered
	 * @return the number of users in the form of an integer
	 */
	
	public static int getUsers() {
		return numOfUsers;
	}
	
	/**
	 * getUser method to retrieve the currently entered username
	 * @return a string containing the current username
	 */
	
	public static String getUser() {
		return curUser;
	}
	
	/**
	 * getAccounts method to retrieve the 2D array of account details 
	 * @return a 2D String array 
	 */
	
	public static String[][] getAccounts(){
		return accounts;
	}
	
	/**
	 * getPass method to retrieve the currently entered password
	 * @return a string containing the current password
	 */
	
	public static String getPass() {
		return curPass;
	}
	
	/**
	 * addUser method to add a new user to the accounts array
	 * @throws IOException
	 */
	
	public void addUser() throws IOException {
		accounts[0][numOfUsers] = curUser;
		accounts[1][numOfUsers] = curPass;
		accounts[2][numOfUsers] = "0";
		numOfUsers++;
		saveUsers();
	}

	/**
	 * makeButton method to store code changing appearance of a button
	 * @param b
	 */
	
	public void makeButton(@SuppressWarnings("exports") JButton b) {
		b.addActionListener(this);
		b.setFocusable(false);
		b.setFont(buttonFont);
		b.setForeground(Color.black);
		b.setBackground(Color.orange);
		backgroundLabel.add(b);
	}

	/**
	 * accessGranted method to pop up a message dialog stating that access is granted
	 */
	
	public void accessGranted() {
		JOptionPane.showMessageDialog(this, "Access Granted!");
	}

	/**
	 * register method to allow the user to register, meaning a new entry to the accounts array and granting access
	 * @throws FontFormatException
	 */
	
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

	/**
	 * login method to allow a preexisting user to login to the game
	 * @throws FontFormatException
	 */
	
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
	
	/** 
	 * actionPerformed method to keep track of the users clicks and actions
	 */
	
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
		} 

	}
	
	/**
	 * Usercheck method to check if entered credentials are valid or not
	 * @return boolean depending on whether or not the credentials entered are correct
	 */
	private boolean userCheck() {

		for(int j = 0; j < numOfUsers; j++) {
			if(curUser.equals(accounts[0][j]) && curPass.equals(accounts[1][j]))return true;
		}
		return false;
	}
}

