package com.example.ws.service.tss;

import java.util.UUID;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteServiceBean implements QuoteService{
	
	private final RestTemplate restTemplate;
	
	public QuoteServiceBean(RestTemplateBuilder builder){
		this.restTemplate= builder.build();
	}
	
	@Override
	public Quote getDaily(String category){
		if(category==null || category.isEmpty())
			category=QuoteService.CATEGORY_INSPIRATIONAL;
		QuoteResponse quoteResponse=
				this.restTemplate.getForObject("http://quotes.rest/qod.json?category={cat}", 
						QuoteResponse.class, category);
		
		//UUID.randomUUID();
		
		return quoteResponse.getContents().getQuotes()[0];		
	}
}
