package com.malsolo.java8.completable.pipeline;

import java.util.Arrays;
import java.util.List;

import com.malsolo.java8.completable.nonblocking.Client;

public class Main {

	public static void main(String[] args) {
		List<Shop> shops = Arrays.asList(
				new Shop("BestPrice"), 
				new Shop("LetsSaveBig"), 
				new Shop("MyFavoriteShop"),
				new Shop("Amazon1"),
				new Shop("BuyItAll")
				);
		
		Main main = new Main();
		System.out.println(">>>>> AVAILABLE PROCESSORS: " +Runtime.getRuntime().availableProcessors());
		System.out.println("***** SYNCHRONOUS *****");
		Client client = new SynchronousClient(shops);
		main.performClientCall(client);
		System.out.println("***** COMPLETABLE FUTURE *****");
		client = new CompletableFutureClient(shops);
		main.performClientCall(client);
	}
	
	public void performClientCall(Client client) {
		long start = System.nanoTime();
		System.out.println(client.findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}

}
