package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import java.util.Comparator;

public class Comparator4PositionAndOperation implements Comparator<PositionAndOperation > {

	@Override
	public int compare(PositionAndOperation o1, PositionAndOperation o2) {
		
		if( o1.position.threadIndex != o2.position.threadIndex )
		{
		   return	Integer.compare( o1.position.threadIndex , o2.position.threadIndex);
		}
	
		
			return Integer.compare( o1.position.position , o2.position.position);
		
		
		
		
		
		
	}

}
