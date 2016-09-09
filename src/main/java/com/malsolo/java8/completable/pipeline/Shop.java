package com.malsolo.java8.completable.pipeline;

import static com.malsolo.java8.completable.Util.delay;

import java.util.Random;

public class Shop {

	private final String name;
	private Random random = new Random();

	public Shop(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name, price, code);
	}

	private double calculatePrice(String product) {
		delay(1000L);
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

}
