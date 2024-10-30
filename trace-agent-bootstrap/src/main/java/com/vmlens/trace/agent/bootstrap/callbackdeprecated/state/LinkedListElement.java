package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public class LinkedListElement<Element> {

	private LinkedListElement<Element> next;
	private final Element element;
	private final int fieldId;

	public LinkedListElement(Element element,int fieldId) {
		super();
		this.element = element;
		this.fieldId = fieldId;
	}

	public LinkedListElement<Element> getNext() {
		return next;
	}

	public void setNext(LinkedListElement<Element> next) {
		this.next = next;
	}

	public Element getElement() {
		return element;
	}

	public int getFieldId() {
		return fieldId;
	}
	
	
	
	
}
