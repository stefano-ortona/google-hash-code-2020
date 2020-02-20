package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickBasedOnScoreWithDelta extends PickNextLibrary {

    final Logger LOG = LoggerFactory.getLogger(getClass());

    public static int DELTA_SCORE = 5;

    public PickBasedOnScoreWithDelta() {
    }

    public PickBasedOnScoreWithDelta(List<Library> libraries, Status status) {
		double average = libraries.stream()
				.map(new Function<Library, Integer>() {
					@Override
					public Integer apply(Library library) {
						return computeScore(library, new HashSet<>(), 0, status.getMaxDays());
					}
				})
				.mapToInt(Integer::intValue)
				.average()
				.getAsDouble();

		DELTA_SCORE = (int) (average / 10);
    }

    @Override
    public Library pickNext(Status status, int curTime) {
        final List<Library> available = status.getLibraries().stream().filter(l -> l.getSignupDay() == -1)
                .collect(Collectors.toList());
        int bestScore = -1;
        Library bestLibrary = null;

        for (final Library one : available) {
            final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
            if (curScore > 0) {
                if (isBetter(curScore, bestScore, one, bestLibrary)) {
                    bestLibrary = one;
                    bestScore = curScore;
                }
            }
        }
        LOG.info("At time '{}' we picked library '{}' with score '{}'", curTime, bestLibrary, bestScore);
        return bestLibrary;
    }

    private boolean isBetter(int curScore, int bestScore, Library l1, Library l2) {
        if (bestScore == -1) {
            return true;
        }
        if ((curScore > bestScore) && ((curScore - bestScore) > DELTA_SCORE)) {
            return true;
        }
        if ((curScore < bestScore) && ((bestScore - curScore) > DELTA_SCORE)) {
            return false;
        }
        // pick based on singnup tim
        if (l1.getSignupTime() < l2.getSignupTime()) {
            return true;
        }
        return false;
    }

}
