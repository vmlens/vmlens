package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;


public class CallbackStatic {


    public static void volatile_access(int fieldId, int methodId, boolean isWrite) {
		ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		 volatile_access_internal(callbackStatePerThread, fieldId, methodId, isWrite);
	 }


	private static void volatile_access_internal(ThreadLocalForParallelize callbackStatePerThread, int fieldId, int methodId, boolean isWrite) {
/*		 int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

	    	if( !  CallbackState.isSlidingWindowTrace( slidingWindowId  ) )
			{
				return;
			}
		 
		 

		 
		  
		  StaticVolatileOrder current = null;
		  
		  synchronized (staticIdToOrder)
	      {
			  current = staticIdToOrder.get(fieldId);
			  
			  if( current == null )
			  {
				  current = new StaticVolatileOrder();
				  staticIdToOrder.put(fieldId , current);
				  
			  }		  
			  
	      }		  
			 
		  
		  synchronized(current)
		  {


              callbackStatePerThread.programCount += 1;
              int order = current.order;
              current.order = order + 1;

              callbackStatePerThread.programCount += 1;
// Fixme Callback
//	        callbackStatePerThread.sendEvent.writeVolatileAccessEventStaticGen( slidingWindowId ,
//	        		callbackStatePerThread.programCount, order, fieldId, callbackStatePerThread.methodCount, methodId, isWrite);


              callbackStatePerThread.programCount += 1;

          } */
	 }
	 
	 
	 
	 
	  public static void non_volatile_access( int fieldId, int methodId, boolean isWrite) 
	  {
		  ThreadLocalForParallelize callbackStatePerThread = (ThreadLocalForParallelize) CallbackState.callbackStatePerThread.get();
	    non_volatile_access_internal(callbackStatePerThread , fieldId, methodId,  isWrite) ;
	  }


	private static void non_volatile_access_internal(ThreadLocalForParallelize callbackStatePerThread, int fieldId, int methodId, boolean isWrite) {
	/*	   int slidingWindowId =  CallbackState.traceFields(callbackStatePerThread);
		    
		    if (!  CallbackState.isSlidingWindowTrace(slidingWindowId )) {
		      return;
		    }


          synchronized (staticField2ThreadId)
		    {
		    	
		      if( callbackStatePerThread.isInInterleaveLoop()  )
		      {
		    	  staticField2ThreadId.put(fieldId, -1L);
		    	  writeEvent(callbackStatePerThread, callbackStatePerThread.threadId, slidingWindowId , fieldId, methodId, isWrite, callbackStatePerThread.programCount, callbackStatePerThread.methodCount);
		      }
		      else	
		      if (!staticField2ThreadId.contains(fieldId))
		      {
		        staticField2ThreadId.put(fieldId, callbackStatePerThread.threadId);
		      }
		      else
		      {
		        long prevoiusThreadId = staticField2ThreadId.get(fieldId);
		        if (prevoiusThreadId == callbackStatePerThread.threadId)
		        {
		         return;
		        }
		        if (prevoiusThreadId > 0L)
		        {
		          staticField2ThreadId.put(fieldId, -1L);
		        }
		        writeEvent(callbackStatePerThread, callbackStatePerThread.threadId, slidingWindowId , fieldId, methodId, isWrite, callbackStatePerThread.programCount, callbackStatePerThread.methodCount);
		      }
		    } */
	  }


	private static void writeEvent(ThreadLocalForParallelize callbackStatePerThread, long threadId, int slidingWindowId, int fieldId, int methodId, boolean isWrite, int programCounter, int methodCounter) {
// Fixme Callback
//		  callbackStatePerThread.sendEvent.writeFieldAccessEventStaticGen(slidingWindowId , programCounter, fieldId, methodCounter,  MemoryAccessType.getOperation(isWrite), methodId , callbackStatePerThread.isStackTraceIncomplete() );


	}


	private static void access_mode_state(final ThreadLocalForParallelize callbackStatePerThread, final int fieldId, int methodId, boolean isWrite) {
	/*	 final int slidingWindowId =  CallbackState.traceFields(callbackStatePerThread);
		    
		    if (!  CallbackState.isSlidingWindowTrace(slidingWindowId )) {
		      return;
		    }
		    
		    int operation = MemoryAccessType.IS_READ;
			
			if( isWrite )
			{
				operation = MemoryAccessType.IS_WRITE;
			}
		    
		synchronized(staticId2State4ModeState)
		{
			final ModeStateStatic staticState4ModeState = staticId2State4ModeState.get(fieldId);
			
			if( staticState4ModeState == null)
			{
				ModeStateStatic n = new ModeStateStatic( callbackStatePerThread.threadId );
				n.access4StateSet.add(  new Access4State(callbackStatePerThread.methodCount , methodId ,  operation,slidingWindowId) );
				staticId2State4ModeState.put( fieldId , n );
				
				
				return;
				
			}
			
			
			if(staticState4ModeState.lastThreadId ==  callbackStatePerThread.threadId)
			{
				staticState4ModeState.access4StateSet.add(  new Access4State(callbackStatePerThread.methodCount , methodId ,  operation,slidingWindowId) );
				return;
			}
			
			if(staticState4ModeState.lastThreadId > 0)
			{
				staticState4ModeState.access4StateSet.forEach(  new TObjectProcedure<Access4State>(){
					  
					   public boolean execute(Access4State object)
					   {
							//callbackStatePerThread.sendEvent.writeStateEventStaticFieldInitialGen(slidingWindowId,staticState4ModeState.lastThreadId , fieldId, object.methodId, object.methodNumber, object.operation  ,object.slidingWindowId );
						   
						   return true;
					   }
					   
				   });
				 
				 
				staticState4ModeState.access4StateSet = null;
				   
				   
				   
				//   callbackStatePerThread.sendEvent.writeStateEventStaticFieldGen(slidingWindowId, fieldId, methodId , callbackStatePerThread.methodCount, operation );
				   
				   
				   
				   staticState4ModeState.lastThreadId = -10L;
				   return;
			}
			
			
			 //  callbackStatePerThread.sendEvent.writeStateEventStaticFieldGen(slidingWindowId, fieldId, methodId , callbackStatePerThread.methodCount, operation );
			
			
		}
		    
		*/
	}
	
	
	
	
	
}
