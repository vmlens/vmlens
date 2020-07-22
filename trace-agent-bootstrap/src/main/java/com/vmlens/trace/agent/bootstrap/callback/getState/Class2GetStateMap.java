package com.vmlens.trace.agent.bootstrap.callback.getState;


import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;

public class Class2GetStateMap {

	private static ConcurrentHashMapComputeIfAbsentOnly class2GetState = new ConcurrentHashMapComputeIfAbsentOnly(5000);

	private static final CreateGetState createGetState = new CreateGetState();
	
	
	// public static StateAccess getStateFromMap(Object in)
	// {
	// return GetStateFromMap.create().getState(in);
	// }

	public static void resetState(Object in) {
		try {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

			Class cl = in.getClass();

			GetState getState = (GetState) class2GetState.computeIfAbsent(cl,createGetState);

			getState.resetState(in);
		} finally {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}
	}

	public static StateAccess getState(Object in) {

		try {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

			Class cl = in.getClass();
			GetState getState = (GetState) class2GetState.computeIfAbsent(cl,createGetState);

			return getState.getState(in);
		} finally {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}
	}

}
