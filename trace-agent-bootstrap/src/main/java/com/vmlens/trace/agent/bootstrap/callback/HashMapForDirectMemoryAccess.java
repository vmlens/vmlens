package com.vmlens.trace.agent.bootstrap.callback;

import gnu.trove.map.hash.TLongObjectHashMap;

public class HashMapForDirectMemoryAccess<ELEMENT> {
	
	
	private final TLongObjectHashMap<ELEMENT> memoryAddress2Element = new
            TLongObjectHashMap<ELEMENT>();
	
	
	public static interface ElementFactory<ELEMENT>
	{
		ELEMENT create();
		
	}
	

	private final ElementFactory<ELEMENT> factory;
	
	
	
	
	
	
	
	
	public HashMapForDirectMemoryAccess(ElementFactory<ELEMENT> factory) {
		super();
		this.factory = factory;
	}


	public synchronized ELEMENT getOrCreate(long address)
	{
		
		ELEMENT result = memoryAddress2Element.get(address);
		
		if( result == null )
		{
			result = factory.create();
			memoryAddress2Element.put(address, result);
			
			
			
		}
		
		
		
		return result;
		
	}
	
	
//	clear()
//	{
//		
//	}
//	
//	
//	allocate()
//	{
//		
//	}
//	
//	
//	reallocate()
//	{
//		
//	}

}
