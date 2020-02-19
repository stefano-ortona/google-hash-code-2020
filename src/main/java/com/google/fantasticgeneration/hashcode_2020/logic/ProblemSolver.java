package com.google.fantasticgeneration.hashcode_2020.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.SolutionContainer;

public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	public SolutionContainer solve(ProblemContainer problem) {
		// TODO
		final SolutionContainer solution = new SolutionContainer();
		// set the score
		solution.setScore(0);
		return solution;
	}

	public static void main(String[] args) {
		LOG.info("Hello World!");
	}

}
