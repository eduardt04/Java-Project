package proiect;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {

	NBA championship;
	
	public Menu() {
		this.championship = new NBA();
		/// clear content of output CSV before running.
		try {
			FileWriter fw = new FileWriter("src/proiect/output.csv", false);
			fw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void Run() {
		
		String option = "";
		Scanner input = new Scanner(System.in);
		
		while(option.equals("exit") == false){
		
			System.out.println("Choose which feature you want to use: (use the code after ->) ");
			System.out.println("Add a team -> addteam");
			System.out.println("Add a player -> addplayer");
			System.out.println("Show all teams(to CSV) -> showteams");
			System.out.println("Print players alphabetically(to CSV) -> prtalph");
			System.out.println("Exit the menu -> exit");
			
			System.out.println("Choose an option: ");
			option = input.nextLine();
		
			
			switch(option.toLowerCase()) {
			
				case "addteam":
					
					System.out.println("Adding a team: ");
					
					System.out.println("Team name:");
					String team_name = "";
					team_name = input.nextLine();
					
					System.out.println("Team abbreviation: ");
					String team_abbr = "";
					team_abbr = input.nextLine();
					
					Team new_team = new Team(team_name, team_abbr);
					championship.addTeam(new_team);
					
					break;
				
				case "addplayer":
					
					if(championship.getTeamsNumber() == 0) {
						System.out.println("There are no teams yet. Add teams before you add players.");
						break;
					}
					
					System.out.println("Adding a player: ");
					
					System.out.println("Player name:");
					String player_name = "";
					player_name = input.nextLine();
					
					System.out.println("Player height: ");
					int player_height;
					
					try {
						player_height = Integer.parseInt(input.nextLine());
					} 
					catch (NumberFormatException e) {
						System.out.println("The height provided is not valid.");
						break;
					}
					
					System.out.println("Player age: ");
					int player_age;
					
					try {
						player_age = Integer.parseInt(input.nextLine());
					} 
					catch (NumberFormatException e) {
						System.out.println("The age provided is not valid.");
						break;
					}
					
					System.out.println("To which team will the player sign? (use the abbreviation)");
					championship.printTeams();
					
					String abbr = "";
					abbr = input.nextLine();
					Team player_team = championship.getTeamByAbbr(abbr);
					
					if(player_team == null) {
						System.out.println("The abbreviation was not valid.");
					}
					else {
						Player new_player = new Center(player_name, player_height, player_age, 15, player_team);
						player_team.hirePlayer(new_player);
						System.out.println(player_name + " has signed for " + player_team.getTeamName());
					}
					break;
				
				case "showteams":
					try {
						championship.printTeamsToCSV("src/proiect/output.csv");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					
				case "prtalph":
					ArrayList<Player> players = championship.getPlayers();
					Collections.sort(players, Comparator.comparing(Player::getName));
					for(Player p : players)
						try {
							p.printPlayerInfoToCSV("src/proiect/output.csv");
						} catch (Exception e) {
							e.printStackTrace();
						}
					break;
					
				case "exit":
					System.out.println("Program has been finished.");
					break;
					
				default:
					System.out.println("This is not a valid feature. Please try again.");
			}		
		}
		input.close();
	}
}
