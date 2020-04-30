package proiect;

public class Wing extends Player {
	
	private double three_pointers_per_game;
	
	public Wing(String Name, int height, int age, double p_3ppg, Team p_team) {
		super(Name, height, age, p_team);
		this.three_pointers_per_game = p_3ppg;
	}
	
	public Wing(String line) {
		super(line.split(",")[0], Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2]), null);
		this.three_pointers_per_game = Double.parseDouble(line.split(",")[4]);
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Assists per game: " + three_pointers_per_game);
	}
	
	public void printPlayerPosition() {
		System.out.println(this.name + " is a wing.");
	}
}
