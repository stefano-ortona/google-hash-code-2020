package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickBasedOnScore implements PickNextLibrary {

	@Override
	public Library pickNext(Status status, int curTime) {
		final List<Library> available = status.getLibraries().stream().filter(l -> l.getSignupDay() == -1)
				.collect(Collectors.toList());
		int bestScore = -1;
		Library bestLibrary = null;
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore > bestScore) {
				bestScore = curScore;
				bestLibrary = one;
			} else if (curScore == bestScore) {
				if (one.getSignupTime() < bestLibrary.getSignupTime()) {
					bestLibrary = one;
				}
			}
		}
		return bestLibrary;
	}

	private int computeScore(Library l, Set<Book> delivered, int curTime, int maxTime) {
		final List<Book> possibleDelvier = l.deliver(delivered, curTime, maxTime);
		int tot = 0;
		for (final Book oneB : possibleDelvier) {
			tot += oneB.getScore();
		}
		return tot;
	}

}
