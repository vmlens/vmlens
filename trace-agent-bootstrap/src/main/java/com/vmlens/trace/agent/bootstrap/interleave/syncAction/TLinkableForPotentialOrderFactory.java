package com.vmlens.trace.agent.bootstrap.interleave.syncAction;

import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderFactory;

import gnu.trove.list.TLinkable;

public class TLinkableForPotentialOrderFactory implements  TLinkable<TLinkableForPotentialOrderFactory> {
	
	
	private TLinkableForPotentialOrderFactory next;
	private TLinkableForPotentialOrderFactory previous;
	
	
	
	public final PotentialOrderFactory factory;

	private TLinkableForPotentialOrderFactory(PotentialOrderFactory factory) {
		super();
		this.factory = factory;
	}

	public TLinkableForPotentialOrderFactory getNext() {
		return next;
	}

	public void setNext(TLinkableForPotentialOrderFactory next) {
		this.next = next;
	}

	public TLinkableForPotentialOrderFactory getPrevious() {
		return previous;
	}

	public void setPrevious(TLinkableForPotentialOrderFactory previous) {
		this.previous = previous;
	}

	
	public static TLinkableForPotentialOrderFactory linked(PotentialOrderFactory factory) {
		return new TLinkableForPotentialOrderFactory(factory);
	}
	
	
}
