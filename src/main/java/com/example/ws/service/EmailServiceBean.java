package com.example.ws.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.entity.Task;
@Service
public class EmailServiceBean implements EmailService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public Boolean send(Task task) {
		logger.info("> send");
		Boolean success = Boolean.FALSE;
		
		int wait = 5000;
		try{
			Thread.sleep(wait);
		}
		catch(Exception ex){}
		logger.info("Processing time was {} seconds.", wait/1000);
		success = Boolean.TRUE;
		logger.info("< send");
		return success;
	}

	@Override
	@Async
	public void sendASync(Task task) {
		logger.info("> sendAsync");
		send(task);
		logger.info("< sendAsync");
	}

	@Override
	@Async
	public Future<Boolean> sendAsyncWithResult(Task task) {
		logger.info("> sendAsyncWithResult");
		CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
		
		future.complete(send(task));
		logger.info("< sendAsyncWithResult");
		return future;
	}
}