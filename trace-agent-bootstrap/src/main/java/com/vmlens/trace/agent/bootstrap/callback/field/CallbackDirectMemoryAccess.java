package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.HashMapForDirectMemoryAccess;
import com.vmlens.trace.agent.bootstrap.callback.HashMapForDirectMemoryAccess.ElementFactory;
import com.vmlens.trace.agent.bootstrap.callback.state.ObjectIdAndOrder;

public class CallbackDirectMemoryAccess {
	
	
	private static final HashMapForDirectMemoryAccess<ObjectIdAndOrder> volatileDirectAccess = new HashMapForDirectMemoryAccess<ObjectIdAndOrder>(new ElementFactory<ObjectIdAndOrder>()
			{

				@Override
				public ObjectIdAndOrder create() {
					return new ObjectIdAndOrder();
				}
		
		
		
			});


    public static void volatile_access(long address, CallbackStatePerThreadForParallelize callbackStatePerThread, int operation, int slidingWindowId) {
        ObjectIdAndOrder current = volatileDirectAccess.getOrCreate(address);

        synchronized (current) {
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
	
	

}
