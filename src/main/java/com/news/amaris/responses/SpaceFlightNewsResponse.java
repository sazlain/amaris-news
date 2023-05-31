package com.news.amaris.responses;

import java.util.List;

import com.google.gson.Gson;
import com.news.amaris.dtos.SpaceFlightNewsDto;

public class SpaceFlightNewsResponse {

	private Integer count;
	private String  next;
	private String previous;
	private List<SpaceFlightNewsDto> results;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<SpaceFlightNewsDto> getResults() {
		return results;
	}

	public void setResults(List<SpaceFlightNewsDto> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
