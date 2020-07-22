package com.vmlens.trace.agent.bootstrap.interleave.operation;


import com.vmlens.trace.agent.bootstrap.interleave.MonitorInfo;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationMap;

import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.hash.TIntHashSet;

public interface OperationTyp {

	void fill(RelationMap relationMap , Position position,TIntHashSet takeMonitorIds );
	
	void prefill(TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo,int threadIndex);
	

	boolean createsSyncRelation( OperationTyp other );

	void setDataFromMonitorStack(MonitorLockEnterStack monitorLockEnterStack,int threadIndex ,int  perThreadPosition);

	void addPotentialRelation(RelationList orderList, Position position, OperationTyp operation, Position position2);
	
}
