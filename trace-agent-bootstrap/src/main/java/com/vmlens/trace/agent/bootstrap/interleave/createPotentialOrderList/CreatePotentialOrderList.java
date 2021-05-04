package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import java.util.Arrays;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import static com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList.TLinkableForPotentialOrder.linked;
import gnu.trove.list.linked.TLinkedList;

/**
 * 
 * volatile fields
 * 				read/write/atomice
 * 
 * 
 * locks/monitor
 * 			 
 * 
 * 
 * 
 * deadlocks
 * 		extra, does not apply to all monitor/lock operations
 *      -> depends on the other monitors flow, additional
 * 
 * 
 * 
 * atomic
 * 
 * 
 * callback
 * 
 * 
 * algo:
 * 		sort
 * 		if same category and id 
 *           all combinations
 * 		
 * 		remove from potential order list,  sort and if higher than 2 do not add to new list
 * 
 */


public class CreatePotentialOrderList {
	
	
	public  TLinkableForPotentialOrder[] create(TLinkableForSyncAction[] syncActions) {
		Arrays.sort( syncActions , new ComparatorSyncActionByCategoryAndIndex()  );
		
		TLinkedList<TLinkableForPotentialOrder> result = new TLinkedList<TLinkableForPotentialOrder>();
		
		for( int start = 0 ; start < syncActions.length; start++ ) {
			addForSyncAction(start, syncActions, result);
		}
		
		
		return result.toArray(new TLinkableForPotentialOrder[0]);
	}
	
	
	private void addForSyncAction(int start,TLinkableForSyncAction[] syncActions,TLinkedList<TLinkableForPotentialOrder> result  ) {
		for( int i = start + 1 ;   i <syncActions.length; i++ ) {
			if(  syncActions[start].syncAction.category() !=    syncActions[i].syncAction.category() ||  
					syncActions[start].syncAction.id() !=    syncActions[i].syncAction.id() ) {
				return;
			}
			
			if(   syncActions[start].syncAction.createsSyncRelation(syncActions[i].syncAction)  ) {
				result.add( linked( new PotentialOrderSingle(  new LeftBeforeRight(syncActions[start].syncAction ,syncActions[i].syncAction ) , 
						new LeftBeforeRight(syncActions[i].syncAction ,syncActions[start].syncAction )) )) ;
			}
			
			
			
		}
			
		
	}
	
	
	

}
