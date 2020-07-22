package com.vmlens.trace.agent.bootstrap.interleave.actualAccess;

import java.util.Comparator;

public class Comparator4ActualAccess implements Comparator<ActualAccess > {

	@Override
	public int compare(ActualAccess o1, ActualAccess o2) {
		
		if( o1.threadIndex != o2.threadIndex )
		{
		   return	Integer.compare( o1.threadIndex , o2.threadIndex);
		}
		
		
		
		return Integer.compare( o1.position , o2.position);
	}

}
