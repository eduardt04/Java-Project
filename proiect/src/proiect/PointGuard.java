package proiect;

public class PointGuard extends Player {
	
	private double points_per_game;
	
	public PointGuard(String Name, int height, int age, double p_ppg, Team p_team) {
		super(Name, height, age, p_team);
		this.points_per_game = p_ppg;
	}
	
	public PointGuard(String line) {
		super(line.split(",")[0], Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2]), null);
		this.points_per_game = Double.parseDouble(line.split(",")[4]);
	}
	
	public void printPlayerInfo() {
		super.printPlayerInfo();
		System.out.println("Points per game: " + points_per_game);
	}

	public void printPlayerPosition() {
		System.out.println(this.name + " is a point-guard.");
	}
}
