package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {

    private T[] elements;

    @SuppressWarnings("unchecked")
    public ArrayList(T firstElement) {
        this.elements = (T[]) new Object[1];
        this.elements[0] = firstElement;
    }


    public ArrayList(T[] elementArray) {
        this.elements = elementArray;
    }

    public void add(T element) {
        int oldLength = elements.length;
        elements = Arrays.copyOf(elements, oldLength + 1);
        elements[oldLength] = element;
    }

    public void addAll(T[] newElements) {
        int oldLength = elements.length;
        elements = Arrays.copyOf(elements, oldLength + newElements.length);
        System.arraycopy(newElements, 0, elements, oldLength, newElements.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < elements.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[cursor++];
            }
        };
    }
}


