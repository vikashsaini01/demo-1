package com.example.ws.service.tss;

import java.awt.MediaTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
	
	@Autowired
	QuoteService quoteService;
	
	@RequestMapping(value="/ws/quote/daily", produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public ResponseEntity<Quote> getDailyQuote(@RequestParam(required=false) String category){
		Quote quote = quoteService.getDaily(category);
		if(quote==null)
			return new ResponseEntity<Quote>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Quote>(quote,HttpStatus.OK);
		
	}
}
