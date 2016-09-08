package com.malsolo.java8.completable.nonblocking;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractClient implements Client {

	protected List<Shop> shops = Arrays.asList(
			new Shop("BestPrice"), 
			new Shop("LetsSaveBig"), 
			new Shop("MyFavoriteShop"),
			new Shop("Amazon1"),
//			new Shop("Amazon2"),
//			new Shop("Amazon3"),
//			new Shop("Amazon4"),
//			new Shop("Amazon5"),
			new Shop("BuyItAll")
			);


}
