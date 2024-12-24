package com.anarsoft.trace.agent.runtime;

import com.vmlens.shaded.gnu.trove.list.TLinkable;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

import java.lang.reflect.Array;

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

    public static <ELEMENT> ELEMENT[] toArray(Class<ELEMENT> clazz, TLinkedList<TLinkableWrapper<ELEMENT>> list) {
        ELEMENT[] array = (ELEMENT[]) Array.newInstance(clazz, list.size());
        int index = 0;
        for (TLinkableWrapper<ELEMENT> elem : list) {
            array[index] = elem.element;
            index++;
        }

        return array;
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
