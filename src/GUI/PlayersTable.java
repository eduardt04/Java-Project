package GUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import project.Player;

@SuppressWarnings("serial")
public class PlayersTable extends AbstractTableModel {
	private String[] columnNames = {"Name", "Height", "Age", "Position", "Team"};
		
	private List<Player> playersList;
	
	public PlayersTable(List<Player> playersList) {
		this.playersList = playersList;
	}
	
	public int getRowCount() {
		return playersList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player player = playersList.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return player.getName();
		case 1:
			return player.getHeight();
		case 2:
			return player.getAge();
		case 3:
			return player.getPosition();
		case 4:
			return player.getTeam().getName();
		default:
			break;
		}
		return null;
	}
	
	public List<Player> getPlayersList() {
		return this.playersList;
	}
	
	public void setPlayersList(List<Player> playersList) {
		this.playersList = playersList;
	}
	
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
}
