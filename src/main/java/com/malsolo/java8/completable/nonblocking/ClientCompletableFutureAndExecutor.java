package com.malsolo.java8.completable.nonblocking;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class ClientCompletableFutureAndExecutor extends AbstractClient {

	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}
	});
	
	@Override
	public List<String> findPrices(String product) {
		
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> shop.getName() + " price is " + shop.getPrice(product), executor))
				.collect(Collectors.toList());

		return priceFutures.stream().map(CompletableFuture::join).collect(toList());
	}

}
