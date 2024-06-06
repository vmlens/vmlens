package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.partialorder.WithPositionImpl.pos
import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.{HashMap, HashSet}

class PartialOrderContainer {

  private val allLeftThreadIds = new HashSet[Long]();
  private val threadIdToPartialOrderBetweenTwoThreads = new HashMap[LeftRightThreadId, LeftBeforeRightPerThread]();


  def foreachMaxDirectBefore(right: WithPosition, f: (WithPosition) => Unit): Unit = {
    for (leftThreadId <- allLeftThreadIds) {
      if (right.threadId != leftThreadId) {
        threadIdToPartialOrderBetweenTwoThreads
          .get(LeftRightThreadId(leftThreadId, right.threadId))
          .foreach(order => order.maxPositionForKeyBefore(right.positionInRun)
            .foreach(posInRun => f(pos(posInRun, leftThreadId))));
      }
    }
  }

  def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit = {
    allLeftThreadIds.add(left.threadId);
    val order = threadIdToPartialOrderBetweenTwoThreads
      .getOrElseUpdate(LeftRightThreadId(left.threadId, right.threadId), new LeftBeforeRightPerThread());
    order.addLeftBeforeRight(left.positionInRun, right.positionInRun);
  }

}
