package com.vmlens.trace.agent.bootstrap.callback.getstate;


public class CreateGetState implements FunctionForJDK7 {

	@Override
	public Object apply(Object key) {
		try {
			return GetStateFromObject.create((Class)key);
		} catch (Exception e) {
			return GetStateFromMap.create();
		}
	}

}
