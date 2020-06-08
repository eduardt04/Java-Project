package players;

import project.Player;
import project.Team;

public class Forward extends Player {
	
	public Forward(String Name, int height, int age, Team p_team) {
		super(Name, height, age, p_team);
	}
	
	public String getPosition() {
		return "Forward";
	}
	
	@Override
	public String getData() {
		return this.getName() + " - Forward for " + this.getTeam().getAbbr();
	}
	
}
