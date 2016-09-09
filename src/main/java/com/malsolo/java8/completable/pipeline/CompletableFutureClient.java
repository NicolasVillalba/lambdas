package com.malsolo.java8.completable.pipeline;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.malsolo.java8.completable.nonblocking.Client;

public class CompletableFutureClient implements Client {
	
	private final Executor executor;
	private final List<Shop> shops;
	
	public CompletableFutureClient(List<Shop> shops) {
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

	@Override
	public List<String> findPrices(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote),executor)))
			.collect(toList())
			;
		
		return priceFutures.stream().map(CompletableFuture::join).collect(toList());
	}

}
