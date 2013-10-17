package api.util;

import java.text.SimpleDateFormat;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class Utils {

	public static SimpleDateFormat getHattrickDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	public static int getIntFromString(String string) {
		return Integer.valueOf(string);
	}

	public static float getFloatFromString(String string) {
		return Float.valueOf(string.replace(",", "."));
	}

	public static boolean getBooleanFromString(String string) {
		return Boolean.valueOf(string);
	}
}
