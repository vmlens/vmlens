package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.getstate.Class2GetStateMap;

public class CloneCallback {

	
	public static void resetState(Object in)
	{
		Class2GetStateMap.resetState(in);
	}
	
	
}
