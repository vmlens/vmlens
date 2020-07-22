package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Comparator;

public class Comparator4MonitorIdAndInfo implements Comparator<MonitorIdAndInfo > {

	@Override
	public int compare(MonitorIdAndInfo o1, MonitorIdAndInfo o2) {
		
		if( o1.monitorInfo.threadIndices.size() !=  o2.monitorInfo.threadIndices.size()  )
		{
		   return	Integer.compare(o1.monitorInfo.threadIndices.size() , o2.monitorInfo.threadIndices.size());
		}
		
		
		
		return Integer.compare( o1.monitorInfo.positionSet.size()  , o2.monitorInfo.positionSet.size() );
	}

}
