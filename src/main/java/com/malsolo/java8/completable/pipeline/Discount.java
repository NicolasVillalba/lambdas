package com.malsolo.java8.completable.pipeline;

import static com.malsolo.java8.completable.Util.*;

public class Discount {
	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}

		public int getPercentage() {
			return percentage;
		}
	}

	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}

	private static double apply(double price, Code code) {
		delay(1000L);
		return format(price * (100 - code.percentage) / 100);
	}

}