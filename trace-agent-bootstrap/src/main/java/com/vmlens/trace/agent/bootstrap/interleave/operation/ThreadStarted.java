package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PositionAndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class ThreadStarted implements OperationTyp {
	
	private final int startingThreadIndex;
	public final int startedThreadIndex;
	
	public ThreadStarted(int startingThreadIndex, int startedThreadIndex) {
		super();
		this.startingThreadIndex = startingThreadIndex;
		this.startedThreadIndex = startedThreadIndex;
	}

	@Override
	public void fill(RelationMap relationMap, Position position,TIntHashSet takeMonitorIds) {
		relationMap.startThread.add(new PositionAndOperation(position,this));
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + startedThreadIndex;
		result = prime * result + startingThreadIndex;
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
		ThreadStarted other = (ThreadStarted) obj;
		if (startedThreadIndex != other.startedThreadIndex)
			return false;
		if (startingThreadIndex != other.startingThreadIndex)
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
		
	};
	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
			Position b) {
		
	}

}
