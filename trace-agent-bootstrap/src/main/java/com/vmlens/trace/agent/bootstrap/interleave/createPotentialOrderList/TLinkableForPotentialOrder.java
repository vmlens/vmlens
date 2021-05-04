package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;



import gnu.trove.list.TLinkable;

public class TLinkableForPotentialOrder  implements  TLinkable<TLinkableForPotentialOrder>{
	
	private TLinkableForPotentialOrder next;
	private TLinkableForPotentialOrder previous;
	
	public final PotentialOrder potentialOrder;

	


	
	@Override
	public String toString() {
		return potentialOrder.toString();
	}





	private TLinkableForPotentialOrder(PotentialOrder potentialOrder) {
		super();
		this.potentialOrder = potentialOrder;
	}





	public TLinkableForPotentialOrder getNext() {
		return next;
	}










	public void setNext(TLinkableForPotentialOrder next) {
		this.next = next;
	}










	public TLinkableForPotentialOrder getPrevious() {
		return previous;
	}










	public void setPrevious(TLinkableForPotentialOrder previous) {
		this.previous = previous;
	}










	public static TLinkableForPotentialOrder linked(PotentialOrder  potentialOrder) {
		return new TLinkableForPotentialOrder(potentialOrder);
	}
	
}
