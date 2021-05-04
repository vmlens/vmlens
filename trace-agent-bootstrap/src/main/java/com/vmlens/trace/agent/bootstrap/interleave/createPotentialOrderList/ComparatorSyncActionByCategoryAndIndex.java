package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import java.util.Comparator;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;



public class ComparatorSyncActionByCategoryAndIndex implements Comparator<TLinkableForSyncAction> {

	@Override
	public int compare(TLinkableForSyncAction o1, TLinkableForSyncAction o2) {
		
		if( o1.syncAction.category() != o2.syncAction.category() )
		{
			return Integer.compare(o1.syncAction.category(), o2.syncAction.category());
		}
		
		if(o1.syncAction.id() !=  o2.syncAction.id())
		{
			return  Integer.compare(o1.syncAction.id(), o2.syncAction.id());
		}
		
		
		if(o1.syncAction.threadIndex !=  o2.syncAction.threadIndex)
		{
			return  Integer.compare(o1.syncAction.threadIndex, o2.syncAction.threadIndex);
		}
		
		
		return  Integer.compare(o1.syncAction.position, o2.syncAction.position);
	}

}
