package com.malsolo.java8.completable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Util {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

    public static double format(double number) {
        synchronized (FORMATTER) {
            return new Double(FORMATTER.format(number));
        }
    }
}
