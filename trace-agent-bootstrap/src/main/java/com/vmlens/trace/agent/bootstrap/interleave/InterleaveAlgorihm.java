package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Arrays;
import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList.CreatePotentialOrderList;
import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.CreateThreadIds;
import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForCreateThreadIds;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderListFactory;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForCreateThreadIds.linked;
import gnu.trove.list.linked.TLinkedList;

/**
 * 
 * 
 * hasNext
 * next
 * add
 * 
 * 
 * @author thomas
 *
 */


public class InterleaveAlgorihm {
	
	
	private int currentIndex = 0;
	private final TLinkedList<TLinkableForCreateThreadIds> createThreadIdsList = new TLinkedList<TLinkableForCreateThreadIds>();
	
	public boolean hasNext() {
		
		if( currentIndex >=  createThreadIdsList.size() ) {
			return false;
		}
		
		
		while(! createThreadIdsList.get(currentIndex).createThreadIds.hasNext()) {
			
			currentIndex++;
			
			if( currentIndex >=  createThreadIdsList.size() ) {
				return false;
			}
			
			
		}
		
		
		return true;
		
	}
	
	public TLinkableForSyncAction[] next() {
		return createThreadIdsList.get(currentIndex).createThreadIds.next();
	}
	

	public void add(TLinkedList<TLinkableForSyncAction> syncActionList) {
		
		/*
		 * TODO: could be made faster by first comparing the syncActionList.
		 * 
		 */
		
		TLinkableForSyncAction[] array = syncActionList.toArray(new TLinkableForSyncAction[0]);
		Arrays.sort( array , new ComparatorForSyncActionByThreadIndexAndPosition()  );
		
		
		PotentialOrderListFactory factory = new CreatePotentialOrderList().create(array);
		
		CreateThreadIds createThreadIds = new CreateThreadIds(array , factory.createPotential());
		boolean found = false;
		Iterator<TLinkableForCreateThreadIds> it = createThreadIdsList.iterator();
		
		while(it.hasNext() ) {
 			if( it.next().createThreadIds.equals(createThreadIds) ) {
 				found = true;
 				break;
 			}
		}
		
		
		if(!found) {
			createThreadIdsList.addLast(linked(createThreadIds));
		}
		
		
	}
	
	
	
}
