package engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Contain the hash table of words, ScoreCompany can be retrieved by the words.
 * @author WIKY.AU
 *
 */
public class ScoreGroup {

	private Map<String, ScoreCompany> group = new HashMap<String, ScoreCompany>();
	
	public void add(String key, String text) {
		ScoreCompany company = group.get(key);
		if (company == null) {
			company = new ScoreCompany();
			group.put(key, company);
		}
		company.add(text);
	}
	
	public ScoreCompany get(String key) {
		return group.get(key);
	}
	
}
