package com.example.ws.service.tss;

public interface QuoteService {

	String CATEGORY_INSPIRATIONAL  ="inspire";
	public Quote getDaily(String category);
}
