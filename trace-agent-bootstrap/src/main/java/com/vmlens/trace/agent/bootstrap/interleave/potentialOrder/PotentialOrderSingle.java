package com.vmlens.trace.agent.bootstrap.interleave.potentialOrder;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((optionA == null) ? 0 : optionA.hashCode());
		result = prime * result + ((optionB == null) ? 0 : optionB.hashCode());
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
		PotentialOrderSingle other = (PotentialOrderSingle) obj;
		if (optionA == null) {
			if (other.optionA != null)
				return false;
		} else if (!optionA.equals(other.optionA))
			return false;
		if (optionB == null) {
			if (other.optionB != null)
				return false;
		} else if (!optionB.equals(other.optionB))
			return false;
		return true;
	}
	
	

}
