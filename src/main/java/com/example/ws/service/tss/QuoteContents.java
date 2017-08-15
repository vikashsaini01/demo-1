package com.example.ws.service.tss;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteContents {

	Quote[] quotes;

	public Quote[] getQuotes() {
		return quotes;
	}

	public void setQuotes(Quote[] quotes) {
		this.quotes = quotes;
	}

}