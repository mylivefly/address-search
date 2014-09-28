package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestEngine {

	static Engine engine = new Engine();
	
	public static void main(String[] args) {
		try {
			execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void execute() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("address_list"));
			String line;
			while ((line=br.readLine()) != null) {
				engine.parse(line);
			}
			
			engine.search("LOI");
		} finally {
			if (br != null) br.close();
		}
	}
	
	
}
