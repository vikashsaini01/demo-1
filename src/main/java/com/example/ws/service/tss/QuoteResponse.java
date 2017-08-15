package com.example.ws.service.tss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuoteResponse {
	
	QuoteResponseSuccess success;
	QuoteContents contents;
	
	public QuoteResponseSuccess getSuccess() {
		return success;
	}
	public void setSuccess(QuoteResponseSuccess success) {
		this.success = success;
	}
	public QuoteContents getContents() {
		return contents;
	}
	public void setContents(QuoteContents contents) {
		this.contents = contents;
	}
	
	
}
