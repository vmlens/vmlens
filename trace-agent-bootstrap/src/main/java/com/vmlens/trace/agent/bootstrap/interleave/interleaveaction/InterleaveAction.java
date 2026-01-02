package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;


import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToKeyToOperationCollectionWithThreadIndex;

/**
 * The equals and hashCode is used for pattern detection, so two
 * InterleaveAction are equal if they represent the sane action independent
 * in the same thread independent of the position in the thread
 *
 * equalsNormalized is used to see if to runs are the same so is independent of the
 * hashcode
 *
 * For array access and later for hash map access we do not take the index into account
 *
 */

public interface  InterleaveAction extends AddToKeyToOperationCollectionWithThreadIndex {

    boolean equalsNormalized(InterleaveAction other);

}
