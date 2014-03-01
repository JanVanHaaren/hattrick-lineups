package api.util;

import java.text.DecimalFormat;
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
	
	public static String get5PrecisionDouble(Double d){
		DecimalFormat df = new DecimalFormat("##.#####");
		return df.format(d);
	}
	
	public static String getFactor10Integer(Double d){
		return Integer.toString((int)(d*10));
	}
	
	public static String getDoubleCharacter(Double d){
		return Character.toString((char)(((int)(d*10)) + 97));
	}
}
