package com.vmlens.trace.agent.bootstrap;

import gnu.trove.map.hash.TIntIntHashMap;

public class FieldId2WaitpointIdMap {

	
	private static TIntIntHashMap map = new TIntIntHashMap();
	
	
	public static synchronized void setWaitointId( int fieldId , int waipointId )
	{
		map.put(fieldId, waipointId);
		
	}
	
	public  static synchronized int getWaitointId(int fieldId)
	{
		
		if(map.contains(fieldId))
		{
			return map.get(fieldId);
		}
		else
		{
			return -1;
		}
		
		
	}
	
	
}
