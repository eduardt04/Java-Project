package players;

import project.Player;
import project.Team;

public class Center extends Player {
	
	public Center(String Name, int height, int age, Team p_team) {
		super(Name, height, age, p_team);
	}
	
	public String getPosition() {
		return "Center";
	}

	@Override
	public String getData() {
		return this.getName() + " - Center for " + this.getTeam().getAbbr();
	}
	
}