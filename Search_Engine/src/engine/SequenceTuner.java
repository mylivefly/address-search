package engine;

import java.util.List;

public class SequenceTuner {

	public void tune(String keyword, List<ScoreUnit> units) {
		for (ScoreUnit unit : units) {
			tune(keyword, unit);
		}
	}
	
	public void tune(String keyword, ScoreUnit unit) {
		int score = matchScore(keyword, unit.getText());
		unit.increaseScore(score);
	}
	
	private int matchScore(String key, String item) {
		item = Parser.separate(item);
		int matches = matchfrom(key, item, 0);
		int m2;
		for (int i=1; i<=5; i++) {
			m2 = matchfrom(key, item, i);
			if (matches < m2) {
				matches = m2;
			}
		}
		return matches * Keys.SCORE_SEQUENCE;
	}
	
	private int matchfrom(String key, String item, int from) {
		if (key.length() <= from+from) return 0;
		int i=from+1,len=key.length();
		for (; i<=len; i++ ) {
			if (item.indexOf(key.substring(from, i)) == -1) {
				break;
			}
		}
		return i-from-1;
	}
	
}
