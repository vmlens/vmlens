package com.vmlens.trace.agent.bootstrap.util;

import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

public class TLinkableWrapper<T> implements TLinkable<TLinkableWrapper<T>> {

    private TLinkableWrapper<T> next;
    private TLinkableWrapper<T> previous;

    public final T element;

    public static <T> TLinkableWrapper<T> wrap(T element) {
        return new TLinkableWrapper<T>(element);
    }

    public static <T> TLinkedList<TLinkableWrapper<T>> emptyList() {
        return new TLinkedList<>();
    }

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
