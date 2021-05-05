package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import java.util.Arrays;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.PotentialOrderIndex.PotentialOrderIndexState;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.TLinkableForPotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

import gnu.trove.list.linked.TLinkedList;

/**
 * 
 * hasNext
 * next
 * 
 * contains PotentialOrderIndex and PotentialOrderList
 * 
 * Entry point for createThreadIds. Responsible to create thread indices out of a list of potential orders.
 * Log when a potential order can not create ThreadIds, similar to timeout
 * 
 * equals if it contains the same PotentialOrderList
 * 
 * 
 * 
 * 
 */


public class CreateThreadIds {
	
	private static final SortThreadIds SORT_THREAD_IDS = new SortThreadIds(); 
	
	private final  TLinkableForSyncAction[] syncActions;
	private final TLinkableForPotentialOrder[] potentialOrderArray;
	private final PotentialOrderIndex index;
	private  TLinkableForSyncAction[] next;
	
	public CreateThreadIds(TLinkableForSyncAction[] syncActions,TLinkableForPotentialOrder[] potentialOrderArray) {
		super();
		this.syncActions = syncActions;
		this.potentialOrderArray = potentialOrderArray;
		this.index = new PotentialOrderIndex(potentialOrderArray.length);
		 calculateNext();
	}
	
	
	private void calculateNext() {
		next = null;
		
		while(next == null && index.hasNext() ) 	{
			PotentialOrderIndexState currentIndex = index.next();
			TLinkedList<TLinkableForLeftBeforeRight> leftBeforeRightList = new TLinkedList<TLinkableForLeftBeforeRight>();
			
			for(int i = 0 ; i < potentialOrderArray.length ; i++ ) 	{
				
				potentialOrderArray[i].potentialOrder.add(leftBeforeRightList, currentIndex.isSet(i));  
				
			}
			
			next = SORT_THREAD_IDS.sort(leftBeforeRightList.toArray(new TLinkableForLeftBeforeRight[0]), syncActions);
			
		}
		
		
		
	}
	
	
	public boolean hasNext() {
		return next != null;
	}
	
	
	public TLinkableForSyncAction[] next() {
		TLinkableForSyncAction[] temp = next;
		 calculateNext();
		return temp;
	}



	@Override
	public boolean equals(Object obj) {
		
		if( ! ( obj instanceof  CreateThreadIds) ) {
			return false;
		}
		
		CreateThreadIds other = (CreateThreadIds) obj;
		
		return Arrays.equals( potentialOrderArray  , other.potentialOrderArray);
	}
	
	
	
	
	
	
	
	
	
	
	
}
