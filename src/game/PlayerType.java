package game;
/**
 * PlayerType class to represent the player's account within the game, storing their username and their password
 * @author Ian Tang
 *
 */
public class PlayerType {
	String name; //username of player
	int score; //score of player
	public PlayerType(String name, int score) {
		this.name = name;
		this.score = score;
	}
	public PlayerType(String name) {
		this.name = name;
		this.score = 0;
	}
	public PlayerType(int score) {
		this.name = "undefined";
		this.score = score;
	}
	public PlayerType() {
		this.name = "undefined";
		this.score = 0;
	}
	
	/**
	 * getName - getter method to retrieve the username of the current player, in String format
	 * @return the username of the current player, in String format
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * getScore - getter method to retrieve the score of the current player, in integer format
	 * @return the score of the current player, in integer format
	 */
	
	public int getScore() {
		return this.score;
	}
	
	/**
	 * setScore - setter method to change the score of the current player.
	 * @param score - new score that the player's score is being changed to
	 */
	
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * setName - setter method to change the username of the current player
	 * @param name - new name that the player will have
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * sortPlayers method to sort an array of PlayerTypes, from lowest score to highest score
	 * @param p - PlayerType array being sorted
	 */
	
	public static void sortPlayers(PlayerType[] p) {
		for(int i = 0; i < p.length-1; i++) {
			if(p[i].getScore() > p[i+1].getScore()) {
				PlayerType temp = p[i];
				p[i] = p[i+1];
				p[i+1] = temp;
			}
		}
	}
	
}
