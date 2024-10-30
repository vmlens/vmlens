package com.anarsoft.trace.agent.runtime;

import com.vmlens.shaded.gnu.trove.list.TLinkable;

public class TLinkableWrapper<ELEMENT> implements TLinkable<TLinkableWrapper<ELEMENT>> {


	private ELEMENT element;
	private  TLinkableWrapper<ELEMENT> next;
	private  TLinkableWrapper<ELEMENT> previous;

	public ELEMENT getElement() {
		return element;
	}

	public TLinkableWrapper(ELEMENT element) {
		super();
		this.element = element;
	}

    public static <ELEMENT> TLinkableWrapper<ELEMENT> wrap(ELEMENT element) {
        return new TLinkableWrapper<>(element);
    }

	@Override
	public TLinkableWrapper<ELEMENT> getNext() {

		return next;
	}

	@Override
	public TLinkableWrapper<ELEMENT> getPrevious() {

		return previous;
	}

	@Override
	public void setNext(TLinkableWrapper<ELEMENT> arg0) {
		next = arg0;

	}

	@Override
	public void setPrevious(TLinkableWrapper<ELEMENT> arg0) {
		previous = arg0;

	}





}
