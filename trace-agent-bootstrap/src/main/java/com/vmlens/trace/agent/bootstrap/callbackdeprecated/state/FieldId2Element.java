package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public interface FieldId2Element<Element> {
	
	
	Element get(int fieldId); 
	FieldId2Element<Element> put(Element element, int id);

}
