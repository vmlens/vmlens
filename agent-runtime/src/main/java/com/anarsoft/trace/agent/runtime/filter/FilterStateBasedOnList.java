package com.anarsoft.trace.agent.runtime.filter;

import com.anarsoft.trace.agent.runtime.util.AntPatternMatcher;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

public class FilterStateBasedOnList implements FilterState {

	private final THashSet<String> nameSet;
	private final boolean returnValue;
	private AntPatternMatcher antPatternMatcher;
	
	
	
	public FilterStateBasedOnList(THashSet<String> nameSet,boolean returnValue) {
		super();
		this.nameSet = nameSet;
		this.returnValue = returnValue;
		
		antPatternMatcher = new AntPatternMatcher();
		antPatternMatcher.setPathSeparator("/");
		
	}




	public boolean take(String name)
	{
		
		/**
		 * Nicht wieder einbauen,
		 * benötigt viel speicher und stalled den agenten
		 * 
		 */
		
		//String replaced = name.replace('/', '.');
		
		
		for(  String filter : nameSet )
		{
			if( antPatternMatcher.match(filter,name) )
			{
				return returnValue;
			}
		}
		
		
		return ! returnValue;
	}
	
	
	public boolean isDefined()
	{
		return true;
	}
	
}
