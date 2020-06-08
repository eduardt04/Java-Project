package database;

import java.sql.*;

public class DatabaseConnection {

	private Connection db_connection;
	
	public DatabaseConnection(String connection_url, String connection_user, String connection_pass) throws SQLException {
		this.db_connection = DriverManager.getConnection(connection_url, connection_user, connection_pass);
		
	}
	
	public void printQuery(String query) {
		try {
			// create a statement and execute the query
			Statement myStatement = this.db_connection.createStatement();
			ResultSet myData = myStatement.executeQuery(query);
			ResultSetMetaData myMetaData = myData.getMetaData();
			
			while(myData.next()) {
				for(int i = 1; i <= myMetaData.getColumnCount(); i++)
					System.out.print(myData.getString(myMetaData.getColumnName(i)) + " ");
				System.out.println();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void insertPlayerQuery(String Name, int height, int age, String position, int team_id) {
		try {
			String query = "INSERT INTO Players (Name, Height, Age, Position, TeamID)" + "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = db_connection.prepareStatement(query);
			statement.setString(1, Name);
			statement.setInt(2, height);
			statement.setInt(3, age);
			statement.setString(4, position);
			statement.setInt(5, team_id);
			
			statement.execute();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void insertTeamQuery(String Name, String abbreviation) {
		try {
			String query  = "INSERT INTO Teams (TeamName, Abbreviation)" + "VALUES (?, ?)";
			
			PreparedStatement statement = db_connection.prepareStatement(query);
			statement.setString(1, Name);
			statement.setString(2, abbreviation);
			
			statement.execute();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		try {
			// create a statement and execute the query
			Statement myStatement = this.db_connection.createStatement();
			ResultSet myData = myStatement.executeQuery(query);
			return myData;
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
}
