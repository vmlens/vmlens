package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public class FieldId2ElementListBased<Element> implements FieldId2Element<Element> {

	private final LinkedListElement<Element> start;
	private LinkedListElement<Element> end;
	private int count = 1;
	private static final int MAX_COUNT_FOR_LIST = 5;
	
	public FieldId2ElementListBased(Element element, int fieldId )
	{
		start = new LinkedListElement<Element>(element,fieldId);
		end = start;
	}
	
	
	
	public Element get(int fieldId)
	{
		LinkedListElement<Element> current = start;
		
		while( current != null )
		{
			if( current.getFieldId() == fieldId )
			{
				return current.getElement();
			}
			
			current = current.getNext();
			
		}
		
		return null;
	}
	
	
	public FieldId2Element<Element> put(Element element, int fieldId)
	{
		if( count >= MAX_COUNT_FOR_LIST )
		{
			
			return new  FieldId2ElementMapBased<Element>(start);
			
			
		}
		else
		{
			end.setNext( new LinkedListElement<Element>(element,fieldId)  );
			end = end.getNext();
			
			count++;
			
			return this;
		}
		
		
		
	}
	
	
}
