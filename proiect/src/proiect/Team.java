package proiect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Team {
	
	private String team_name, team_abbr;
	private ArrayList<Player> team_players;
	
	public Team(String t_name, String t_abbr) {
		this.team_name = t_name;
		this.team_abbr = t_abbr;
		this.team_players = new ArrayList<Player>();
	}
	
	public Team(String line) {
		String[] values = line.split(",");
		if(values.length == 2) {
			this.team_name = values[0];
			this.team_abbr = values[1];
		}
		this.team_players = new ArrayList<Player>();
	}
	
	public String getTeamName() {
		return this.team_name;
	}
	
	public String getTeamAbbr() {
		return this.team_abbr;
	}
	
	public ArrayList<Player> getPlayers() {
		return this.team_players;
	}
	
	public void hirePlayer(Player p) {
		team_players.add(p);
	}
	
	public void printTeamPlayers() {
		
		Iterator<Player> playersItr = team_players.iterator();
		
		while(playersItr.hasNext()) {
			Player p = playersItr.next();
			p.printPlayerInfo();
			
		}
	}
	
	public void printTeamInfoToCSV(String filepath) throws Exception {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
			writer.append(this.team_name + ", " + this.team_abbr + "\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
