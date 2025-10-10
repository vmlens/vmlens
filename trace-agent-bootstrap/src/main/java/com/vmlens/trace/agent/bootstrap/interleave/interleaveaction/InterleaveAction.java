package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;


import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToKeyToOperationCollectionWithThreadIndex;

public interface InterleaveAction extends AddToKeyToOperationCollectionWithThreadIndex {

    boolean equalsNormalized(InterleaveAction other);
    int normalizedHashCode();

}
