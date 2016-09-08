package com.malsolo.java8.completable;

import java.util.concurrent.Future;
import java.util.function.Function;

public class Client {

	public static void main(String[] args) {
		Client client = new Client();
		Shop shop = new Shop("BestShop");
		System.out.println("***** GET PRICE *****");
		client.doStuff(shop::getPrice);
		System.out.println("***** GET PRICE ASYNC *****");
		client.doStuff(shop::getPriceAsync);
		System.out.println("***** END *****");
	}
	
	public void doStuff(Function<String, Future<Double>> function) {
		long start = System.nanoTime();
		Future<Double> futurePrice = function.apply("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		// Do some more tasks, like querying other shops
		doSomethingElse();
		// while the price of the product is being calculated
		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	private void doSomethingElse() {
		System.out.println("Doing something else");
	}

}
