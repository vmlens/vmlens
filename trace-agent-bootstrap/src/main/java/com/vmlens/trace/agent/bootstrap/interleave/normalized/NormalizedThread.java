package com.vmlens.trace.agent.bootstrap.interleave.normalized;



import java.util.Iterator;

import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

public class NormalizedThread implements TLinkable<NormalizedThread> {

	private NormalizedThread next;
	private NormalizedThread previous;
	
	private final TLinkedList<NormalizedAccess> operations;
	
	
	public NormalizedThread(TLinkedList<NormalizedAccess> operations) {
		super();
		this.operations = operations;
	}
	
	
	
	public Iterator<NormalizedAccess> iterator() {
		return operations.iterator();
	}



	public NormalizedThread getNext() {
		return next;
	}
	public void setNext(NormalizedThread next) {
		this.next = next;
	}
	public NormalizedThread getPrevious() {
		return previous;
	}
	public void setPrevious(NormalizedThread previous) {
		this.previous = previous;
	}
	
	
	
	
	
	
	
}
