package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickBasedOnCannotComplete extends PickBasedOnScore {

	@Override
	public Library pickNext(Status status, int curTime) {
		final List<Library> all = status.getLibraries().stream().filter(l -> (l.getSignupDay() == -1))
				.collect(Collectors.toList());
		final List<Library> cannotComplete = status.getLibraries().stream()
				.filter(l -> (l.getSignupDay() == -1)
						&& !canComplete(l, curTime, status.getMaxDays(), status.getDeliveredBooks()))
				.collect(Collectors.toList());

		if (cannotComplete.isEmpty()) {
			return pickBestForScore(all, status, curTime);
		}
		final List<Library> canComplete = status.getLibraries().stream().filter(l -> (l.getSignupDay() == -1))
				.filter(l -> (l.getSignupDay() == -1)
						&& canComplete(l, curTime, status.getMaxDays(), status.getDeliveredBooks()))
				.collect(Collectors.toList());
		if (canComplete.size() < cannotComplete.size()) {
			System.out.println("time");
			return pickBestForTime(cannotComplete, status, curTime);
		}
		return pickBestForScore(all, status, curTime);

	}

	private Library pickBestForScore(List<Library> available, Status status, int curTime) {
		int bestScore = 0;
		Library bestLibrary = null;
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore == 0) {
				continue;
			}
			if (curScore > bestScore) {
				bestScore = curScore;
				bestLibrary = one;
			} else if ((curScore > 0) && (curScore == bestScore)) {
				if (one.getSignupTime() < bestLibrary.getSignupTime()) {
					bestLibrary = one;
				}
			}
		}
		return bestLibrary;
	}

	private Library pickBestForTime(List<Library> available, Status status, int curTime) {
		int bestTime = Integer.MAX_VALUE;
		Library bestLibrary = null;
		int bestScore = -1;
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore == 0) {
				continue;
			}
			if (one.getSignupTime() < bestTime) {
				bestTime = one.getSignupTime();
				bestLibrary = one;
				bestScore = curScore;
			} else if (one.getSignupTime() == bestTime) {
				if ((curScore > 0) && (curScore > bestScore)) {
					bestLibrary = one;
					bestScore = curScore;
				}
			}
		}
		return bestLibrary;
	}

	private boolean canComplete(Library l, int curTime, int maxTime, Set<Book> alreadyDelivered) {
		final List<Book> delivered = l.deliver(alreadyDelivered, curTime, maxTime);
		final List<Book> allRemainings = new ArrayList<>();
		allRemainings.addAll(l.getBooks());
		allRemainings.removeAll(alreadyDelivered);
		return delivered == allRemainings;
	}

}
