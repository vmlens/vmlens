package com.anarsoft.trace.agent.runtime.waitPoints;

import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

import com.anarsoft.trace.agent.runtime.filter.FilterState;




public class FilterWithAnnotations  {

	
	private final FilterState fromConfig;
	private final THashSet<MethodInClassIdentifier>  fromAnnotaton = new THashSet<MethodInClassIdentifier>();
	private final Object LOCK = new Object();
	
	

	public FilterWithAnnotations(FilterState fromConfig) {
		super();
		this.fromConfig = fromConfig;
	}

	
	public boolean takeFromAnnotation(MethodInClassIdentifier id)
	{
		synchronized(LOCK)
		{
			return fromAnnotaton.contains(id);
		}
	}
	
	
	public void addFromAnnotation(MethodInClassIdentifier id)
	{
		synchronized(LOCK)
		{
			fromAnnotaton.add(id);
		}
	}
	
	
	
	
	public boolean takeFromConfig(String name) {
	
		return fromConfig.take(name);
	}

}
