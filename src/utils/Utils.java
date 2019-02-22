package utils;

import java.io.PrintWriter;

public class Utils {
	public static void handleProcedure(Procedure procedure) {
		try {
			procedure.invoke();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void handleProcedure(PrintWriter procedure) {
		try {
			((Procedure) procedure).invoke();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}