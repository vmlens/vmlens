package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.*;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class LockEnterOrExit  implements  OperationTyp  {

	private final boolean isShared;
	private Position lockEnter;
	
	
	
	
	public LockEnterOrExit(boolean isShared) {
		super();
		this.isShared = isShared;
	}

	@Override
	public void fill(RelationMap relationMap, Position position, TIntHashSet takeMonitorIds) {
		
		if(  isShared )
		{
			relationMap.sharedLockEnterAccess.add(new PositionAndOperation(position,this));
			
		}
		else
		{
			relationMap.exclusiveLockExitAccess.add(new PositionAndOperation(position,this));
		}
		
	
		
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
		return true;
	}

	
	@Override
	public String toString() {
	
		return "{\"jsonClass\":\"LockExit\",\"isShared\":" + isShared + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isShared ? 1231 : 1237);
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
		LockEnterOrExit other = (LockEnterOrExit) obj;
		if (isShared != other.isShared)
			return false;
		return true;
	}

	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex) {
	
		
	}

	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		if(  ! isShared)
		{
			if(! monitorLockEnterStack.lock.isEmpty())
			{
				lockEnter =	monitorLockEnterStack.lock.poll();
			}
			else
			{
				AgentLogCallback.logError("empty " + threadIndex + " " + perThreadPosition);
			}
		
		}
	
	}
	
	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
                                     Position b) {
	
		if(isShared)
		{
			orderList.addPotentialOrder(new PotentialOrderTwice(new LeftBeforeRight(a, b) ,new LeftBeforeRight(b, a)  ));
			
		}
		else
		{
			
			if( operation != null )
			{
				LockEnterOrExit lockExit = (LockEnterOrExit)operation;
				
				orderList.addPotentialOrder(new PotentialOrderSingle (new LeftBeforeRight(a, lockExit.lockEnter) ,new LeftBeforeRight(b, lockEnter)  ));
			}
			
			

		}
		
		
	}
	
	
	
	
}
