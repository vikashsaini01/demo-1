package com.example.ws.util;
public class RequestContext {
	private static ThreadLocal<String> userNames = new ThreadLocal<>();
	
	private RequestContext(){}
	
	public static String getUserName(){
		return userNames.get();
	}

	public static void setUserName(String userName){
		userNames.set(userName);
	}
	
	public static void init(){
		userNames.set(null);
	}
}
