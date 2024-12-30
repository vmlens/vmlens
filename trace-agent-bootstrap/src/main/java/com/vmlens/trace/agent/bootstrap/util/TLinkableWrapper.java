package com.vmlens.trace.agent.bootstrap.util;


import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

import java.lang.reflect.Array;
import java.util.Objects;

public class TLinkableWrapper<ELEMENT> implements TLinkable<TLinkableWrapper<ELEMENT>> {


	private ELEMENT element;
	private  TLinkableWrapper<ELEMENT> next;
	private  TLinkableWrapper<ELEMENT> previous;

	public static <T> TLinkedList<TLinkableWrapper<T>> emptyList() {
		return new TLinkedList<>();
	}

	public TLinkableWrapper(ELEMENT element) {
		super();
		this.element = element;
	}

	public ELEMENT element() {
		return element;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TLinkableWrapper<?> that = (TLinkableWrapper<?>) o;

		return Objects.equals(element, that.element);
	}

	@Override
	public int hashCode() {
		return element != null ? element.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "TLinkableWrapper{" +
				"element=" + element +
				'}';
	}
}
