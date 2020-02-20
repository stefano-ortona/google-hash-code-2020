package com.google.fantasticgeneration.hashcode_2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.io.ProblemReader;
import com.google.fantasticgeneration.hashcode_2020.io.ProblemWriter;
import com.google.fantasticgeneration.hashcode_2020.logic.ProblemSolver;
import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.SolutionContainer;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

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

		final Book book0 = new Book(0, 1);
		final Book book1 = new Book(1, 2);
		final Book book2 = new Book(2, 3);
		final Book book3 = new Book(3, 6);
		final Book book4 = new Book(4, 5);
		final Book book5 = new Book(5, 4);

		final List<Book> lib0bks = Arrays.asList(book0, book1, book2, book3, book4);
		final Library lib0 = new Library(0, lib0bks, 2, 2);

		final List<Book> lib1bks = Arrays.asList(book3, book2, book5, book0);
		final Library lib1 = new Library(1, lib1bks, 3, 1);

		final List<Library> libs = Arrays.asList(lib0, lib1);

		final Status status = new Status(libs, 7);

		final ProblemContainer problem_container = new ProblemContainer(status);
		final SolutionContainer solution = SOLVER.solve(problem_container);

		LOG.info(solution.toString());

		Assert.assertEquals(solution.getScore(), 16);
		Assert.assertEquals(solution.getLibraries().size(), 2);
		for (final Library lib : solution.getLibraries()) {
			if (lib.getId() == 0) {
				final List<Book> delivered_lib0 = Arrays.asList(book0, book1, book2, book3, book4);
				for (final Book book : delivered_lib0) {
					Assert.assertTrue(lib.getDeliveredBooks().contains(book));
				}
			}
			if (lib.getId() == 1) {
				final List<Book> delivered_lib1 = Arrays.asList(book5);
				for (final Book book : delivered_lib1) {
					Assert.assertTrue(lib.getDeliveredBooks().contains(book));
				}
			}
		}
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

	private final String[] testFiles = new String[] { "a_example.txt", "b_read_on.txt", "", "", "" };

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
