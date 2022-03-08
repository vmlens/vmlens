package com.vmlens.trace.agent.bootstrap.callback.obj;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;
import com.vmlens.trace.agent.bootstrap.callback.state.StateHolder;

public class ObjectCallbackState {

	 final int fieldId;
	private final AnarsoftWeakHashMap<StateAccess> object2State = new AnarsoftWeakHashMap<StateAccess>();
	private final Object LOCK = new Object();

	public ObjectCallbackState(int fieldId) {
		super();
		this.fieldId = fieldId;
	}

//	StateAccess getObjectState(Object obj)
//	{
//
//		StateAccess objectState = null;
//
//		synchronized (LOCK) {
//			objectState = object2State.get(obj);
//
//			if (objectState == null) {
//				objectState = StateHolder.createAccess();
//				object2State.put(obj, objectState);
//			}
//
//		
//		}
//		return objectState;
//			
//	}
	
	
	void access(Object obj, int operation, int methodId) {

		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		

		StateAccess objectState = null;

		synchronized (LOCK) {
			objectState = object2State.get(obj);

			if (objectState == null) {
				objectState =  StateHolder.createAccess();
				object2State.put(obj, objectState);
			}

		}
		/*
		 * (CallbackStatePerThread callbackStatePerThread, Object orig,
			Object c, int fieldId, int methodId, int operation)
		 */
		
//		if(callbackStatePerThread.isInInterleaveLoop())
//		{
//			callbackStatePerThread.stackTraceBasedDoNotTrace++;
//			
//			System.out.println(objectState.getId() + " "  + fieldId + " " + operation + " " + methodId + " " +  callbackStatePerThread.threadId);
//			
//			new Exception().printStackTrace();
//			callbackStatePerThread.stackTraceBasedDoNotTrace--;
//		}
		
//		
		/*
		 non_volatile_access_internal(CallbackStatePerThread callbackStatePerThread, Object orig, Object stateHolder,
			long offset, int fieldId, int methodId, int operation) 
		 */
		CallbackObject.non_volatile_access_internal(callbackStatePerThread, obj,objectState.obj , objectState.offset , fieldId, methodId , operation );
		
		
		
	

	}

	

}
