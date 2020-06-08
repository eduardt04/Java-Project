package project;

public class Team {
	private String name, abbreviation;
	
	public Team(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	public String getData() {
		return this.name + "(" + this.abbreviation + ")"; 
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAbbr() {
		return this.abbreviation;
	}
	
}
