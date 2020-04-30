package proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayersCSVReader {
	
	private static PlayersCSVReader instance;

	public static PlayersCSVReader getInstance() {
		// singleton class
		if(instance == null) {
			synchronized(TeamsCSVReader.class) {
				/// this block can only be accessed by one thread at a time
				if(instance == null) {
					instance = new PlayersCSVReader();
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> readData(String filepath, Class<T> data_Type) throws Exception {
		ArrayList<T> records = new ArrayList<T>();
		Path pathToFile = Paths.get(filepath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while(line != null) {
				String[] data = line.split(",");
				Constructor<T> constructor = (Constructor<T>) Player.class.getConstructor(String.class);
				
				switch(data[3]) {
					case "Center":
						constructor = (Constructor<T>) Center.class.getConstructor(String.class);
						break;
					
					case "Forward":
						constructor = (Constructor<T>) Forward.class.getConstructor(String.class);
						break;
					
					case "PointGuard":
						constructor = (Constructor<T>) PointGuard.class.getConstructor(String.class);
						break;
				
					case "Wing":
						constructor = (Constructor<T>) Wing.class.getConstructor(String.class);
						break;
				}
				
				Object obj = constructor.newInstance(new Object[] {line});
				records.add((T) obj);
				line = br.readLine();
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
		return records;
	}
}
