package com.malsolo.java8.spliterator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterFunctional implements WordCounter {

	@Override
	public int countWords(String s) {
		
		Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
		
		WordCounterTuple wordCounterTuple = stream.reduce(new WordCounterTuple(0, true), WordCounterTuple::accumulate, WordCounterTuple::combine);
		
		return wordCounterTuple.getCounter();
	}

}
