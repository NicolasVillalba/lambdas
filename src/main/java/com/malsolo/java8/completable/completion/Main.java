package com.malsolo.java8.completable.completion;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {
		List<Shop> shops = Arrays.asList(
				new Shop("BestPrice"), 
				new Shop("LetsSaveBig"), 
				new Shop("MyFavoriteShop"),
				new Shop("Amazon1"),
				new Shop("BuyItAll")
				);
		
		BestPriceFinder finder = new BestPriceFinder(shops);
		
		long start = System.nanoTime();
		CompletableFuture[] futures = finder.findPricesStream("iPhone7")
				.map(f -> f.thenAccept(
						s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
				.toArray(size -> new CompletableFuture[size]);
		
		CompletableFuture.allOf(futures).join();
		System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
	}
	
	private void summary() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Double> future = executor.submit(() -> doSomeLongComputation());
		System.out.println("Do something else");
		Double result = future.get();
		System.out.println(result);
		
		
	}
	
	public Double doSomeLongComputation() {
		Random random = new Random();
		int delay = 500 + random.nextInt(2000);
		return new Double(delay);
	}
}
