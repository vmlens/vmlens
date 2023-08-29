package com.anarsoft.trace.agent.runtime.filter;

public class HasGeneratedMethodsAlwaysFalse implements  HasGeneratedMethods {

	public boolean hasGeneratedMethods(String owner) {
	
		return false;
	}

}
