package com.vmlens.trace.agent.bootstrap.interleave.potentialOrder;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForLeftBeforeRight;

import gnu.trove.list.linked.TLinkedList;

public abstract class PotentialOrder {
	
	
	public abstract void add(TLinkedList<TLinkableForLeftBeforeRight> leftBeforeRight, boolean reverse);
	

}
