package com.documents.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
	public String title;
	public String first;
	public String last;
	
	public Name() {
		super();
	}
	public Name(String title, String first, String last) {
		super();
		this.title = title;
		this.first = first;
		this.last = last;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	@Override
	public String toString() {
		return title + " " + first + " " + last;
	}
	
}
