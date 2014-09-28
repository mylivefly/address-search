package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Engine {

	private ScoreGroup group = new ScoreGroup();
	private Pattern spacePattern = Pattern.compile(" ");
	private SequenceTuner seqTuner = new SequenceTuner();
	
	public void parse(String text) {
		if (text == null) return;
		text = text.trim();
		if (text.length() == 0) return;
		String fmtText = Parser.separate(text.toUpperCase());
		String[] parts = spacePattern.split(fmtText);
		for (String part : parts) {
			group.add(part, text);
		}
	}
	
	public List<ScoreUnit> search(String keyword, int maxRows) {
		List<ScoreUnit> units = new ArrayList<ScoreUnit>();
		if (keyword == null || maxRows < 1) return units;
		keyword = keyword.trim();
		if (keyword.length() == 0) return units;
		keyword = Parser.separate(keyword.toUpperCase());
		String[] parts = spacePattern.split(keyword);
		ScoreCompany company = new ScoreCompany();
		for (String part : parts) {
			company.merge(group.get(part));
		}
		units = company.toList();
		// tune score according sequence
		seqTuner.tune(keyword, units);
		// sorting
		Collections.sort(units);
		// cut tail
		if (units.size() > maxRows) {
			for (int i=maxRows, len=units.size(); i<len; i++) {
				units.remove(maxRows);
			}
		}
		return units;
	}
	
}
