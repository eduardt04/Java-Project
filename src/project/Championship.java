package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;
import players.Center;
import players.Forward;
import players.PointGuard;
import players.Wing;

public class Championship {
	
	static Championship instance = null;
	static private ArrayList<Player> allPlayers;
	static private ArrayList<Team> allTeams;
	
	public Championship() {
		allPlayers = new ArrayList<Player>();
		allTeams = new ArrayList<Team>();
		
		DatabaseConnection db_conn = null;
		try {
			db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
			ResultSet teamsData = db_conn.executeQuery("SELECT * FROM Teams");
			
			while(teamsData.next())
				allTeams.add(new Team(teamsData.getString("TeamName"), teamsData.getString("Abbreviation")));
			
			ResultSet playersData = db_conn.executeQuery("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID)");
			
			while(playersData.next()) {
				switch(playersData.getString("Position")) {
				case "Point Guard":
					allPlayers.add(new PointGuard(playersData.getString("Name"), playersData.getInt("Height"), 
												  playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
					break;
				case "Center":
					allPlayers.add(new Center(playersData.getString("Name"), playersData.getInt("Height"), 
				   			  				  playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
					break;
				case "Wing":
					allPlayers.add(new Wing(playersData.getString("Name"), playersData.getInt("Height"), 
				   			  				playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
					break;
				case "Forward":
					allPlayers.add(new Forward(playersData.getString("Name"), playersData.getInt("Height"), 
				   			  				   playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
					break;
				default:
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkExistentPlayer(String name, int height, int age) {
		for(Player p : allPlayers)
			if(p.getName().equals(name))
				return true;
		return false;
	}
	
	public static Team getTeambyName(String name) {
		for(Team t : allTeams)
			if(t.getName().equals(name))
				return t;
		return null;
	}
	
	public static void addPlayer(Player p) {
		allPlayers.add(p);
	}
	
	public static void addTeam(Team t) {
		allTeams.add(t);
	}
	
	public static Championship getChampionship() {
		if(instance == null)
			instance = new Championship();
		return instance;
	}
}
