package com.google.fantasticgeneration.hashcode_2020.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Status {

	private final Set<Book> deliveredBooks;
	private List<Library> libraries;
	int maxDays;

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public Status(List<Library> libraries, int maxDays) {
		this.libraries = libraries;
		this.deliveredBooks = new HashSet<>();
		this.maxDays = maxDays;
	}

	public Set<Book> getDeliveredBooks() {
		return deliveredBooks;
	}

	public void addDeliveredBook(Book book) {
		this.deliveredBooks.add(book);
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}

	@Override
	public String toString() {
		return "Status{" +
				"deliveredBooks=" + deliveredBooks +
				", libraries=" + libraries +
				", maxDays=" + maxDays +
				'}';
	}
}
