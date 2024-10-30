package com.vmlens.trace.agent.bootstrap.util;

import gnu.trove.list.TLinkable;

public class TLinkableWrapper<T> implements TLinkable<TLinkableWrapper<T>> {

    private TLinkableWrapper<T> next;
    private TLinkableWrapper<T> previous;

    public final T element;

    public static <T> TLinkableWrapper l(T element) {
        return new TLinkableWrapper(element);
    }

    public TLinkableWrapper(T element) {
        super();
        this.element = element;
    }


    public static <T> TLinkableWrapper<T> wrap(T element) {
        return new TLinkableWrapper<T>(element);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLinkableWrapper<?> that = (TLinkableWrapper<?>) o;
        return element != null ? element.equals(that.element) : that.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }
}
