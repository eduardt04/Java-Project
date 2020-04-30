package proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TeamsCSVReader {

	private static TeamsCSVReader instance;
	
	public static TeamsCSVReader getInstance() {
		// singleton class
		if(instance == null) {
			synchronized(TeamsCSVReader.class) {
				/// this block can only be accessed by one thread at a time
				if(instance == null) {
					instance = new TeamsCSVReader();
				}
			}
		}
		return instance;
	}
	
	public <T> ArrayList<T> readData(String filepath, Class<T> data_Type) throws Exception {
		ArrayList<T> records = new ArrayList<T>();
		Path pathToFile = Paths.get(filepath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while(line != null) {
				Constructor<T> constructor = data_Type.getConstructor(String.class);
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
