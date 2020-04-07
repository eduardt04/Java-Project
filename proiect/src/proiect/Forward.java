package proiect;

public class Forward extends Player {
	
	private double reboundsPer_game;
	
	public Forward(String Name, int height, int age, double p_rpg, Team p_team) {
		super(Name, height, age, p_team);
		this.reboundsPer_game = p_rpg;
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Points per game: " + reboundsPer_game);
	}

	public void printPlayerPosition() {
		System.out.println(this.name + " is a forward.");
	}
	
}
