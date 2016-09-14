package com.malsolo.java8.spliterator;

public class WordCounterIterative implements WordCounter {

	@Override
	public int countWords(String s) {
		int counter = 0;
		boolean lastSpace = true;

		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace)
					counter++;
				lastSpace = false;
			}
		}

		return counter;
	}

}
