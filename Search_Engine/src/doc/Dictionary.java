package doc;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import engine.Keys;
import engine.Parser;
import engine.ScoreUnit;

/**
 * Mapping from words to document.
 * @author WIKY.AU
 *
 */
public class Dictionary {

//	private Map<String, Map<String, Document>> fielding  = new HashMap<String, Map<String,Document>>();
	
	//          field       word        text    score
	private Map<String, Map<String, Map<String, Document>>> dic = 
			new HashMap<String, Map<String, Map<String, Document>>>();
	
	private Pattern spacePattern = Pattern.compile(" ");
	
	public void addDocument(Document doc) {
		String[] fieldNames = doc.getFieldNames();
		String text;
		String[] words;
		Document d;
		for (String field : fieldNames) {
			text = doc.getField(field).getText();
			words = spacePattern.split(Parser.separate(text));
			Map<String, Map<String, Document>> wordMap = dic.get(field);
			if (wordMap == null) {
				wordMap = new HashMap<String, Map<String, Document>>();
				dic.put(field, wordMap);
			}
			for (String word : words) {
				Map<String, Document> textMap = wordMap.get(word);
				if (textMap == null) {
					textMap = new HashMap<String, Document>();
					wordMap.put(word, textMap);
				}
				d = textMap.get(text);
				if (d == null) {
					textMap.put(text, doc);
					d = doc;
				} else {
					d.getField(field).increaseScore(Keys.SCORE_DUPLICATE);
				}
				
			}
		}
	}
	
}
