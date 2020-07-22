package com.vmlens.trace.agent.bootstrap;


import gnu.trove.map.hash.TObjectIntHashMap;



public class StaticMonitorRepository {


	private static TObjectIntHashMap<String> className2Id = new TObjectIntHashMap<String>();
	private static int maxId=0;

	public static synchronized int getOrCreate(String className)
	{

		if( className2Id.contains(className) )
		{
			return className2Id.get(className);
		}


		    int	result = maxId;
			maxId++;
			className2Id.put(className, result);



		return result;
	}



}
