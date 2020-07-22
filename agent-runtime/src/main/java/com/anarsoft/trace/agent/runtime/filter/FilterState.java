package com.anarsoft.trace.agent.runtime.filter;

public interface FilterState {
	
	
	boolean take(String name);

	boolean isDefined();
	
}
