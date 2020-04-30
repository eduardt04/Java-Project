package proiect;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
	
	public Player(String line) {
		String[] values = line.split(",");
		if(values.length == 3) {
			this.name = values[0];
			this.height = Integer.parseInt(values[1]);
			this.age = Integer.parseInt(values[2]); 
		}
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
	
	public Team getTeam() {
		return this.team;
	}
	
	public void printPlayerInfoToCSV(String filepath) throws Exception {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
			writer.append(this.name + ", " + this.height + ", " + this.age + "\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void printPlayerPosition();
}
