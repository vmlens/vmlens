package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForLeftBeforeRight;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForLeftBeforeRight.linked;


import gnu.trove.list.linked.TLinkedList;

public class PotentialOrderSingle extends PotentialOrder  {
	
	private final LeftBeforeRight optionA;
	private final LeftBeforeRight optionB;
	
	

	
	

	public PotentialOrderSingle(LeftBeforeRight optionA, LeftBeforeRight optionB) {
		super();
		this.optionA = optionA;
		this.optionB = optionB;
	}

	@Override
	public void add(TLinkedList<TLinkableForLeftBeforeRight> leftBeforeRightList, boolean reverse) {
		
       if(reverse) {
    	   leftBeforeRightList.add(linked(optionA));
       }
       else    {
    	   leftBeforeRightList.add(linked(optionB));
       }
		
	}

	
	@Override
	public String toString() {
		return "(" + optionA + ")|(" + optionB + ")";
	}
	
	

}
