package com.google.fantasticgeneration.hashcode_2020.logic;

import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class PickNextLibrary {

	@Nullable
	public abstract Library pickNext(Status status, int curTime);

	public int computeScore(Library l, Set<Book> delivered, int curTime, int maxTime) {
		final List<Book> possibleDelvier = l.deliver(delivered, curTime, maxTime);
		int tot = 0;
		for (final Book oneB : possibleDelvier) {
			tot += oneB.getScore();
		}
		return tot;
	}

}
