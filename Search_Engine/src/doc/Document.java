package doc;

import java.util.HashMap;
import java.util.Map;

import engine.ScoreUnit;

/**
 * Contain the fields of a document, field is including name, text and score
 * @author WIKY.AU
 *
 */
public class Document {

	private Map<String, ScoreUnit> fields = new HashMap<String, ScoreUnit>();
	
	private int score = 0;
	
	public void addField(String name, String value) {
		ScoreUnit unit = new ScoreUnit(value);
		fields.put(name, unit);
	}
	
	public String[] getFieldNames() {
		return fields.keySet().toArray(new String[0]);
	}
	
	public ScoreUnit getField(String name) {
		return fields.get(name);
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int score) {
		this.score += score;
	}
	
}
