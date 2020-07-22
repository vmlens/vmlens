package com.vmlens.trace.agent.bootstrap;

public class OptionalShort {

	private final boolean hasShort;
	private final short theValue;
	public OptionalShort(boolean hasByte, short theValue) {
		super();
		this.hasShort = hasByte;
		this.theValue = theValue;
	}
	public boolean isHasShort() {
		return hasShort;
	}
	public short getTheValue() {
		return theValue;
	}
	
	
}
