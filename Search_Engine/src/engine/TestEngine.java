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
		String file = "C:/home/wip/wip_aws_orp/address/address_new";
		//file = "address_list";
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(file));
			buildIndex(br);
			
			search("永嘉", 5);
			search("yat tai house", 5);
			search("漾日", 3);
			search("漾日新", 3);
			search("漾日新大", 3);
		} finally {
			if (br != null) br.close();
		}
	}

	private static void search(String keyword, int maxRows) {
		System.out.println("keyword: " + keyword);
		long start = System.currentTimeMillis();
		List<ScoreUnit> units = engine.search(keyword, maxRows);
		long end = System.currentTimeMillis();
		System.out.println("searching: " + (end-start));
		for (ScoreUnit unit : units) {
			System.out.println(unit.getScore() + ":" + unit.getText());
		}
	}

	private static void buildIndex(BufferedReader br)
			throws IOException {
		long start = System.currentTimeMillis();
		long end;
		String line;
		while ((line=br.readLine()) != null) {
			engine.parse(line);
		}
		end = System.currentTimeMillis();
		System.out.println("build index: " + (end-start));
	}
	
	
}
