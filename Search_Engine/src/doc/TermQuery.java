package doc;

public class TermQuery {

	private String field;
	
	private String word;
	
	private Occur occur = Occur.SHOULD;

	public TermQuery(String field, String word, Occur occur) {
		this.field = field;
		this.word = word;
		this.occur = occur;
	}
	
	public TermQuery(String field, String word) {
		this.field = field;
		this.word = word;
	}

	public String getField() {
		return field;
	}

	public String getWord() {
		return word;
	}

	public Occur getOccur() {
		return occur;
	}
	
}
