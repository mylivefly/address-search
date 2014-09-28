package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
		long start, end;
		try {
			start = System.currentTimeMillis();
			br = new BufferedReader(new FileReader("address_list"));
			String line;
			while ((line=br.readLine()) != null) {
				engine.parse(line);
			}
			end = System.currentTimeMillis();
			System.out.println("build index: " + (end-start));
			
			start = System.currentTimeMillis();
			List<ScoreUnit> units = engine.search("yat tai house", 10);
			end = System.currentTimeMillis();
			System.out.println("searching: " + (end-start));
			for (ScoreUnit unit : units) {
				System.out.println(unit.getScore() + ":" + unit.getText());
			}
		} finally {
			if (br != null) br.close();
		}
	}
	
	
}
