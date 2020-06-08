package project;

public abstract class Player {
	
	private String name;
	private int height, age;
	private Team playerTeam;
	
	public Player(String name, int height, int age, Team t) {
		this.name = name;
		this.height = height;
		this.age = age;
		this.playerTeam = t;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public Team getTeam() {
		return this.playerTeam;
	}
	
	public abstract String getPosition();
	
	public abstract String getData();
	
}
