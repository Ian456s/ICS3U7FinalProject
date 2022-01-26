package game;

public class PlayerType {
	String name;
	int score;
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
	public String getName() {
		return this.name;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setName(String name) {
		this.name = name;
	}
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
