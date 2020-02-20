package com.google.fantasticgeneration.hashcode_2020.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Library implements Comparable<Library> {
	int id;
	List<Book> books;
	int signupTime;
	int parallelBooks;

	int signupDay = -1;
	List<Book> deliveredBooks;

	public int getSignupDay() {
		return signupDay;
	}

	public void setSignupDay(int signupDay) {
		this.signupDay = signupDay;
	}

	public List<Book> getDeliveredBooks() {
		return deliveredBooks;
	}

	public void setDeliveredBooks(List<Book> deliveredBooks) {
		this.deliveredBooks = deliveredBooks;
	}

	public Library(int id, List<Book> books, int signupTime, int parallelBooks) {
		super();
		this.id = id;
		this.books = books;
		this.signupTime = signupTime;
		this.parallelBooks = parallelBooks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(int signupTime) {
		this.signupTime = signupTime;
	}

	public int getParallelBooks() {
		return parallelBooks;
	}

	public void setParallelBooks(int parallelBooks) {
		this.parallelBooks = parallelBooks;
	}

	public List<Book> deliver(Set<Book> alreadyDelivered, int curTime, int maxTime) {
		curTime += this.signupTime;
		final List<Book> available = new ArrayList<>();
		available.addAll(this.books);
		available.removeAll(alreadyDelivered);
		final List<Book> potentialDeliver = new LinkedList<>();
		int bookIndex = 0;
		for (int i = curTime; (i < maxTime) && (bookIndex < available.size()); i++) {
			for (int j = 0; (j < this.parallelBooks) && (bookIndex < available.size()); j++) {
				potentialDeliver.add(available.get(bookIndex++));
			}
		}
		return potentialDeliver;
	}

	@Override
	public String toString() {
		return "Library{" + "id=" + id + ", books=" + books + ", signupTime=" + signupTime + ", parallelBooks="
				+ parallelBooks + ", signupDay=" + signupDay + ", deliveredBooks=" + deliveredBooks + '}';
	}

	@Override
	public int compareTo(Library o) {
		if ((o.signupDay == -1) && (this.signupDay == -1)) {
			return this.id - o.id;
		}
		if ((o.signupDay != -1) && (this.signupDay != -1)) {
			return this.signupDay - o.signupDay;
		}
		return this.signupDay == -1 ? 1 : -1;
	}
}
