package players;

import project.Player;
import project.Team;

public class PointGuard extends Player {
	
	public PointGuard(String Name, int height, int age, Team p_team) {
		super(Name, height, age, p_team);
	}

	public String getPosition() {
		return "Point Guard";
	}
	
	@Override
	public String getData() {
		return this.getName() + " - Point Guard for " + this.getTeam().getAbbr();
	}
	
}