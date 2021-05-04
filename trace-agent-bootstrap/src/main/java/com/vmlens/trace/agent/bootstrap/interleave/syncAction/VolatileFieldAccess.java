package com.vmlens.trace.agent.bootstrap.interleave.syncAction;

import static com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.TLinkableForPotentialOrder.linked;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderFactory;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderListFactory;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderSingle;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForPotentialOrderFactory.linked;
import gnu.trove.list.linked.TLinkedList;

public class VolatileFieldAccess extends SyncAction implements PotentialOrderFactory {
	
	 static final int MIN_OPERATION = 3;
	
	private final int operation;
	private final int fieldId;
	
	public VolatileFieldAccess(int operation, int fieldId,int threadIndex, int position) {
		super(threadIndex,position);
		this.operation = operation;
		this.fieldId = fieldId;
	}
	
	

	@Override
	public String toString() {
		String text = "";
		
		if(operation == MemoryAccessType.IS_READ) {
			text="R_";
		} else if(operation == MemoryAccessType.IS_WRITE){
			text="W_";
		}  else 	{
			text="A_";
		}
			
		
		return text + threadIndex + "_" + position;
		
	}



	@Override
	public int category() {
		return VOLATILE_FIELD;
	}



	@Override
	public int id() {
		return fieldId;
	}



	@Override
	public void addPotentialOrderFactory(CreatePotentialOrderFactoryContext context,
			TLinkedList<TLinkableForPotentialOrderFactory> list) {
		list.add(linked(this));
		
	}



	@Override
	public void addPotentialOrder(PotentialOrderFactory other, PotentialOrderListFactory orderList) {
		if( other instanceof  VolatileFieldAccess)
		{	
			if(((VolatileFieldAccess) other).threadIndex == threadIndex) {
				return;
			}
			
			
			if(  (operation | ((VolatileFieldAccess) other).operation ) >= MIN_OPERATION) {
				orderList.addPotential( linked( new PotentialOrderSingle(  new LeftBeforeRight(this ,(VolatileFieldAccess) other ) , 
						new LeftBeforeRight((VolatileFieldAccess) other ,this )) )) ;
			}
		}
		else
		{
			return;
		}
	}
		
	



}
