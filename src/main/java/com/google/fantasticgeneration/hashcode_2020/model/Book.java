package com.google.fantasticgeneration.hashcode_2020.model;

public class Book implements Comparable<Book> {
	private int id;
	private int score;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Book other = (Book) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public Book(int id, int score) {
		super();
		this.id = id;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", score=" + score + '}';
	}

	@Override
	public int compareTo(Book o) {
		if (this.score == o.score) {
			return this.id - o.id;
		}
		return o.score - this.score;
	}
}
