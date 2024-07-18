package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrapp;

public class ActualRunTestBuilder {

    TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();
    private int index;

    public void thread(InterleaveActionTestFactory... interleaveActionTestBuilderList) {
        for (InterleaveActionTestFactory factory : interleaveActionTestBuilderList) {
            actualRun.add(wrapp(factory.create(index)));
        }
        index++;
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        return actualRun;
    }

}
