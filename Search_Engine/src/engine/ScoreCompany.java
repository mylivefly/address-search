package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * As a item in ScoreGroup, contain the hash table of text, ScoreUnit can be retrieved by the text. 
 * @author WIKY.AU
 *
 */
public class ScoreCompany {

	private Map<String, ScoreUnit> company = new HashMap<String, ScoreUnit>();
	
	public void add(String text) {
		ScoreUnit su = company.get(text);
		if (su == null) {
			su = new ScoreUnit(text);
			company.put(text, su);
		} else {
			su.increase(Keys.SCORE_DUPLICATE);
		}
	}
	
	public ScoreUnit get(String text) {
		return company.get(text);
	}
	
	public void addUnit(ScoreUnit unit) {
		ScoreUnit su = company.get(unit.getText());
		if (su == null) {
			company.put(unit.getText(), unit);
		} else {
			su.increase(unit.getScore());
		}
	}
	
	public List<ScoreUnit> toList() {
		List<ScoreUnit> list = new ArrayList<ScoreUnit>(company.values());
		return list; 
	}

	public void merge(ScoreCompany company) {
		if (company == null) return;
		for (ScoreUnit unit : company.toList()) {
			addUnit(unit);
		}
	}
	
}
