package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Comparator;


import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

public class ComparatorForSyncActionByThreadIndexAndPosition implements Comparator<TLinkableForSyncAction > {

	@Override
	public int compare(TLinkableForSyncAction o1, TLinkableForSyncAction o2) {
		
		if( o1.syncAction.threadIndex !=  o2.syncAction.threadIndex  )
		{
		   return	Integer.compare(o1.syncAction.threadIndex , o2.syncAction.threadIndex);
		}
		
		
		
		return Integer.compare( o1.syncAction.position  , o2.syncAction.position );
	}

}
