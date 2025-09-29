package com.vmlens.trace.agent.bootstrap.interleave.run;


import gnu.trove.list.linked.TIntLinkedList;

public class InterleaveRunTest {

    /**
     * all tests are with two active thread indices, 0 and 1
     */
    private final TIntLinkedList activeThreadIndices;

    public InterleaveRunTest() {
        TIntLinkedList temp = new TIntLinkedList();
        temp.add(0);
        temp.add(1);
        this.activeThreadIndices = temp;
    }



}
