package proiect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class NBA {
	
	private Vector<Team> teams;
	
	public NBA() {
		this.teams = new Vector<Team>();
	}
	
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	
	public int getTeamsNumber() {
		return teams.size();
	}
	
	public ArrayList<Player> getAllPlayers() {
		
		ArrayList<Player> all_players = new ArrayList<Player>();
		Iterator<Team> teamsItr = this.teams.iterator();
		
		while(teamsItr.hasNext()) {
			
			Team t = teamsItr.next();
			Iterator<Player> playersItr = t.getPlayers().iterator();
			
			while(playersItr.hasNext()) {
				Player p = playersItr.next();
				all_players.add(p);
			}
		}
		
		return all_players;
	}
	
	public void printTeams() {
		
		if(teams.size() == 0)
			System.out.println("Currently there are no teams created.");
		
		Iterator<Team> teamsItr = this.teams.iterator();
		
		while(teamsItr.hasNext()) {
			Team t = teamsItr.next();
			System.out.println(t.getTeamName() + "-" + t.getTeamAbbr());
		}
	}
	
	public void printTeamsWithPlayers() {
		
		if(teams.size() == 0)
			System.out.println("Currently there are no teams created.");
		
		Iterator<Team> teamsItr = this.teams.iterator();
		
		while(teamsItr.hasNext()) {
			Team t = teamsItr.next();
			System.out.println(t.getTeamName() + "-" + t.getTeamAbbr());
			t.printTeamPlayers();
			System.out.println("\n");
			
		}
	}
	
	public Team getTeamByAbbr(String abbr) {
		
		Iterator<Team> teamsItr = this.teams.iterator();
		Team result = null;
		
		while(teamsItr.hasNext()) {
			Team t = teamsItr.next();
			if(abbr.equals(t.getTeamAbbr()))
				result = t;
		}
		
		return result;
	}
	
}
