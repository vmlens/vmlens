package com.vmlens.trace.agent.bootstrap;

import gnu.trove.map.hash.THashMap;

public class ClassInheritanceRepository {

	
	private static final THashMap<String,String> child2parent = new THashMap<String,String> ();
	
	
	
	public static synchronized void addRelation(String child, String parent)
	{
		child2parent.put( child, parent );
	}
	
	
	public static synchronized String getParent4Child(String child)
	{
		return child2parent.get( child );
	}
	
	
	
}
