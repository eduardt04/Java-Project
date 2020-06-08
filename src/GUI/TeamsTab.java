package GUI;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.DatabaseConnection;
import players.Center;
import players.Forward;
import players.PointGuard;
import players.Wing;
import project.Championship;
import project.Player;
import project.Team;

// singleton class for building the Teams Tab in the JTabbedPane
public class TeamsTab {
	
	private static TeamsTab instance = null;
	private static JSplitPane teamsTab = null;
	
	private JList<Team> teamsList;
	
	private TeamsTab() {
		teamsTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getTeamsList(), getTeamsPlayers());
		teamsTab.setDividerLocation(350);
	}
	
	@SuppressWarnings("serial")
	public JScrollPane getTeamsList() {
		DatabaseConnection db_conn = null;
		try {
			db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
			ResultSet teamsData = db_conn.executeQuery("SELECT * FROM Teams");
			
			Vector<Team> allTeams = new Vector<Team>();
			while(teamsData.next())
				allTeams.add(new Team(teamsData.getString("TeamName"), teamsData.getString("Abbreviation")));
				
			this.teamsList = new JList<Team>(allTeams);
			teamsList.setVisibleRowCount(10);
			teamsList.setCellRenderer(new DefaultListCellRenderer() {
				public Component getListCellRendererComponent(JList<?> teamsList, Object value, int index, boolean isSelected, boolean focused) {
					Component renderer = super.getListCellRendererComponent(teamsList, value, index, isSelected, focused);
					if(renderer instanceof JLabel && value instanceof Team)
						((JLabel)renderer).setText(((Team)value).getData());
					return renderer;
				}
			});
			teamsList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					teamsTab.setRightComponent(getTeamsPlayers());
					teamsTab.setDividerLocation(350);
					GUI.updateTabbedPane(1, teamsTab);
				}
				
			});
			return new JScrollPane(teamsList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	public JScrollPane getTeamsPlayers() {
		Object t = teamsList.getSelectedValue();
		
		if (t == null) {
			t = Championship.getTeambyName("Los Angeles Lakers");
		}
			
		if (t instanceof Team) {
			
			Team chosen_team = (Team)t;
			DatabaseConnection db_conn = null;
			try {
				db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
				ResultSet playersData = db_conn.executeQuery("SELECT * FROM Players JOIN Teams ON (Players.TeamID = Teams.TeamID) "
						+ "WHERE TeamName = '" + chosen_team.getName() + "'");
			
				Vector<Player> teamPlayers = new Vector<Player>();
				
				while(playersData.next()) {
					switch(playersData.getString("Position")) {
					case "Point Guard":
						teamPlayers.add(new PointGuard(playersData.getString("Name"), playersData.getInt("Height"), 
													   playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
						break;
					case "Center":
						teamPlayers.add(new Center(playersData.getString("Name"), playersData.getInt("Height"), 
					   			  				   playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
						break;
					case "Wing":
						teamPlayers.add(new Wing(playersData.getString("Name"), playersData.getInt("Height"), 
					   			  			     playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
						break;
					case "Forward":
						teamPlayers.add(new Forward(playersData.getString("Name"), playersData.getInt("Height"), 
					   			  				    playersData.getInt("Age"), Championship.getTeambyName(playersData.getString("TeamName"))));
						break;
					default:
						break;
					}
				}
			
				JList<Player> playersList = new JList<Player>(teamPlayers);
				playersList.setVisibleRowCount(10);
				playersList.setCellRenderer(new DefaultListCellRenderer() {
					public Component getListCellRendererComponent(JList<?> playersList, Object value, int index, boolean isSelected, boolean focused) {
						Component renderer = super.getListCellRendererComponent(playersList, value, index, isSelected, focused);
						if(renderer instanceof JLabel && value instanceof Player)
							((JLabel)renderer).setText(((Player)value).getData());
						return renderer;
					}
				});
				
				return new JScrollPane(playersList);
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}			
		}
		return null;
	}
	
	public static JSplitPane getTeamsTab(boolean refresh) {
		
		if(instance == null)
			instance = new TeamsTab();
		
		if(refresh == true)
			instance = new TeamsTab();
		
		return teamsTab;
	}
	
}
