package com.malsolo.java8.completable.completion;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class BestPriceFinder {

	private final List<Shop> shops;
	private final Executor executor;
	
	public BestPriceFinder(List<Shop> shops) {
		this.shops = shops;
		executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		});
	}

	public Stream<CompletableFuture<String>> findPricesStream(String product) {
		return shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
	}

}
