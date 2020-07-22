package com.anarsoft.trace.agent.runtime.filter;

import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

public class FilterBuilder {

	
	
	public static FilterState create(String token,boolean returnValue,boolean defaultValue)
	{
		if( token == null )
		{
			return new FilterStateDefaultValue(defaultValue);
		}
		
		if( token.trim().equals("") )
		{
			return new FilterStateDefaultValue(defaultValue);
		}
		
		
		 THashSet<String> nameSet = new THashSet<String>();
		
		int index = token.indexOf(';');
		
		while( index > 0 )
		{
			
			String filter = token.substring(0, index );
			nameSet.add(filter.replace('.', '/'));
			
			token = token.substring(index + 1);
			index = token.indexOf(';');
			
		}
		
		if( token.length() > 0 )
		{
			nameSet.add(token.replace('.', '/'));
		}
		
		return new FilterStateBasedOnList(nameSet,returnValue);
		
		
		
	}
	
	
	public static FilterState createExcludeFilter(String token)
	{
		return create( token, false,true);
	}
	
	public static FilterState createIncludeFilter(String token)
	{
		return create( token, true,true);
	}
	
	public static FilterState createOnlyWhenSetFilter(String token)
	{
		return create( token, true,false);
	}
	
	
	
	
	
	public static void main(String[] args)
	{
//		FilterList filterList = new FilterList(createIncludeFilter(null) , createExcludeFilter(null) , 
//				createIncludeFilter(null) , createOnlyWhenSetFilter(null) , createOnlyWhenSetFilter(null));
//		
//		
//	System.out.println(	filterList.traceField("java.util.ArrayDeque") );
//	System.out.println(	filterList.doNotTraceWhenIn("java.util.ArrayDeque"));
//	System.out.println(	filterList.onlyTraceWhenIn("java.util.ArrayDeque"));
	}
	
	
}
