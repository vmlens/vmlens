package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

import gnu.trove.map.hash.TIntObjectHashMap;

public class FieldId2ElementMapBased<Element> implements FieldId2Element<Element> {

	
	private final TIntObjectHashMap<Element> fieldId2ObjectIdAndThreadState = new
            TIntObjectHashMap<Element>();
	
	
	
	public  FieldId2ElementMapBased(LinkedListElement<Element> start)
	{
	LinkedListElement<Element> current = start;
		
		while( current != null )
		{
			
			fieldId2ObjectIdAndThreadState.put(  current.getFieldId() , current.getElement());
			
			
			current = current.getNext();
			
		}
	}
	
	
	public Element get(int fieldId)
	{
		return fieldId2ObjectIdAndThreadState.get(fieldId);
	}
	
	
	public FieldId2Element<Element> put(Element element, int id)
	{
		fieldId2ObjectIdAndThreadState.put(id, element);
		return this;
	}
	
	
	
	
}
