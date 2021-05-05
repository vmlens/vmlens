package com.vmlens.trace.agent.bootstrap.interleave.potentialOrder;



import gnu.trove.list.TLinkable;

public class TLinkableForPotentialOrder  implements  TLinkable<TLinkableForPotentialOrder>{
	
	private TLinkableForPotentialOrder next;
	private TLinkableForPotentialOrder previous;
	
	public final PotentialOrder potentialOrder;

	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TLinkableForPotentialOrder other = (TLinkableForPotentialOrder) obj;
		if (potentialOrder == null) {
			if (other.potentialOrder != null)
				return false;
		} else if (!potentialOrder.equals(other.potentialOrder))
			return false;
		return true;
	}





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
