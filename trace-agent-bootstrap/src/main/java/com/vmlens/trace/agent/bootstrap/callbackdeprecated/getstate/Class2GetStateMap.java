package com.vmlens.trace.agent.bootstrap.callbackdeprecated.getstate;


import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.StateAccess;

public class Class2GetStateMap {

	private static ConcurrentHashMapComputeIfAbsentOnly class2GetState = new ConcurrentHashMapComputeIfAbsentOnly(5000);

	private static final CreateGetState createGetState = new CreateGetState();
	
	
	// public static StateAccess getStateFromMap(Object in)
	// {
	// return GetStateFromMap.create().getState(in);
	// }

	public static void resetState(Object in) {

	}

	public static StateAccess getState(Object in) {

		return null;
	}

}
