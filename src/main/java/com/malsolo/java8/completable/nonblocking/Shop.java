package com.malsolo.java8.completable.nonblocking;

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

	public double getPrice(String product) {
		return calculatePrice(product);
	}

	private double calculatePrice(String product) {
		delay(1000L);
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

}
