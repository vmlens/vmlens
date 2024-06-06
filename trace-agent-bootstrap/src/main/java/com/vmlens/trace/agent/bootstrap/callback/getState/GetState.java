package com.vmlens.trace.agent.bootstrap.callback.getstate;


import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;

public interface GetState {

	StateAccess getState(Object in);
	void resetState(Object in);
	
	
}
