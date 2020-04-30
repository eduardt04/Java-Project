package proiect;

public class Center extends Player {
	
	private double assists_per_game;
	
	public Center(String Name, int height, int age, double p_apg, Team p_team) {
		super(Name, height, age, p_team);
		this.assists_per_game = p_apg;
	}
	
	public Center(String line) {
		super(line.split(",")[0], Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2]), null);
		this.assists_per_game = Double.parseDouble(line.split(",")[4]);
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Assists per game: " + assists_per_game);
	}
	
	public void printPlayerPosition() {
		System.out.println(this.name + " is a center.");
	}
}
