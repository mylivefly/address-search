package engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {

	public static void main(String[] args) {
		//regxChinese();
		
		String ss = "a,b;c/桑s德，斯；AB  dD3d！！！dd5×22  2字符@＃￥是％个＆国×经……）——＋－＝串";
//		String p = "[[^0-9]&&[^a-z]&&[^A-Z]&&[^\u4E00-\u9FA5]]+";
//		p = ss.replaceAll(p, " ");
//		System.out.println(p);
//		p = p.toUpperCase();
//		p = separate(p);
//		System.out.println(p);
		System.out.println(separate(ss.toUpperCase()));
		
		String text = separate(ss.toUpperCase());
		text = text.replaceAll("(AB|C)", "<$1>");
		System.out.println(text);
		
	}

	static CharType getChartType(char c) {
		if ('0' <= c && c <= '9') return CharType.NUMBER;
		if ('A' <= c && c <= 'Z') return CharType.LETTER;
		if (0x4E00 <= c && c <= 0x9FA5) return CharType.UNICODE;
		return CharType.OTHER;
	}
	
	static String separate(String s) {
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
		return sb.toString();
	}
	
	public static void regxChinese() 
	   { 
	       String source = "桑德斯ABdD3ddd5222字符串转换健，康康可能34f丰富4f成小写dx5kljfdsljDSknf943立即地方立即的ddd顶顶顶3"; 
	       String reg_charset = "([a-z]*)([A-Z]*)([0-9]*)([\u4E00-\u9FA5]*)"; 
	       Pattern p = Pattern.compile(reg_charset); 
	       Matcher m = p.matcher(source); 
	       while (m.find()) 
	       { 
	           System.out.println("0~~~ " +  m.group(0)); 
	           System.out.println("1~~~ " +  m.group(1)); 
	           System.out.println("2~~~ " +  m.group(2)); 
	           System.out.println("3~~~ " +  m.group(3)); 
	           System.out.println("4~~~ " +  m.group(4)); 
	       } 
	   } 
	
}
