package engine;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Engine {

	private ScoreGroup group = new ScoreGroup();
	private Pattern spacePattern = Pattern.compile(" ");
	
	public void parse(String text) {
		if (text == null) return;
		text = text.trim();
		if (text.length() == 0) return;
		text = Parser.separate(text.toUpperCase());
		String[] parts = spacePattern.split(text);
		for (String part : parts) {
			group.add(part, text);
		}
	}
	
	public void search(String keyword) {
		if (keyword == null) return;
		keyword = keyword.trim();
		if (keyword.length() == 0) return;
		keyword = Parser.separate(keyword.toUpperCase());
		String[] parts = spacePattern.split(keyword);
		ScoreCompany company = new ScoreCompany();
		for (String part : parts) {
			company.merge(group.get(part));
		}
		List<ScoreUnit> units = company.toList();
		Collections.sort(units);
		for (ScoreUnit unit : units) {
			System.out.println(unit.getScore() + ": " + unit.getText());
		}
	}
	
}
