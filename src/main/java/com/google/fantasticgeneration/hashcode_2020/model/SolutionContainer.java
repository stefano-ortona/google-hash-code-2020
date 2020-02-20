package com.google.fantasticgeneration.hashcode_2020.model;

import java.util.Collections;
import java.util.List;

public class SolutionContainer {

	public List<Library> libraries;

	public SolutionContainer(List<Library> libraries, int score) {
		super();
		this.libraries = libraries;
		this.score = score;
	}

	private int score;

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public List<Library> getLibraries() {
		return this.libraries;
	}
	
	public void setLibraries(List<Library> libs) {
		this.libraries = libs;
	}

	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		int signed_ups = 0;
		final List<Library> libs = getLibraries();
		Collections.sort(libs);
		
		for (final Library lib : libs) {
			if(lib.getSignupDay() != -1) {
				signed_ups ++ ;
			}
		}
		string.append(signed_ups + "\n");
		for (final Library lib : libs) {
			if(lib.getSignupDay() != -1) {
				string.append(lib.id + " ");
			    int size = lib.getDeliveredBooks().size();
				string.append(size + "\n");
				int i = 0;
				for(final Book book: lib.getDeliveredBooks()) {
					i++;
					string.append(book.getId());
					if(i != size) {
						string.append(" ");
					}
				}
				string.append("\n");
			}
		}
        return string.toString();
	}

}
