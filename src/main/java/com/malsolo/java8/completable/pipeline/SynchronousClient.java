package com.malsolo.java8.completable.pipeline;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.malsolo.java8.completable.nonblocking.Client;

public class SynchronousClient implements Client {

	private final List<Shop> shops;
	
	public SynchronousClient(List<Shop> shops) {
		this.shops = shops;
	}
	
	@Override
	public List<String> findPrices(String product) {
		return shops.stream()
			.map(shop -> shop.getPrice(product))
			.map(Quote::parse)
			.map(Discount::applyDiscount)
			.collect(toList());
	}

}
