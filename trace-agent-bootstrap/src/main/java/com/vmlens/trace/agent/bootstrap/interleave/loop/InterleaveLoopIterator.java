package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<CalculatedRun> {

    private final InterleaveLoopContext interleaveLoopContext;
    private final int MAXIMUM_NULL;
    private final THashSet<CalculatedRun> alreadyExecuted = new THashSet<CalculatedRun>();
    private final IteratorQueue container;
    private Iterator<CalculatedRun> currentIterator;
    private CalculatedRun next;

    public InterleaveLoopIterator(InterleaveLoopContext interleaveLoopContext,
                                  IteratorQueue container) {
        this.interleaveLoopContext = interleaveLoopContext;
        this.container = container;
        /*
         * for very small maximumAlternatingOrders it is possible that all orders lead to
         * a blocked run
         * this is for example the case at TestNonBlockingAtomic
         * To avoid overruns  we cap at 25
         * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html:
         * int: By default, the int data type is a 32-bit signed
         *
         */
        if(interleaveLoopContext.maximumAlternatingOrders()  < 25) {
            MAXIMUM_NULL = (int) Math.pow(2, interleaveLoopContext.maximumAlternatingOrders() + 1) + 4;
        } else {
            MAXIMUM_NULL = -1;
        }


    }

    @Override
    public boolean hasNext() {

        if(alreadyExecuted.size() > interleaveLoopContext.maximumIterations()) {
            interleaveLoopContext.maximumIterationsReached();
            return false;
        }

        if (next != null) {
            return true;
        }
        if (currentIterator == null) {
            currentIterator = container.poll();
        }
        int nullCount = 0;
        while (currentIterator != null) {
            while (currentIterator.hasNext()) {
                CalculatedRun temp = nextElement();
                if (temp != null ) {
                    next = temp;
                    return true;
                }
                if(MAXIMUM_NULL != -1 && nullCount > MAXIMUM_NULL) {
                    break;
                }
                nullCount++;
            }
            currentIterator = container.poll();
        }
        return false;
    }

    private CalculatedRun nextElement() {
        CalculatedRun temp = currentIterator.next();
        if(temp == null) {
            return null;
        }
        if(alreadyExecuted.contains(temp)) {
            return null;
        }
        CalculatedRun result = temp.copy();
        alreadyExecuted.add(result);
        return result;
    }

    @Override
    public CalculatedRun next() {
        CalculatedRun temp = next;
        next = null;
        return temp;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
