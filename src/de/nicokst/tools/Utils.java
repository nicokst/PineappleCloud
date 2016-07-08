package de.nicokst.tools;

public class Utils {
	
	public static String join(String[] lines) {
		String s = "";
		for(int i = 0; i < lines.length; i++) {
			s = s + " " + lines[i];
		}
		return s.trim();
	}

}
