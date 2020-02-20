package com.google.fantasticgeneration.hashcode_2020.logic;

import com.google.fantasticgeneration.hashcode_2020.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    private static PickNextLibrary LIBRARY_PICKER = new PickBasedOnScore();

    public SolutionContainer solve(ProblemContainer problem) {
        Status status = problem.status;

        for (int currentTime = 0; currentTime < status.getMaxDays(); ) {

            Library nextLibrary = LIBRARY_PICKER.pickNext(status, currentTime);

            if (nextLibrary == null) {
                break; // SKIP: no more libraries
            }

            nextLibrary.setSignupDay(currentTime);
            List<Book> deliveredBooks = nextLibrary.deliver(status.getDeliveredBooks(), currentTime, status.getMaxDays());

            nextLibrary.setDeliveredBooks(deliveredBooks);
            status.addDeliveredBooks(deliveredBooks);

            currentTime += nextLibrary.getSignupTime();
        }

        int score = calculateScore(status.getLibraries());

        SolutionContainer solutionContainer = new SolutionContainer(status.getLibraries(), score);
        return solutionContainer;
    }

    private int calculateScore(List<Library> libraries) {
        int score = 0;

        for (Library library : libraries) {
            for (Book book : library.getBooks()) {
                score += book.getScore();
            }
        }

        return score;
    }

}
