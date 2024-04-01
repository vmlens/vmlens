package com.vmlens.trace.agent.bootstrap.callback.state;


public class
StateHolder {

    // Fixme
    public static final long offset = 0;

    static {

        //null; //offset = UpdateObjectState.getFieldOffset(StateHolder.class)	;


    }

    private volatile Object _pAnarsoft_;
	
	
	public static StateAccess createAccess() {
		return new StateAccess( new StateHolder(), offset );
	}
	
}
