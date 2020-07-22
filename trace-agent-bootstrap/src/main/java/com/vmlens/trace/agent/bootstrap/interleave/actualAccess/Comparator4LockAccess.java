package com.vmlens.trace.agent.bootstrap.interleave.actualAccess;

import java.util.Comparator;

public class Comparator4LockAccess implements Comparator<LockAccess > {

	@Override
	public int compare(LockAccess o1, LockAccess o2) {
		
		if( o1.threadIndex != o2.threadIndex )
		{
		   return	Integer.compare( o1.threadIndex , o2.threadIndex);
		}
	
		if( o1.position != o2.position)
		{
			return Integer.compare( o1.position , o2.position);
		}
		
		
		
		
		return Integer.compare( o1.lockPosition , o2.lockPosition);
	}

}
