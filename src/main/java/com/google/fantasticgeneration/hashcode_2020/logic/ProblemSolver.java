package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.List;
import java.util.Set;

import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.SolutionContainer;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class ProblemSolver {

	private PickNextLibrary LIBRARY_PICKER = null;

	public ProblemSolver(PickNextLibrary strategy) {
		this.LIBRARY_PICKER = strategy;
	}

	public ProblemSolver() {
		// by default use default weighted sum strategy
		this.LIBRARY_PICKER = new PickLibraryWeightedSum();
	}

	public SolutionContainer solve(ProblemContainer problem) {
		final Status status = problem.status;

		for (int currentTime = 0; currentTime < status.getMaxDays();) {

			final Library nextLibrary = LIBRARY_PICKER.pickNext(status, currentTime);

			if (nextLibrary == null) {
				break; // SKIP: no more libraries
			}

			nextLibrary.setSignupDay(currentTime);
			final List<Book> deliveredBooks = nextLibrary.deliver(status.getDeliveredBooks(), currentTime,
					status.getMaxDays());

			nextLibrary.setDeliveredBooks(deliveredBooks);
			status.addDeliveredBooks(deliveredBooks);

			currentTime += nextLibrary.getSignupTime();
		}

		final int score = calculateScore(status.getDeliveredBooks());

		final SolutionContainer solutionContainer = new SolutionContainer(status.getLibraries(), score);
		return solutionContainer;
	}

	/*
	 * Internal methods
	 */

	private int calculateScore(Set<Book> books) {
		int score = 0;

		for (final Book book : books) {
			score += book.getScore();
		}

		return score;
	}

}
