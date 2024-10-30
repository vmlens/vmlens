package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

public class VolatileArrayAccessCallback {

	private static final AnarsoftWeakHashMap<VolatileArrayIdAndOrder> objectToOrder = new AnarsoftWeakHashMap<VolatileArrayIdAndOrder>();
	private static final Object LOCK = new Object();
	
	
	public static void access(long index, Object obj, int methodId , int operation)
	{
	/*	CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		//callbackStatePerThread.monitorCount++;
		int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

		if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

			
			int order = 0;
			long id = -1;
			
			synchronized(LOCK)
			{
				VolatileArrayIdAndOrder monitorIdAndOrder= objectToOrder.get(obj);
				
				if( monitorIdAndOrder == null )
				{
					 monitorIdAndOrder = new VolatileArrayIdAndOrder();
					 objectToOrder.put( obj , monitorIdAndOrder );
				}
				
				order = monitorIdAndOrder.order;
				id = monitorIdAndOrder.id;


                monitorIdAndOrder.order++;

            }

            callbackStatePerThread.programCount++;


            int currentProgramCounter = callbackStatePerThread.programCount;

            // Fixme Callback
            //	callbackStatePerThread.sendEvent.writeVolatileArrayAccessEventGen(slidingWindowId, currentProgramCounter, order, index, callbackStatePerThread.methodCount, methodId, operation, id);


            callbackStatePerThread.programCount++;


        }
		
	*/
		
	}
	
	
	public static void before_access(long index, Object obj, int methodId , int operation)
	{
	/*	CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		//callbackStatePerThread.monitorCount++;
		int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

		if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

			
			int order = 0;
			long id = -1;
			
			synchronized(LOCK)
			{
				VolatileArrayIdAndOrder monitorIdAndOrder= objectToOrder.get(obj);
				
				if( monitorIdAndOrder == null )
				{
					 monitorIdAndOrder = new VolatileArrayIdAndOrder();
					 objectToOrder.put( obj , monitorIdAndOrder );
				}
				
				order = monitorIdAndOrder.order;
				id = monitorIdAndOrder.id;


                monitorIdAndOrder.order++;

            }

            callbackStatePerThread.programCount++;


            int currentProgramCounter = callbackStatePerThread.programCount;

            // Fixme Callback
            //	callbackStatePerThread.sendEvent.writeWithoutInterleaveVolatileArrayAccessEventGen(slidingWindowId, currentProgramCounter, order, index, callbackStatePerThread.methodCount, methodId, operation, id);


            callbackStatePerThread.programCount++;


        }
		
		
		*/
	}
	
	
	
	
	
	
}
