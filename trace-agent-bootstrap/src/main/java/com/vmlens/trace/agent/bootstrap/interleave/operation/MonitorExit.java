package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorPosition;
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

public class MonitorExit implements  OperationTyp  {
	
	private final int methodId;
	private final boolean isStatic;
	private final int  position;
	private final int  monitorId;
	private Position monitorEnter;


	

	public MonitorExit(int methodId, boolean isStatic, int position,int monitorId) {
		super();
		this.methodId = methodId;
		this.isStatic = isStatic;
		this.position = position;
		this.monitorId = monitorId;
	}

	@Override
	public void fill(RelationMap relationMap, Position position,TIntHashSet takeMonitorIds) {
		
		
	if(takeMonitorIds.contains(monitorId))
	{
	TLinkedList<PositionAndOperation> list = relationMap.monitorAccess.get( monitorId );
		
		if( list == null )
		{
			list = new TLinkedList<PositionAndOperation>();
			relationMap.monitorAccess.put( monitorId  , list);
		}
		
		
		list.add(new PositionAndOperation(position,this));
		
		
		MonitorPosition key = new MonitorPosition(methodId , this.position);
		relationMap.monitorKey2Count.put(  key ,   relationMap.monitorKey2Count.get(key) + 1);
		
	}
		
	
		
		
		
		
		
		
	
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
			
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isStatic ? 1231 : 1237);
		result = prime * result + methodId;
		result = prime * result + monitorId;
		result = prime * result + position;
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
		MonitorExit other = (MonitorExit) obj;
		if (isStatic != other.isStatic)
			return false;
		if (methodId != other.methodId)
			return false;
		if (monitorId != other.monitorId)
			return false;
		if (position != other.position)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "MonitorExit [methodId=" + methodId + ", isStatic=" + isStatic + ", position=" + position
				+ ", monitorId=" + monitorId + ", monitorEnter=" + monitorEnter + "]";
	}

	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex) {
	
	    MonitorInfo info = monitorId2MonitorInfo.get(monitorId);
		
		if( info == null)
		{
			info = new MonitorInfo();
			 monitorId2MonitorInfo.put(monitorId, info);
		}
	    
		info.threadIndices.add(threadIndex);
		info.positionSet.add( new MonitorPosition(methodId,position) );
	    
	}

	
	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		
		monitorEnter =	monitorLockEnterStack.monitor.poll();
		
		
	}
	
	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
			Position b) {
		
		MonitorExit monitorExit = (MonitorExit)operation;
		
		orderList.addPotentialOrder(new PotentialOrderSingle (new LeftBeforeRight(a, monitorExit.monitorEnter) ,new LeftBeforeRight(b, monitorEnter)  ));
		
	}
	
	
}
