package com.vmlens.trace.agent.bootstrap.interleave.operation;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.PotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public class BeginWithThread  implements OperationTyp {

	@Override
	public void fill(RelationMap relationMap, Position position,TIntHashSet takeMonitorIds) {
		
		
	}

	@Override
	public boolean createsSyncRelation(OperationTyp other) {
	
		return false;
	}

	@Override
	public void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex) {
		
		
	}

	@Override
	public void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addPotentialRelation(RelationList orderList, Position a, OperationTyp operation,
			Position b) {
		
	}

}
