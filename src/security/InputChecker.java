package security;

public class InputChecker {
	public static boolean checkInput(String test) {
		if(!test.contains(";")
			&& !test.contains("--"))
			return true;
		return false;
	}
}
