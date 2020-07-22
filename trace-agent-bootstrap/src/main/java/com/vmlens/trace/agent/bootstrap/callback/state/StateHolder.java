package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public class StateHolder {

	private volatile Object _pAnarsoft_;
	
	public static final long offset;
	
	
	static {
		
		offset = UpdateObjectState.getFieldOffset(StateHolder.class)	;
		
		
		
	}
	
	
	public static StateAccess createAccess() {
		return new StateAccess( new StateHolder(), offset );
	}
	
}
