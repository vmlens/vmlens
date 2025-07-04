package com.vmlens.trace.agent.bootstrap.interleave.run;


import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToKeyToOperationCollectionWithThreadIndex;

public interface InterleaveAction extends AddToKeyToOperationCollectionWithThreadIndex {

    boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other);

}
