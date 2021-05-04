package com.vmlens.trace.agent.bootstrap.interleave.syncAction;



import gnu.trove.list.TLinkable;

public class TLinkableForSyncAction implements  TLinkable<TLinkableForSyncAction>{
	
	private TLinkableForSyncAction next;
	private TLinkableForSyncAction previous;
	
	public final SyncAction syncAction;
	

	public TLinkableForSyncAction(SyncAction syncAction) {
		super();
		this.syncAction = syncAction;
	}
	public TLinkableForSyncAction getNext() {
		return next;
	}
	public void setNext(TLinkableForSyncAction next) {
		this.next = next;
	}
	public TLinkableForSyncAction getPrevious() {
		return previous;
	}
	public void setPrevious(TLinkableForSyncAction previous) {
		this.previous = previous;
	}
	
	@Override
	public String toString() {
		
		return syncAction.toString();
	}
	
	public static TLinkableForSyncAction linked(SyncAction action) {
		return new TLinkableForSyncAction(action);
	}

}
