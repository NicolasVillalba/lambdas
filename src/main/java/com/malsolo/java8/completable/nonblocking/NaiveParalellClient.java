package com.malsolo.java8.completable.nonblocking;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class NaiveParalellClient extends AbstractClient {

	@Override
	public List<String> findPrices(String product) {
		return shops.parallelStream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.collect(toList());
	}

}
