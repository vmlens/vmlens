package com.vmlens.trace.agent.bootstrap.interleave.potentialOrder;

public interface PotentialOrderFactory {
	
	int category();
	int id();
	void addPotentialOrder( PotentialOrderFactory other,PotentialOrderListFactory orderList );
	

}
