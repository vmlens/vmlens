package com.anarsoft.trace.agent.runtime.filter;

public class FilterStateDefaultValue implements FilterState {

	private boolean defaultValue;
	
	
	
	
	public FilterStateDefaultValue(boolean defaultValue) {
		super();
		this.defaultValue = defaultValue;
	}




	public boolean take(String name)
	{
		return defaultValue;
	}
	
	
	public boolean isDefined()
	{
		return false;
	}
}
