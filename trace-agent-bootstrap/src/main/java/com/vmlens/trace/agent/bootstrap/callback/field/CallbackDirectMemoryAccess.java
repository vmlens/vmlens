package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;

public class CallbackDirectMemoryAccess {
	
	

    public static void volatile_access(long address, CallbackStatePerThreadForParallelize callbackStatePerThread, int operation, int slidingWindowId) {

			/*
			Fixme Callback

			int order = current.order;
	      current.order += 1;
	      
	      callbackStatePerThread.programCount += 1;
		  
	      callbackStatePerThread.sendEvent.writeVolatileDirectMemoryEventGen
	    		(       slidingWindowId
	    		, callbackStatePerThread.programCount
	    		, callbackStatePerThread.methodCount
	    		, current.getId()
	    		,       operation , order
	    		);
	      callbackStatePerThread.programCount += 1;
	      */


    }
	
	

}
