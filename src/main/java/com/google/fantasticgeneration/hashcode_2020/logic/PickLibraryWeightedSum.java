package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickLibraryWeightedSum extends PickNextLibrary {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final double percentageScore;
	private final double percentageTime;

	public PickLibraryWeightedSum() {
		// by default use 0.8 for time and 0.2 for score
		this.percentageScore = 0.2;
		this.percentageTime = 0.8;
	}

	public PickLibraryWeightedSum(double percentageTime, double percentageScore) {
		this.percentageScore = percentageScore;
		this.percentageTime = percentageTime;
	}

	@Override
	public Library pickNext(Status status, int curTime) {
		final List<Library> available = status.getLibraries().stream().filter(l -> l.getSignupDay() == -1)
				.collect(Collectors.toList());
		double bestScore = -1;
		Library bestLibrary = null;
		final int bestPossibleScore = available.stream()
				.map(l -> computeScore(l, status.getDeliveredBooks(), curTime, status.getMaxDays()))
				.max((t1, t2) -> t1 - t2).get();
		final int bestPossibleTime = available.stream().map(l -> l.getSignupTime()).max((t1, t2) -> t1 - t2).get();
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore == 0) {
				continue;
			}
			final double weightedScore = weightedScore(curScore, bestPossibleScore, one.getSignupTime(),
					bestPossibleTime);
			if (weightedScore > bestScore) {
				bestScore = weightedScore;
				bestLibrary = one;
			} else if (weightedScore == bestScore) {
				// return the one shipping the books in longest time, should come first
				if (one.getCompleteTime() > bestLibrary.getCompleteTime()) {
					bestLibrary = one;
				}
			}
		}
		final String bestLibraryId = bestLibrary == null ? "null" : bestLibrary.getId() + "";
		LOG.info("At time '{}' picked best library '{}' with score '{}'", curTime, bestLibraryId, bestScore);
		return bestLibrary;
	}

	private double weightedScore(double curScore, double maxScore, double curTime, double maxTime) {
		return (this.percentageScore * (curScore / maxScore)) + (this.percentageTime * (1 - (curTime / maxTime)));
	}

}
