package com.vmlens.trace.agent.bootstrap.callback.getState;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;

public class CreateGetState implements FunctionForJDK7 {

	

	@Override
	public Object apply(Object key) {

		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		try {
			return GetStateFromObject.create((Class)key);
		} catch (Exception e) {

		//  System.out.println( ((Class) key).getName() );
			
			
			return GetStateFromMap.create();

		} finally {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}

	}

}
