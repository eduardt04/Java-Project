package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import database.DatabaseConnection;
import players.Center;
import players.Forward;
import players.PointGuard;
import players.Wing;
import project.Championship;
import project.Team;

public class InsertTab {
	
	private static InsertTab instance = null;
	private static JSplitPane insertTab = null;
	private static ArrayList<JComponent> dataFields = null;
	
	public InsertTab() {
		dataFields = new ArrayList<JComponent>();
		insertTab = new JSplitPane(JSplitPane.	HORIZONTAL_SPLIT, getPlayersAddPanel(), this.getTeamsAddPanel());
		insertTab.setDividerLocation(350);
	}
	
	public JPanel getPlayersAddPanel() {
		JPanel addPlayers = new JPanel();		
		addPlayers.setLayout(null);
		addPlayers.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
															  "Add a player", TitledBorder.LEFT, TitledBorder.TOP));
		
		JLabel playerNameLabel = new JLabel("Player name: ");
		int prefferedSize = (int) playerNameLabel.getPreferredSize().getWidth();
		playerNameLabel.setBounds(165 - prefferedSize/2, 20, prefferedSize, 30);
		addPlayers.add(playerNameLabel);
		
		JTextField playerName = new JTextField();
		playerName.setBounds(90, 40, 150, 30);
		addPlayers.add(playerName);
		dataFields.add(playerName);
		
		JLabel playerHeightLabel = new JLabel("Player height: ");
		prefferedSize = (int) playerHeightLabel.getPreferredSize().getWidth();
		playerHeightLabel.setBounds(165 - prefferedSize/2, 70, prefferedSize, 30);
		addPlayers.add(playerHeightLabel);
		
		JTextField playerHeight = new JTextField();
		playerHeight.setBounds(90, 90, 150, 30);
		addPlayers.add(playerHeight);
		dataFields.add(playerHeight);
		
		JLabel playerAgeLabel = new JLabel("Player age: ");
		prefferedSize = (int) playerAgeLabel.getPreferredSize().getWidth();
		playerAgeLabel.setBounds(165 - prefferedSize/2, 120, prefferedSize, 30);
		addPlayers.add(playerAgeLabel);
		
		JTextField playerAge = new JTextField();
		playerAge.setBounds(90, 140, 150, 30);
		addPlayers.add(playerAge);
		dataFields.add(playerAge);
		
		addPlayers.add(getRadioButtons());
		addPlayers.add(getTeamsDisplay());
		
		JButton addPlayerButton = new JButton("Add the player");
		addPlayerButton.setBounds(90, 390, 150, 30);
		
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPlayer();
			}
		});
		
		addPlayers.add(addPlayerButton);
		return addPlayers;
	}
	
	public void addPlayer() {
		// Validate player's name
		String playerName = ((JTextField)dataFields.get(0)).getText();
		if(playerName.length() < 7) {
			JOptionPane.showMessageDialog(insertTab, "Name is invalid. Too short!");
			return;
		}
		Pattern p = Pattern.compile("[^a-z A-Z]");
		if(p.matcher(playerName).find()) {
			JOptionPane.showMessageDialog(insertTab, "Name contains invalid characters!");
			return;
		}

		int playerAge = Integer.parseInt(((JTextField)dataFields.get(2)).getText());
		if(playerAge < 18 || playerAge > 45) {
			JOptionPane.showMessageDialog(insertTab, "The player is either too young, or too old. Check the age!");
			return;
		}
		
		int playerHeight = Integer.parseInt(((JTextField)dataFields.get(1)).getText());
		if(playerHeight < 130 || playerHeight > 220) {
			JOptionPane.showMessageDialog(insertTab, "The height is invalid!");
			return;
		}

		@SuppressWarnings("rawtypes")
		String playerTeam = (String)((JList)dataFields.get(7)).getSelectedValue();
		
		if(Championship.checkExistentPlayer(playerName, playerHeight, playerAge)) {
			JOptionPane.showMessageDialog(insertTab, "There already is a player with that name!");
			return;
		}
	
		DatabaseConnection db_conn = null;
		int team_id = 0;
		try {
			db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
			ResultSet teamData = db_conn.executeQuery("SELECT * FROM Teams WHERE TeamName = \"" + playerTeam + "\"");
			
			while(teamData.next())
				team_id = teamData.getInt("TeamID");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(((JRadioButton)dataFields.get(3)).isSelected()) {
			PointGuard newPlayer = new PointGuard(playerName, playerHeight, playerAge, Championship.getTeambyName(playerTeam));
			Championship.addPlayer(newPlayer);
			db_conn.insertPlayerQuery(playerName, playerHeight, playerAge, "Point Guard", team_id);
			JOptionPane.showMessageDialog(insertTab, "Successfully added!");
			GUI.updateTabbedPanes();
		}
		else if(((JRadioButton)dataFields.get(4)).isSelected()) {
			Center newPlayer = new Center(playerName, playerHeight, playerAge, Championship.getTeambyName(playerTeam));
			Championship.addPlayer(newPlayer);
			db_conn.insertPlayerQuery(playerName, playerHeight, playerAge, "Center", team_id);
			JOptionPane.showMessageDialog(insertTab, "Successfully added!");
			GUI.updateTabbedPanes();
		}
		else if(((JRadioButton)dataFields.get(5)).isSelected()) {
			Wing newPlayer = new Wing(playerName, playerHeight, playerAge, Championship.getTeambyName(playerTeam));
			Championship.addPlayer(newPlayer);
			db_conn.insertPlayerQuery(playerName, playerHeight, playerAge, "Wing", team_id);
			JOptionPane.showMessageDialog(insertTab, "Successfully added!");
			GUI.updateTabbedPanes();
		}
		else {
			Forward newPlayer = new Forward(playerName, playerHeight, playerAge, Championship.getTeambyName(playerTeam));
			Championship.addPlayer(newPlayer);
			db_conn.insertPlayerQuery(playerName, playerHeight, playerAge, "Forward", team_id);
			JOptionPane.showMessageDialog(insertTab, "Successfully added!");
			GUI.updateTabbedPanes();
		}
	}
	
	public JPanel getRadioButtons() {
		JPanel radioButtons = new JPanel();
		radioButtons.setLayout(null);
		radioButtons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Player position:",
																TitledBorder.CENTER, TitledBorder.TOP));
		radioButtons.setBounds(45, 180, 240, 80);
		
		JRadioButton PGButton = new JRadioButton("Point Guard");    
		JRadioButton CButton = new JRadioButton("Center");    
		JRadioButton WButton = new JRadioButton("Wing");
		JRadioButton FButton = new JRadioButton("Forward");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(PGButton); bg.add(CButton); bg.add(WButton); bg.add(FButton);  
		
		PGButton.setBounds(10, 20, 130, 30); CButton.setBounds(150, 20, 130, 30);
		FButton.setBounds(10, 40, 130, 30); WButton.setBounds(150, 40, 130, 30);
		
		radioButtons.add(PGButton); radioButtons.add(CButton);
		radioButtons.add(WButton); radioButtons.add(FButton);
		
		dataFields.add(PGButton); dataFields.add(CButton);
		dataFields.add(WButton); dataFields.add(FButton);
		
		return radioButtons;
	}
	
	public JScrollPane getTeamsDisplay() {
		String teamNames[] = new String[100];
		DatabaseConnection db_conn = null;
		
		try {
			db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
			ResultSet teamsData = db_conn.executeQuery("SELECT * FROM Teams");
			
			int i = 0;
			while(teamsData.next()) {
				teamNames[i] = teamsData.getString("TeamName");
				i++;
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JList<String> teamsList = new JList<String>(teamNames);
		JScrollPane scrollTeams = new JScrollPane(teamsList);
		
		scrollTeams.setBounds(80, 270, 170, 100);
		scrollTeams.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Team:"));
		
		dataFields.add(teamsList);
		
		return scrollTeams;
	}
	
	public JPanel getTeamsAddPanel() {
		JPanel addTeams = new JPanel();
		addTeams.setLayout(null);
		addTeams.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
															"Add a team", TitledBorder.LEFT, TitledBorder.TOP));
		
		JLabel teamNameLabel = new JLabel("Team name: ");
		int prefferedSize = (int) teamNameLabel.getPreferredSize().getWidth();
		teamNameLabel.setBounds(165 - prefferedSize/2, 20, prefferedSize, 30);
		addTeams.add(teamNameLabel);
		
		JTextField teamName = new JTextField();
		teamName.setBounds(90, 40, 150, 30);
		addTeams.add(teamName);
		
		JLabel teamAbbrLabel = new JLabel("Team abbreviation: ");
		prefferedSize = (int) teamAbbrLabel.getPreferredSize().getWidth();
		teamAbbrLabel.setBounds(165 - prefferedSize/2, 70, prefferedSize, 30);
		addTeams.add(teamAbbrLabel);
		
		JTextField teamAbbr = new JTextField();
		teamAbbr.setBounds(90, 90, 150, 30);
		addTeams.add(teamAbbr);
		
		JButton addTeamButton = new JButton("Add the team");
		prefferedSize = (int) addTeamButton.getPreferredSize().getWidth();
		addTeamButton.setBounds(165 - prefferedSize/2, 140, prefferedSize, 30);
		
		addTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = teamName.getText();
				String abbr = teamAbbr.getText();
				
				Pattern p = Pattern.compile("[^a-z A-Z]");
				if(p.matcher(name).find()) {
					JOptionPane.showMessageDialog(insertTab, "Team name contains invalid characters!");
					return;
				}
				
				if(p.matcher(abbr).find()) {
					JOptionPane.showMessageDialog(insertTab, "Abbreviation contains invalid characters!");
					return;
				}
				
				if(abbr.length() != 3) {
					JOptionPane.showMessageDialog(insertTab, "Abbreviation length is not 3!");
					return;
				}
				
				if(Championship.getTeambyName(name) != null) {
					JOptionPane.showMessageDialog(addTeams, "A team with this name already exists!");
					return;
				}
				
				Championship.addTeam(new Team(name, abbr));
				try {
					DatabaseConnection db_conn = new DatabaseConnection("jdbc:mysql://localhost:3306/project_db", "db_connection", "dbconnpass4");
					db_conn.insertTeamQuery(name, abbr);
					JOptionPane.showMessageDialog(insertTab, "Team successfully added!");
					GUI.updateTabbedPanes();
				} catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		
		addTeams.add(addTeamButton);
		
		return addTeams;
	}
	
	public static JSplitPane getInsertTab(boolean refresh) {
		if(instance == null) 
			instance = new InsertTab();
		
		if(refresh == true)
			instance = new InsertTab();
		
		return insertTab;
	}
	
}
