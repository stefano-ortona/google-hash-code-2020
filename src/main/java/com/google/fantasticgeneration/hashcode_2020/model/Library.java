package com.google.fantasticgeneration.hashcode_2020.model;

import java.util.List;

public class Library {
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

}
