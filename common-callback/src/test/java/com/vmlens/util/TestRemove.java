package com.vmlens.util;

public class TestRemove {

	
	public static String removeInnerClassName(String in)
	{
		int index = in.indexOf('$');
		
		if( index > 0 )
		{
			return in.substring(0, index);
			
			
			
		}
		else
		{
			return in;
		}
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(  removeInnerClassName (  "java/util/concurrent/ConcurrentHashMap" ));
		
		
		
		
		
	}
	
	
}
