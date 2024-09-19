package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.partialorder.WithPositionImpl.pos
import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.{HashMap, HashSet}

class PartialOrderContainer {

  private val allLeftThreadIndices = new HashSet[Int]();
  private val threadIndexToPartialOrderBetweenTwoThreads = new HashMap[LeftRightThreadIndex, LeftBeforeRightPerThread]();


  def foreachMaxDirectBefore(right: WithPosition, f: (WithPosition) => Unit): Unit = {
    for (leftThreadIndex <- allLeftThreadIndices) {
      if (right.threadIndex != leftThreadIndex) {
        threadIndexToPartialOrderBetweenTwoThreads
          .get(LeftRightThreadIndex(leftThreadIndex, right.threadIndex))
          .foreach(order => order.maxPositionForKeyBefore(right.runPosition)
            .foreach(posInRun => f(pos(posInRun, leftThreadIndex))));
      }
    }
  }

  def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit = {
    allLeftThreadIndices.add(left.threadIndex);
    val order = threadIndexToPartialOrderBetweenTwoThreads
      .getOrElseUpdate(LeftRightThreadIndex(left.threadIndex, right.threadIndex), new LeftBeforeRightPerThread());
    order.addLeftBeforeRight(left.runPosition, right.runPosition);
  }

}
