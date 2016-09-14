package com.malsolo.java8.spliterator;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterWithSpliterator implements WordCounter {

	@Override
	public int countWords(String s) {
		Spliterator<Character> spliterator = new WordCounterSpliterator(s);
		Stream<Character> stream = StreamSupport.stream(spliterator, true);
		return stream.parallel().reduce(new WordCounterTuple(0, true), WordCounterTuple::accumulate, WordCounterTuple::combine).getCounter();
	}

}
