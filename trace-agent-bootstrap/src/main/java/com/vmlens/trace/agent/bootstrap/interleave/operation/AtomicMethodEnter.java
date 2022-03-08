package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class AtomicMethodEnter implements  OperationTyp  {

	@Override
	public void fill(RelationMap relationMap, Position position, TIntHashSet takeMonitorIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo, int threadIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		
		
		monitorLockEnterStack.atomic.push(new Position(threadIndex,perThreadPosition));
		
	}

	@Override
	public void addPotentialRelation(RelationList orderList, Position position, OperationTyp operation,
                                     Position position2) {
		// TODO Auto-generated method stub
		
	}

}
