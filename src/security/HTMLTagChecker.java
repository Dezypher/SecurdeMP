package security;

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
			&& !test.contains("</")) 
		{
			return true;
		}
		
		return false;
	}
}
