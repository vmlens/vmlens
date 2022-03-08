package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public class ObjectStateVolatile extends ObjectStateAbstractMultiThreaded {
	private  FieldId2Element<ObjectIdAndOrder> fieldId2Order;
	
	
	public ObjectStateVolatile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ObjectStateVolatile(int writeEventCount, int readEventCount, long id) {
		super(writeEventCount, readEventCount, id);
		// TODO Auto-generated constructor stub
	}


	public ObjectIdAndOrder getOrderForFieldId(int fieldId)
	{
		if(fieldId2Order == null)
		{
			return null;
		}
		else
		{
			return fieldId2Order.get(fieldId);
		}
		
		
	}
	
	
	public ObjectIdAndOrder createOrderToFieldId( int fieldId)
	{
		ObjectIdAndOrder objectIdAndOrder  = new ObjectIdAndOrder();
		
		if(fieldId2Order == null)
		{
			fieldId2Order = new FieldId2ElementListBased<ObjectIdAndOrder>(objectIdAndOrder,fieldId);
		}
		else
		{
			fieldId2Order = fieldId2Order.put(objectIdAndOrder,fieldId);
		}
		
		return objectIdAndOrder;
		
		
	}
	
	/*
	 * 	synchronized (state) {
			
			ParallizeFacade.beforeFieldAccessVolatile(callbackStatePerThread, state.getId(), fieldId, operation);

			callbackStatePerThread.programCount += 1;
			int order = 0;
			ObjectIdAndOrder current = state.getOrderForFieldId(fieldId);
			if (current == null) {
				current = state.createOrderToFieldId(fieldId);
				// current.id = ObjectIdAndOrder.getNewId();
			}
			order = current.order;
			current.order += 1;

			callbackStatePerThread.sendEvent.writeVolatileAccessEventGen(
					CallbackState.traceSyncStatements(callbackStatePerThread), callbackStatePerThread.programCount,
					order, fieldId, callbackStatePerThread.methodCount, methodId, operation, current.getId());

			callbackStatePerThread.programCount += 1;
		}
		
		
		 sendEventVolatile(CallbackStatePerThread callbackStatePerThread,int order,
			int fieldId, int methodId, int operation, long objectId) {
		
	 */
	
	
	 public  void sendVolatile( int fieldId,
				int methodId, int operation,UpdateObjectState updateObjectStateNew , CallbackStatePerThread callbackStatePerThread)
	  {
		  synchronized(this)
		  {
			 
				int order = 0;
				ObjectIdAndOrder current = getOrderForFieldId(fieldId);
				if (current == null) {
					current = createOrderToFieldId(fieldId);
					
				}
				order = current.order;
				current.order += 1;
				 
				updateObjectStateNew.parallizeFacadeBeforeFieldAccessVolatile(current.getId(), fieldId, operation , callbackStatePerThread);
				updateObjectStateNew.sendEventVolatile(callbackStatePerThread, order , fieldId, methodId, operation, current.getId());
				
			  
		  }
	  }

	
	
	
	
	
	
	
	
	
	
	
}
