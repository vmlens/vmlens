package com.vmlens.api.testbuilder.internal.runner;



import java.util.Iterator;
import java.util.NoSuchElementException;


public abstract class AbstractIterator<T> implements Iterator<T> {

    private State state = State.NOT_READY;

    /** Constructor for use by subclasses. */
    protected AbstractIterator() {}

    private enum State {
        /** We have computed the next element and haven't returned it yet. */
        READY,

        /** We haven't yet computed or have already returned the element. */
        NOT_READY,

        /** We have reached the end of the data and are finished. */
        DONE,

        /** We've suffered an exception and are kaput. */
        FAILED,
    }

    private T next;

    @Override
    public final boolean hasNext() {
        switch (state) {
            case DONE:
                return false;
            case READY:
                return true;
            default:
        }
        return tryToComputeNext();
    }

    private boolean tryToComputeNext() {
        state = State.FAILED; // temporary pessimism
        next = computeNext();
        if (state != State.DONE) {
            state = State.READY;
            return true;
        }
        return false;
    }


    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = State.NOT_READY;
        // Safe because hasNext() ensures that tryToComputeNext() has put a T into `next`.
        T result = next;
        next = null;
        return result;
    }

    protected abstract  T computeNext();

    protected final  T endOfData() {
        this.state = State.DONE;
        return null;
    }
}
