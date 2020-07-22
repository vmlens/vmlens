package com.anarsoft.trace.agent.runtime;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		e.printStackTrace();
		
	}

}
