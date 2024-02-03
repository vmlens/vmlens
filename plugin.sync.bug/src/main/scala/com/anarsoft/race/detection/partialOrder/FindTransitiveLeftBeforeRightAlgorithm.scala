package com.anarsoft.race.detection.partialOrder

import com.anarsoft.race.detection.partialOrder.WithPositionImpl.pos
import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.HashMap

class FindTransitiveLeftBeforeRightAlgorithm(private val partialOrderContainer: PartialOrderContainer) {

  private val alreadySeen = new HashMap[Long, Int]();
  private var processInNextStep = new HashMap[Long, Int]();

  /**
   * Algorithm: Start with right. Use all related left threads. Process till
   * positionInRun is smaller than left.positionInRun or an order was found
   *
   */
  def isLeftBeforeRight(left: WithPosition, right: WithPosition) = {
    next(right);
    var relationFound = false;

    while (!processInNextStep.isEmpty && !relationFound) {
      val current = processInNextStep;
      processInNextStep = new HashMap[Long, Int]()
      for (elem <- current) {
        partialOrderContainer.foreachMaxDirectBefore(pos(elem._2, elem._1),
          (currentLeft) => {
            if (currentLeft.threadId == left.threadId) {
              if (currentLeft.positionInRun >= left.positionInRun) {
                relationFound = true
              }
            } else {
              next(pos(elem._2, elem._1));
            }
          }
        )
      }
    }
    relationFound;
  }

  private def next(pos: WithPosition): Unit = {
    alreadySeen.get(pos.threadId) match {
      case None => {
        processInNextStep.put(pos.threadId, pos.positionInRun)
        alreadySeen.put(pos.threadId, pos.positionInRun)
      }

      case Some(x) => {
        if (x < pos.positionInRun) {
          processInNextStep.put(pos.threadId, pos.positionInRun)
          alreadySeen.put(pos.threadId, pos.positionInRun)
        }
      }
    }
  }

}
