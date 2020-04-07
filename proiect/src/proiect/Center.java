package proiect;

public class Center extends Player {
	
	private double assists_per_game;
	
	public Center(String Name, int height, int age, double p_apg, Team p_team) {
		super(Name, height, age, p_team);
		this.assists_per_game = p_apg;
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Assists per game: " + assists_per_game);
	}
	
	public void printPlayerPosition() {
		System.out.println(this.name + " is a center.");
	}
}
