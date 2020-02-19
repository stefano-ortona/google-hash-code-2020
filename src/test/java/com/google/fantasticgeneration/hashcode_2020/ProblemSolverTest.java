package com.google.fantasticgeneration.hashcode_2020;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.io.ProblemReader;
import com.google.fantasticgeneration.hashcode_2020.io.ProblemWriter;
import com.google.fantasticgeneration.hashcode_2020.logic.ProblemSolver;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.SolutionContainer;

/**
 *
 */
public class ProblemSolverTest {

	private final static ProblemSolver SOLVER = new ProblemSolver();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Test
	public void firstTest() {
		LOG.info("----------------------");
		LOG.info("First test is starting");
	}

	@Test
	public void secondTest() {
		LOG.info("----------------------");
		LOG.info("Second test is starting");
	}

	@Test
	public void thirdTest() {
		LOG.info("----------------------");
		LOG.info("Second test is starting");
	}

	private final String[] testFiles = new String[] { "", "", "", "", "" };

	@Test
	public void firstFile() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = testFiles[0];
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
		LOG.info("Final score for file '{}' is '{}'", file, sC.getScore());
	}

	@Test
	public void secondFile() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = testFiles[1];
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
		LOG.info("Final score for file '{}' is '{}'", file, sC.getScore());
	}

	@Test
	public void thirdFile() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = testFiles[2];
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
		LOG.info("Final score for file '{}' is '{}'", file, sC.getScore());
	}

	@Test
	public void fourthFile() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = testFiles[3];
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
		LOG.info("Final score for file '{}' is '{}'", file, sC.getScore());
	}

	@Test
	public void fifthFile() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = testFiles[4];
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
		LOG.info("Final score for file '{}' is '{}'", file, sC.getScore());
	}

}
