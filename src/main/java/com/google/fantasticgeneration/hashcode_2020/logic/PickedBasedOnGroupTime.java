package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.List;
import java.util.stream.Collectors;

import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickedBasedOnGroupTime extends PickNextLibrary {

	@Override
	public Library pickNext(Status status, int curTime) {
		final List<Library> available = status.getLibraries().stream().filter(l -> l.getSignupDay() == -1)
				.collect(Collectors.toList());
		int bestTime = Integer.MAX_VALUE;
		Library bestLibrary = null;
		int bestScore = -1;
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore == 0) {
				continue;
			}
			if (one.getSignupTime() < (bestTime-10) && curScore > bestScore) {
				bestTime = one.getSignupTime();
				bestLibrary = one;
				bestScore = curScore;
			} else if (one.getSignupTime() == (bestTime-10)) {
				if ((curScore > 0) && (curScore > bestScore)) {
					bestLibrary = one;
					bestScore = curScore;
					bestTime = one.getSignupTime();
				}
			}
		}
		return bestLibrary;
	}

}
