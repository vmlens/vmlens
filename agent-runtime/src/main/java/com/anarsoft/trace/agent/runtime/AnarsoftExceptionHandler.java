package com.anarsoft.trace.agent.runtime;

public class AnarsoftExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		e.printStackTrace();
		
	}

}
