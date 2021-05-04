package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import static com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.TLinkableForPotentialOrder.linked;

import java.util.Arrays;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderListFactory;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderSingle;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.TLinkableForPotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.CreatePotentialOrderFactoryContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForPotentialOrderFactory;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

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
 * algorithm:
 * 	1) sort
 * 	2) search for deadlocks
 *  3) add found deadlocks to context
 *  4) create PotentialOrderFactory list
 *  5) create Potential orders through PotentialOrderFactory list. 
 * 
 */


public class CreatePotentialOrderList {
	
	
	public  PotentialOrderListFactory create(TLinkableForSyncAction[] syncActions) {
		Arrays.sort( syncActions , new ComparatorSyncActionByCategoryAndIndex()  );
		
		TLinkedList<TLinkableForPotentialOrderFactory> factoryList = new TLinkedList<TLinkableForPotentialOrderFactory>();
		CreatePotentialOrderFactoryContext context = new CreatePotentialOrderFactoryContext();
		
		int currentCategory = SyncAction.UNDEFINED_CATEGORY;
		int currentId = SyncAction.UNDEFINED_ID;
		
		
		for( int i = 0 ; i < syncActions.length; i++ ) {
		
			if(  syncActions[i].syncAction.category() !=   currentCategory ||  
					syncActions[i].syncAction.id() !=   currentId ) {
				context.reset();
			
			    currentId       =  syncActions[i].syncAction.id();
			    currentCategory = syncActions[i].syncAction.category();
			
			}
			
			syncActions[i].syncAction.addPotentialOrderFactory(context, factoryList);
			
		}
		
		
		TLinkableForPotentialOrderFactory[] factoryArray = factoryList.toArray(new TLinkableForPotentialOrderFactory[0]);
		PotentialOrderListFactory result = new PotentialOrderListFactory();
		
		for( int start = 0 ; start < factoryArray.length; start++ ) {
			addForSyncAction(start, factoryArray, result);
		}
		
		
		return result;
	}
	
	
	private void addForSyncAction(int start,TLinkableForPotentialOrderFactory[] factoryArray,PotentialOrderListFactory result  ) {
		for( int i = start + 1 ;   i <factoryArray.length; i++ ) {
			if(  factoryArray[start].factory.category() !=    factoryArray[i].factory.category() ||  
					factoryArray[start].factory.id() !=    factoryArray[i].factory.id() ) {
				return;
			}
			
			factoryArray[start].factory.addPotentialOrder(factoryArray[i].factory  , result  );	
			
		}
			
		
	}
	
	
	

}
