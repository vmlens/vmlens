package com.vmlens.trace.agent.bootstrap.interleave;

import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

public class MonitorInfo {

	public final THashSet<MonitorPosition> positionSet = new THashSet<MonitorPosition>();
	public final TIntHashSet threadIndices = new TIntHashSet();
	
}
