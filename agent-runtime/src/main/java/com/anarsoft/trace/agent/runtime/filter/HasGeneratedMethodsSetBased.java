package com.anarsoft.trace.agent.runtime.filter;

import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

public class HasGeneratedMethodsSetBased  implements  HasGeneratedMethods {

	private static final THashSet<String> ownerWithMethod = new THashSet<String>();
	
	
	
	public static  void addClassName(String in)
	{
		synchronized(ownerWithMethod)
		{
			ownerWithMethod.add(in);
		}
		
	}





	
	public  boolean hasGeneratedMethods(String owner) {
		
		
		synchronized(ownerWithMethod)
		{
		   boolean result =   ownerWithMethod.contains(owner);
		   
		  
		   return result;
		}
	}

}
