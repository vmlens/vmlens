package com.vmlens.trace.agent.bootstrap.interleave.potentialOrder;

import gnu.trove.list.linked.TLinkedList;

/**
 * 
 * list of fixed orders for thread join
 * list of potential orders for everything else
 * 
 * 
 * 
 * @author thomas
 *
 */



public class PotentialOrderListFactory {

	private final TLinkedList<TLinkableForPotentialOrder> potentialOrders =
			new TLinkedList<TLinkableForPotentialOrder>();
	
	

	
	
	public void addPotential(TLinkableForPotentialOrder linked) {
		potentialOrders.add(linked);
		
	}

	
	public TLinkableForPotentialOrder[] createPotential() {
		
		return  potentialOrders.toArray(new TLinkableForPotentialOrder[0]);
		
			
		
	}
	
	
}
