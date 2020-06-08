package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class GUI {
	
	private JFrame window;
	private static JTabbedPane tabbedPane = new JTabbedPane();
	
	public GUI(String title) {
		this.window = new JFrame(title);
		window.setSize(new Dimension(700, 500));
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	// function used to add tabs to the JTabbedPane at the top of the window
	static void addTab(JTabbedPane tabbedPane, String label) {
	    JButton button = new JButton(label);
	    tabbedPane.addTab(label, null, button, label);
	  }
	
	
	public static void addPlayersTab(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Players", PlayersTab.getPlayersTab(false));
		tabbedPane.setBackgroundAt(0, new Color(150, 100, 255));
	}
	
	public void addTeamsTab(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Teams", TeamsTab.getTeamsTab(false));
		tabbedPane.setBackgroundAt(1, new Color(150, 100, 255));
	}
	
	public void addInsertTab(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Add players/teams", InsertTab.getInsertTab(false));
		tabbedPane.setBackgroundAt(2, new Color(150, 100, 255));
	}
	
	public void buildTabbedPane() {
	    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    
	    addPlayersTab(tabbedPane);
	    addTeamsTab(tabbedPane);
	    addInsertTab(tabbedPane);
	    
	    window.add(tabbedPane, BorderLayout.CENTER);
	    window.setVisible(true);
	}
	
	public static void updateTabbedPane(int tab_number, Component cnew) {
		tabbedPane.setComponentAt(tab_number, cnew);
	}
	
	public static void updateTabbedPanes() {
		tabbedPane.setComponentAt(0, PlayersTab.getPlayersTab(true));
		tabbedPane.setComponentAt(1, TeamsTab.getTeamsTab(true));
		tabbedPane.setComponentAt(2, InsertTab.getInsertTab(true));
	}
}
