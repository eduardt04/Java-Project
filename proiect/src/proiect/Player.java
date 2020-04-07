package proiect;

public abstract class Player {
	
	protected String name;
	private int height, age;
	private Team team;

	public Player(String p_name, int p_height, int p_age, Team p_team) {
		this.name = p_name;
		this.height = p_height;
		this.age = p_age;
		this.team = p_team;
	}
	
	public void printPlayerInfo() {
		System.out.println("Name: " + name);
		System.out.println("Height: " + height + "cm");
		System.out.println("Age: " + age + "years");
		System.out.println("Currently playing for: " + team.getTeamName());
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void printPlayerPosition();
}
