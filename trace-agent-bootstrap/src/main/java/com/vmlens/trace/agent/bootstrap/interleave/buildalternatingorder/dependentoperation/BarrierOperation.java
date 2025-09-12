package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;

/**
 * A Barrier is used for all waiting operations
 *
 * Barriers have the following operations
 *          wait
 *          notify
 *          waitAndNotify
 *          getBarrierState
 *
 *
 *  The following Classes are Barriers:
 *      Future
 *      CyclicBarrier
 *      Phaser
 *      Semaphor
 *
 */


public interface BarrierOperation {

    AddToAlternatingOrder accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition);

}
