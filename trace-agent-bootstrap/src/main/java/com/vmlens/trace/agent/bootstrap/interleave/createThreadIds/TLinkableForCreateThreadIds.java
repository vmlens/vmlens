package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import gnu.trove.list.TLinkable;

public class TLinkableForCreateThreadIds  implements  TLinkable<TLinkableForCreateThreadIds> {
	
	private TLinkableForCreateThreadIds next;
	private TLinkableForCreateThreadIds previous;
	
	
	public final CreateThreadIds createThreadIds;

	private TLinkableForCreateThreadIds(CreateThreadIds createThreadIds) {
		super();
		this.createThreadIds = createThreadIds;
	}

	public TLinkableForCreateThreadIds getNext() {
		return next;
	}

	public void setNext(TLinkableForCreateThreadIds next) {
		this.next = next;
	}

	public TLinkableForCreateThreadIds getPrevious() {
		return previous;
	}

	public void setPrevious(TLinkableForCreateThreadIds previous) {
		this.previous = previous;
	}
	
	
	public static TLinkableForCreateThreadIds linked(CreateThreadIds createThreadIds) {
		return new TLinkableForCreateThreadIds(createThreadIds);
	}
	

}
