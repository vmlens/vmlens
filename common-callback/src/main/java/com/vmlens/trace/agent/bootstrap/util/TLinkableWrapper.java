package com.vmlens.trace.agent.bootstrap.util;

import gnu.trove.list.TLinkable;

public class TLinkableWrapper<T> implements TLinkable<TLinkableWrapper<T>> {

	private TLinkableWrapper<T>  next;
	private TLinkableWrapper<T>  previous;
	
	public final T element;
	
	
	
	
	
	
	public TLinkableWrapper(T element) {
		super();
		this.element = element;
	}
	public TLinkableWrapper<T> getNext() {
		return next;
	}
	public void setNext(TLinkableWrapper<T> next) {
		this.next = next;
	}
	public TLinkableWrapper<T> getPrevious() {
		return previous;
	}
	public void setPrevious(TLinkableWrapper<T> previous) {
		this.previous = previous;
	}
	@Override
	public String toString() {
		return element.toString();
	}
	
	
	
}
