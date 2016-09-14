package com.malsolo.java8.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

	public final static int CHARACTERS_LIMIT_TO_SPLIT = 10;

	private final String string;
	private int currentChar = 0;

	public WordCounterSpliterator(String string) {
		this.string = string;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(string.charAt(currentChar++));
		return currentChar < string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length() - currentChar;

		if (currentSize < CHARACTERS_LIMIT_TO_SPLIT) {
			return null;
		}

		for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
			if (Character.isWhitespace(string.charAt(splitPos))) {
				Spliterator<Character> spliterator = new WordCounterSpliterator(
						string.substring(currentChar, splitPos));
				currentChar = splitPos;
				return spliterator;
			}

		}

		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
}
