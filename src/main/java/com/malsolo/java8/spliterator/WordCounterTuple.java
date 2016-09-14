package com.malsolo.java8.spliterator;

public class WordCounterTuple {

	private final int counter;
	private final boolean lastSpace;

	public WordCounterTuple(int counter, boolean lastSpace) {
		super();
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	public int getCounter() {
		return counter;
	}

	/**
	 * The accumulate method traverses the Character s one by one as done by the iterative algorithm.
	 * @param c
	 * @return
	 */
	public WordCounterTuple accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return this.lastSpace ? this : new WordCounterTuple(counter, true);
		} else {
			// Increase the word counter when the last character is a space and the currently traversed one isn’t.
			return this.lastSpace ? new WordCounterTuple(counter + 1, false) : this;
		}
	}

	/**
	 * Combine two WordCounterTuples by summing their counters.
	 * @param wordCounterTuple
	 * @return
	 */
	public WordCounterTuple combine(WordCounterTuple wordCounterTuple) {
		// Use only the sum of the counters so you don’t care about lastSpace .
		return new WordCounterTuple(counter + wordCounterTuple.counter, wordCounterTuple.lastSpace);
	}

}
