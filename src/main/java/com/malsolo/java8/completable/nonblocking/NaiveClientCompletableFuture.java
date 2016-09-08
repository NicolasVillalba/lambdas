package com.malsolo.java8.completable.nonblocking;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class NaiveClientCompletableFuture extends AbstractClient {

	@Override
	public List<String> findPrices(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> shop.getName() + " price is " + shop.getPrice(product)))
				.collect(Collectors.toList());

		return priceFutures.stream().map(CompletableFuture::join).collect(toList());
	}

}
