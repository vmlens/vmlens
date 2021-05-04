package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;



import gnu.trove.list.TLinkable;

public class TLinkableForLeftBeforeRight  implements  TLinkable<TLinkableForLeftBeforeRight>{
	
	private TLinkableForLeftBeforeRight next;
	private TLinkableForLeftBeforeRight previous;
	
	public final LeftBeforeRight leftBeforeRight;

	private TLinkableForLeftBeforeRight(LeftBeforeRight leftBeforeRight) {
		super();
		this.leftBeforeRight = leftBeforeRight;
	}

	public TLinkableForLeftBeforeRight getNext() {
		return next;
	}

	public void setNext(TLinkableForLeftBeforeRight next) {
		this.next = next;
	}

	public TLinkableForLeftBeforeRight getPrevious() {
		return previous;
	}

	public void setPrevious(TLinkableForLeftBeforeRight previous) {
		this.previous = previous;
	}


	
	public static TLinkableForLeftBeforeRight linked(LeftBeforeRight  leftBeforeRight) {
		return new TLinkableForLeftBeforeRight(leftBeforeRight);
	}
	
	
}
