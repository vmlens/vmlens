package com.vmlens.trace.agent.bootstrap.callback.getstate;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;
import com.vmlens.trace.agent.bootstrap.callback.state.StateHolder;

public class GetStateFromMap implements GetState  {
	
	private  final AnarsoftWeakHashMap<StateAccess> objectToPreviousThreadState = new AnarsoftWeakHashMap<StateAccess>();
	
	private static final GetStateFromMap singelton = new GetStateFromMap();
	
	
	 public synchronized StateAccess getState(Object in)
	 {
		 StateAccess state =  objectToPreviousThreadState.get(in);
		 
		 if( state == null )
		 {
			 state =  StateHolder.createAccess();
			 objectToPreviousThreadState.put(in, state);
		 }
			
		 return state;
	 }
	 
	 
	
	public static GetStateFromMap create()
	{
		return singelton;
	}
	
	public  void resetState(Object in)
	{
		// Nothing todo
	}
	

}
