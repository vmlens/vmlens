package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.KeyToThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class KeyToOperation<KEY, ELEMENT extends DependentOperationAndPositionOrContainer<ELEMENT>> {

    private final KeyToThreadIdToElementList<KEY,ELEMENT> keyToList = new KeyToThreadIdToElementList<>();

    public void put(KEY key, ELEMENT element) {
        keyToList.put(key,element);
    }

    public ListBuilderNode process(BuildAlternatingOrderContext context,
                                   ListBuilderNode listBuilderNode) {
        ListBuilderNode current = listBuilderNode;
        for (ThreadIndexToElementList<ELEMENT> threadIndexToElementList : keyToList) {
            current = add(threadIndexToElementList,context, current);
        }
        return current;
    }

    private ListBuilderNode add(ThreadIndexToElementList<ELEMENT> threadIndexToElementList,
                                BuildAlternatingOrderContext context,
                                ListBuilderNode listBuilderNode) {
        ListBuilderNode currentListBuilderNode = listBuilderNode;
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> oneThread : threadIndexToElementList) {
            for (TLinkableWrapper<ELEMENT> current : oneThread.element()) {
                Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> otherThreadBlocks =
                        threadIndexToElementList.iteratorStartingAt(current.element().threadIndex() + 1);
                while (otherThreadBlocks.hasNext()) {
                    TLinkedList<TLinkableWrapper<ELEMENT>> otherThread = otherThreadBlocks.next().element();
                    // ToDo we should probably stop when more than n elements were created
                    for (TLinkableWrapper<ELEMENT> otherBlock : otherThread) {
                        ListBuilderNode nextNode = current.element().addToAlternatingOrder(otherBlock.element(),
                                context,
                                currentListBuilderNode);
                        currentListBuilderNode = nextNode;
                    }
                }
            }
        }
        return currentListBuilderNode;
    }

}
