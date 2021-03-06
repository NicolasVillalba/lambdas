package com.malsolo.java8.spliterator;

public class Main {
	
    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
            "mi  ritrovai in una  selva oscura" +
            " che la  dritta via era   smarrita ";
	
	public static void main(String[] args) {
		WordCounter wordCounter = new WordCounterIterative();
		System.out.printf("Found %d words [ITERATIVE]%n", wordCounter.countWords(SENTENCE));
		
		wordCounter = new WordCounterFunctional();
		System.out.printf("Found %d words [FUNCTIONAL]%n", wordCounter.countWords(SENTENCE));
		
		wordCounter = new WordCounterFunctionalParalell();
		System.out.printf("Found %d words [FUNCTIONAL PARALELL]%n", wordCounter.countWords(SENTENCE));

		wordCounter = new WordCounterWithSpliterator();
		System.out.printf("Found %d words [SPLITERATOR]%n", wordCounter.countWords(SENTENCE));
	}

}
