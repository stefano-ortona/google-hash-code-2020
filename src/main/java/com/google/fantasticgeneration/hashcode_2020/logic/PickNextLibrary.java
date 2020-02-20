package com.google.fantasticgeneration.hashcode_2020.logic;

import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

import javax.annotation.Nullable;

public interface PickNextLibrary {

	@Nullable
	public Library pickNext(Status status, int curTime);

}
