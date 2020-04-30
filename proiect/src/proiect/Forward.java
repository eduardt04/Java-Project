package proiect;

public class Forward extends Player {
	
	private double rebounds_per_game;
	
	public Forward(String Name, int height, int age, double p_rpg, Team p_team) {
		super(Name, height, age, p_team);
		this.rebounds_per_game = p_rpg;
	}
	
	public Forward(String line) {
		super(line.split(",")[0], Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2]), null);
		this.rebounds_per_game = Double.parseDouble(line.split(",")[4]);
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Points per game: " + rebounds_per_game);
	}

	public void printPlayerPosition() {
		System.out.println(this.name + " is a forward.");
	}
	
}
