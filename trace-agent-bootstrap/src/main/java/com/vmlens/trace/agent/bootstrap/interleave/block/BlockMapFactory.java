package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

/**
 * creates a map of block keys to thread ids to blocks from an actual run.
 */
public class BlockMapFactory<ELEMENT extends BlockBuilder> {

    public KeyToThreadIdToElementList<BlockKey,Block> create(
            TLinkedList<TLinkableWrapper<ELEMENT>> actualRun) {
        KeyToThreadIdToElementList<BlockBuilderKey,BlockBuilder> blockFactoryMap =
                new KeyToThreadIdToElementList<>();
        for(TLinkableWrapper<ELEMENT> blockBuilder : actualRun) {
            blockFactoryMap.put(blockBuilder.element.key(), blockBuilder.element);
        }
        KeyToThreadIdToElementList<BlockKey,Block> result = new KeyToThreadIdToElementList<>();
        for(ThreadIdToElementList<BlockBuilder> threadIdToElementList : blockFactoryMap) {
            for(TLinkableWrapper<TLinkedList<TLinkableWrapper<BlockBuilder>>> oneThread : threadIdToElementList) {
                BlockBuilder prevoius = null;
                for(TLinkableWrapper<BlockBuilder> current : oneThread.element) {
                    if(prevoius == null) {
                        current.element.start(result);
                    } else {
                        prevoius.add(current.element,result);
                    }
                    prevoius = current.element;
                }
            }
        }
        return result;
    }
}
