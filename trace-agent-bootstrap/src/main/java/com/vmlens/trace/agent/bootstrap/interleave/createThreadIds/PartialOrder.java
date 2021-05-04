package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

/**
 * 
 * 
 * getSort : Int
 * 			left before
 * 			left after
 * 			undefined
 * 
 * 
 * algorithm:
 *      for left find the one which nearest to the position on the left
 *      check if we find a relation which is smaller from the right.				
 * 
 * 
 * 
 * 
 * 
 */


public class PartialOrder  {
	
	static final int LEFT_COMES_BEFORE = 0;
	static final int LEFT_COMES_AFTER = 1;
	static final int UNDEFINED = 2;	
	
	private final TLinkableForLeftBeforeRight[] order;

	 PartialOrder(TLinkableForLeftBeforeRight[] order) {
		super();
		this.order = order;
	}
	
	public int getSort(TLinkableForSyncAction left, TLinkableForSyncAction right) {
		
		
		if( left.syncAction.threadIndex == right.syncAction.threadIndex ) {
			
			if(left.syncAction.position < right.syncAction.position ) {
				return LEFT_COMES_BEFORE;
			}
			else 	if(left.syncAction.position > right.syncAction.position ) {
				return LEFT_COMES_AFTER;
			} else 	{
				return   UNDEFINED;
			}
				
			
		}
		
		
		
		
		if(  isLeftBeforeRight(left.syncAction,right.syncAction) ) {
			return LEFT_COMES_BEFORE;
		}
		else if( isLeftBeforeRight(right.syncAction,left.syncAction)) {
			return LEFT_COMES_AFTER;
		}
		else {
			return   UNDEFINED;
		}
			
			
			
		
		
	}
	
	
	public boolean isLeftBeforeRight(SyncAction left, SyncAction right)
	{
		for(int i = 0 ; i < order.length ; i++)
		{
			if(order[i].leftBeforeRight.isLeftBeforeRight( left,  right) ) {
				return true;
			}
		}
		
		
		
		return false;
	}
	

}
