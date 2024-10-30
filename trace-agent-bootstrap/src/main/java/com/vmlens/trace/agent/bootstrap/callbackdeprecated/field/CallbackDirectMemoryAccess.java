package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class CallbackDirectMemoryAccess {


    public static void volatile_access(long address, ThreadLocalForParallelize callbackStatePerThread, int operation, int slidingWindowId) {

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
