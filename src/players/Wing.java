package players;

import project.Player;
import project.Team;

public class Wing extends Player {
	
	public Wing(String Name, int height, int age, Team p_team) {
		super(Name, height, age, p_team);
	}
	
	public String getPosition() {
		return "Wing";
	}
	
	@Override
	public String getData() {
		return this.getName() + " - Wing for " + this.getTeam().getAbbr();
	}
	
}