package engine;

public class Parser {

	public static CharType getChartType(char c) {
		if ('0' <= c && c <= '9') return CharType.NUMBER;
		if ('A' <= c && c <= 'Z') return CharType.LETTER;
		if (0x4E00 <= c && c <= 0x9FA5) return CharType.UNICODE;
		return CharType.OTHER;
	}
	
	public static String separate(String s) {
		StringBuilder sb = new StringBuilder();
		CharType last = getChartType(s.charAt(0));
		CharType curr;
		char c;
		for (int i=0,len=s.length(); i<len; i++) {
			c = s.charAt(i);
			curr = getChartType(c);
			if (curr == CharType.OTHER) {
				if (last != CharType.OTHER) {
					sb.append(' ');
				}
			} else if (curr == CharType.UNICODE) {
				if (last == CharType.OTHER) {
					sb.append(c);
				} else {
					sb.append(' ').append(c);
				}
			} else { // Letter and Number
				if (last == CharType.OTHER) {
					sb.append(c);
				} else if (last == curr) {
					sb.append(c);
				} else {
					sb.append(' ').append(c);
				}
			}
			last = curr;
		}
		if (sb.charAt(0) == ' ') {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
}
