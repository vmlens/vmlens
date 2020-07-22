package com.anarsoft.trace.agent.runtime;

public class VmlensUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
		System.err.println( t.getName());
		System.err.println( e.getMessage());
		System.err.println( e.getCause());
		e.printStackTrace();

		
		
	}

}
