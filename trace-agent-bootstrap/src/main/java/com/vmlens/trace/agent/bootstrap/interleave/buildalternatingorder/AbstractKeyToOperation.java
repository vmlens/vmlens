package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.block.KeyToThreadIdToElementList;

public abstract class AbstractKeyToOperation<KEY, ELEMENT extends WithThreadIndex> {

    private final KeyToThreadIdToElementList<KEY,ELEMENT> keyToList = new KeyToThreadIdToElementList<>();




}
