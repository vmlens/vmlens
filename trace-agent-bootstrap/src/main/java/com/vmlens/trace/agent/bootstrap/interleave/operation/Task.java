package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PositionAndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrderTwice;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class Task  implements  OperationTyp {

	public static final int FORK_JOIN_TASK = 1;
	
	
	private final int taskId;
	
	
	
	
	public Task(int atomicId) {
		super();
		this.taskId = atomicId;
	}

	@Override
	public void fill(RelationMap relationMap, Position position, TIntHashSet takeMonitorIds) {
		
		relationMap.tasks.add(new PositionAndOperation(position,this));
		
		
		
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
			
		return true;
	}

	
	@Override
	public String toString() {

		// {"loopId":1,"runId":1,"threadId":5,"actualOperation":{"jsonClass":"ActualOjectLongId","objectHashCode":2,"operation":{"fieldId":5,"operation":1}}}
		
		return "{\"jsonClass\":\"Task\"}";
	}

	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + taskId;
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
		Task other = (Task) obj;
		if (taskId != other.taskId)
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
		orderList.addPotentialOrder(new PotentialOrderTwice(new LeftBeforeRight(a, b) ,new LeftBeforeRight(b, a)  ));
		
	}
	
}
