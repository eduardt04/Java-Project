package proiect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class NBA {
	
	private ArrayList<Team> teams;
	private ArrayList<Player> players;
	
	public NBA() {
		try {
			teams = TeamsCSVReader.getInstance().readData("src/proiect/teams.csv", Team.class); 	
			players = PlayersCSVReader.getInstance().readData("src/proiect/players.csv", Player.class);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	
	public int getTeamsNumber() {
		return teams.size();
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public void printTeams() {
		
		if(teams.size() == 0)
			System.out.println("Currently there are no teams created.");
		
		Iterator<Team> teamsItr = this.teams.iterator();
		
		while(teamsItr.hasNext()) {
			Team t = teamsItr.next();
			System.out.println(t.getTeamName() + " - " + t.getTeamAbbr());
		}
	}
	
	public void printTeamsToCSV(String filepath) throws Exception {
		
		if(teams.size() == 0)
			System.out.println("Currently there are no teams created.");
		
		Iterator<Team> teamsItr = this.teams.iterator();
		
		while(teamsItr.hasNext()) {
			Team t = teamsItr.next();
			t.printTeamInfoToCSV(filepath);
		}
		System.out.println("Teams have been shown in output.csv!\n");
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
