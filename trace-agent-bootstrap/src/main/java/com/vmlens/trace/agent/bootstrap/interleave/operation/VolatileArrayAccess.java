package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PositionAndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrderSingle;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class VolatileArrayAccess  implements  OperationTyp  {

	private final long index;
	private final int operation;
	
	@Override
	public String toString() {

		// {"loopId":1,"runId":1,"threadId":5,"actualOperation":{"jsonClass":"ActualOjectLongId","objectHashCode":2,"operation":{"fieldId":5,"operation":1}}}
		
		return "{\"jsonClass\":\"VolatileArrayAccess\",\"index\":" + index + ",\"operation\":" + operation+ "}";
	}
	

	public VolatileArrayAccess(long index, int operation) {
		super();
		this.index = index;
		this.operation = operation;
	}

	@Override
	public void fill(RelationMap relationMap, Position position, TIntHashSet takeMonitorIds) {
	TLinkedList<PositionAndOperation> list = relationMap.volatileArrayAccess.get( index );
		
		if( list == null )
		{
			list = new TLinkedList<PositionAndOperation>();
			relationMap.volatileArrayAccess.put( index  , list);
		}
		
		
		list.add(new PositionAndOperation(position,this));
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
		if( other instanceof  VolatileArrayAccess)
		{	
			
			return  (operation | ((VolatileArrayAccess) other).operation ) >= VolatileFieldAccess.MIN_OPERATION;
		}
		else
		{
			return false;
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (index ^ (index >>> 32));
		result = prime * result + operation;
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
		VolatileArrayAccess other = (VolatileArrayAccess) obj;
		if (index != other.index)
			return false;
		if (operation != other.operation)
			return false;
		return true;
	}


	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
                                     Position b) {
		orderList.addPotentialOrder(new PotentialOrderSingle(new LeftBeforeRight(a, b) ,new LeftBeforeRight(b, a)  ));
		
	}
	
	
	
}
