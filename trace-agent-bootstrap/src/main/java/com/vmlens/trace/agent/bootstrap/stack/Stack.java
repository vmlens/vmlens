package com.vmlens.trace.agent.bootstrap.stack;


import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

/**
 *
 * stack for pre analyzed method enter and exit and callback methods
 * enter gets called after shouldProcess, exit before shouldProcess
 *
 */

public class Stack {

    private final TLinkedList<TLinkableWrapper<PreAnalyzedOrCallback>> list =
            new TLinkedList<>();

    public void popCallback() {
        if (list.isEmpty()) {
            return;
        }
        list.removeLast();
    }

    public void popPreAnalyzed() {
        if (list.isEmpty()) {
            return;
        }
        TLinkableWrapper<PreAnalyzedOrCallback> current = list.removeLast();
        while(! list.isEmpty() && ! (current.element() instanceof PreAnalyzed)) {
            current = list.removeLast();
        }
    }

    public void pushCallback() {
        list.addLast(wrap(new Callback()));
    }

    public void pushPreAnalyzed() {
        list.addLast(wrap(new PreAnalyzed()));
    }

    public boolean shouldProcess() {
        if(list.isEmpty()) {
            return true;
        }

        TLinkableWrapper<PreAnalyzedOrCallback> lastElement = list.getLast();
        if( lastElement.element() instanceof PreAnalyzed) {
            return false;
        }

        boolean previousAtomic = false;
        for(TLinkableWrapper<PreAnalyzedOrCallback> elem : list) {
            if(elem.element() instanceof Callback) {
                previousAtomic = false;
            } else {
                if(previousAtomic) {
                   return false;
                }
                previousAtomic = true;
            }
        }
        return true;
    }

    // Visible for test
    public int size() {
        return list.size();
    }
}
