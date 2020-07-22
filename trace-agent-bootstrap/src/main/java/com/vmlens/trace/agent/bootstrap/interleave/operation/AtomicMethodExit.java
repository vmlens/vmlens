package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PositionAndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrderSingle;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class AtomicMethodExit implements  OperationTyp  {

	private final int methodId;
	private final int atomicId;
	private final boolean hasCallback;
	private Position atomicMethodEnter;
	
	public AtomicMethodExit(int methodId,int atomicId,boolean hasCallback) {
		super();
		this.methodId = methodId;
		this.atomicId = atomicId;
		this.hasCallback = hasCallback;
	}

	@Override
	public void fill(RelationMap relationMap, Position position,TIntHashSet takeMonitorIds) {
		
		if( atomicMethodEnter != null)
		{
			TLinkedList<PositionAndOperation> list = relationMap.atomicAccess.get( atomicId );
			
			if( list == null )
			{
				list = new TLinkedList<PositionAndOperation>();
				relationMap.atomicAccess.put( atomicId  , list);
			}
			
			
			list.add(new PositionAndOperation(position,this));
			
		}
		
		
	
		
	
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
			
		return true;
	}
	
	
	
	

	@Override
	public String toString() {
		return "AtomicMethodCall [methodId=" + methodId + ", atomicId=" + atomicId + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + atomicId;
		result = prime * result + (hasCallback ? 1231 : 1237);
		result = prime * result + methodId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtomicMethodExit other = (AtomicMethodExit) obj;
		if (atomicId != other.atomicId)
			return false;
		if (hasCallback != other.hasCallback)
			return false;
		if (methodId != other.methodId)
			return false;
		return true;
	}

	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex) {
		
		
	}

	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		
		if(! monitorLockEnterStack.atomic.isEmpty())
		{
			atomicMethodEnter = monitorLockEnterStack.atomic.poll();
		}
		else
		{
			AgentLogCallback.logError(" monitorLockEnterStack.atomic.isEmpty()");
		}
		
	
		
	}

	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
			Position b) {
		/*
		 * 	
		MonitorExit monitorExit = (MonitorExit)operation;
		
		orderList.addPotentialOrder(new PotentialOrder (new LeftBeforeRight(a, monitorExit.monitorEnter) ,new LeftBeforeRight(b, monitorEnter)  ));

		 */
		
		if( ! hasCallback )
		{
			AtomicMethodExit atomicMethodExit = (AtomicMethodExit) operation;
			
			
			orderList.addPotentialOrder(new PotentialOrderSingle (new LeftBeforeRight(a, atomicMethodExit.atomicMethodEnter) ,new LeftBeforeRight(b, atomicMethodEnter)  ));
		
		}
		
		
	}

	

	
	
	
	
	
	
}
