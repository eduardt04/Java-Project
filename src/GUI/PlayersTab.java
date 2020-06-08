package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import database.DatabaseConnection;
import players.Center;
import players.Forward;
import players.PointGuard;
import players.Wing;
import project.Championship;
import project.Player;

// singleton class for building the Players Tab in the JTabbedPane
public class PlayersTab {
	
	private static PlayersTab instance = null;
	private static JSplitPane playersTab = null;
	
	private PlayersTab() {
		playersTab = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.getPlayersTable("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID)"), this.getButtonsPanel());
		playersTab.setDividerLocation(250);
	}
	
	public JPanel getButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		
		JButton showPlayersButton = new JButton("Show players");
		showPlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID)");
			}
		});
		buttonsPanel.add(showPlayersButton);
		
		JButton showPlayersbyHeight = new JButton("Show players by height");
		showPlayersbyHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID) ORDER BY Height");
			}
		});
		buttonsPanel.add(showPlayersbyHeight);
		
		JButton showPlayersbyAge = new JButton("Show players by age");
		showPlayersbyAge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID) ORDER BY Age");
			}
		});
		buttonsPanel.add(showPlayersbyAge);
		
		JButton showPlayersAlphabetically = new JButton("Show players alphabetically");
		showPlayersAlphabetically.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID) ORDER BY Name");
			}
		});
		buttonsPanel.add(showPlayersAlphabetically);
		
		JButton showPlayersbyTeam = new JButton("Show players by team");
		showPlayersbyTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID) ORDER BY TeamName");
			}
		});
		buttonsPanel.add(showPlayersbyTeam);
		
		return buttonsPanel;
	}
	
	public void updateData(String query) {
		playersTab.setTopComponent(getPlayersTable(query));
		playersTab.setDividerLocation(250);
		GUI.updateTabbedPane(0, playersTab);
	}
	
	public JScrollPane getPlayersTable(String query) {
		DatabaseConnection db_conn = null;
		try {
			db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
			ResultSet playersData = db_conn.executeQuery(query);
			
			List<Player> allPlayers = new ArrayList<Player>();
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
			
			JTable table = new JTable();
			AbstractTableModel playersTable = new PlayersTable(allPlayers);
			JScrollPane playersPanel = new JScrollPane(table);
			table.setModel(playersTable);
			return playersPanel;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSplitPane getPlayersTab(boolean refresh) {
		
		if(instance == null)
			instance = new PlayersTab();
		
		if(refresh == true)
			instance = new PlayersTab();
		
		return playersTab;
	}
	
}
