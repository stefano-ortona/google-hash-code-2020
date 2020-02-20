package com.google.fantasticgeneration.hashcode_2020.io;

import com.google.fantasticgeneration.hashcode_2020.UtilsFile;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class ProblemReader {

	public ProblemContainer readProblem(String fileLocation) {

		UtilsFile fr = new UtilsFile(fileLocation);
		Status status = fr.getStatus();
		final ProblemContainer pB = new ProblemContainer(status);
		return pB;
	}

}
