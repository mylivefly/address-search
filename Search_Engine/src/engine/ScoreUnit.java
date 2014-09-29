package engine;

public class ScoreUnit implements Comparable<ScoreUnit>, Cloneable {

	private String text;
	
	private int score = Keys.SCORE_APPEAR;

	public ScoreUnit(String text) {
		this.text = text;
	}
	
	public void increaseScore(int score) {
		this.score += score;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreUnit other = (ScoreUnit) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public int compareTo(ScoreUnit o) {
		return o.score - score;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
