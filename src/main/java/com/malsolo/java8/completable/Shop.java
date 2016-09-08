package com.malsolo.java8.completable;

import static com.malsolo.java8.completable.Util.delay;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

	@SuppressWarnings("unused")
	private final String name;
	private Random random = new Random();

	public Shop(String name) {
		this.name = name;
	}

	public Future<Double> getPrice(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				futurePrice.complete(calculatePrice(product));
			} catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		return futurePrice;
	}

	public Future<Double> getPriceAsync(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	private double calculatePrice(String product) {
		delay(1000L);
		if (Math.random() < 0.2) {
			throw new RuntimeException("Product not available: " + product);
		} else {
			return random.nextDouble() * product.charAt(0) + product.charAt(1);
		}
	}
}
