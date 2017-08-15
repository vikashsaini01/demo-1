package com.example.ws.service;

import java.util.concurrent.Future;

import com.example.entity.Task;

public interface EmailService {
	
	Boolean send(Task task);
	
	void sendASync(Task task);
	
	Future<Boolean> sendAsyncWithResult(Task task);
}
