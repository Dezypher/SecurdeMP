package security;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HTMLTagChecker {
	public static boolean checkTags(String test) {
		if(!test.contains("<script>") 
			&& !test.contains("<html>")
			&& !test.contains("<a>")
			&& !test.contains("<body>")
			&& !test.contains("<head>")
			&& !test.contains("<input>")
			&& !test.contains("<form>")
			&& !test.contains("<button>")
			&& !test.contains("</") 
			&& !test.contains("/>"))
		{
			return true;
		}
		
		return false;
	}
	
	public static String cleanStringTags(String input) {
		return Jsoup.clean(input, Whitelist.none());
	}
	
	public static String cleanStringTagsWithWhitelist(String input) {
		return Jsoup.clean(input, Whitelist.simpleText());
	}
}
