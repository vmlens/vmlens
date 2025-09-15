package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;


import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToKeyToOperationCollectionWithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public interface InterleaveAction extends AddToKeyToOperationCollectionWithThreadIndex {

    boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other);
    boolean startsThread();

}
