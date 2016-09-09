package com.malsolo.java8.completable.pipeline;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShopTest {
	
	private final static String SHOP_NAME = "SHOP";
	
	private final Shop shop = new Shop(SHOP_NAME);
	
	@Test
	public void testGetPrice() {
		String product = "PRODUCT";
		String price = shop.getPrice(product);
		System.out.printf("Price for product %s = %s%n", product, price);
		assertNotNull(price);
		assertTrue(price.contains(SHOP_NAME + ":"));
		assertEquals(0, price.indexOf((SHOP_NAME + ":")));
	}

}
