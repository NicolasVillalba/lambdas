package com.malsolo.java8.completable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Util {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    
    private static final Random random = new Random();

    public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
    
    public static void randomDelay(int min, int gap) {
    	int delay = min + random.nextInt(gap);
		try {
			Thread.sleep(delay);
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
