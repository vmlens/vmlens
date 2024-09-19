package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.partialorder.WithPositionImpl.pos
import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.HashMap

class FindTransitiveLeftBeforeRightAlgorithm(private val partialOrderContainer: PartialOrderContainer) {

  private val alreadySeen = new HashMap[Int, Int]();
  private var processInNextStep = new HashMap[Int, Int]();

  /**
   * Algorithm: Start with right. Use all related left threads. Process till
   * runPosition is smaller than left.runPosition or an order was found
   *
   */
  def isLeftBeforeRight(left: WithPosition, right: WithPosition) = {
    next(right);
    var relationFound = false;

    while (!processInNextStep.isEmpty && !relationFound) {
      val current = processInNextStep;
      processInNextStep = new HashMap[Int, Int]()
      for (elem <- current) {
        partialOrderContainer.foreachMaxDirectBefore(pos(elem._2, elem._1),
          (currentLeft) => {
            if (currentLeft.threadIndex == left.threadIndex) {
              if (currentLeft.runPosition >= left.runPosition) {
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
    alreadySeen.get(pos.threadIndex) match {
      case None => {
        processInNextStep.put(pos.threadIndex, pos.runPosition)
        alreadySeen.put(pos.threadIndex, pos.runPosition)
      }

      case Some(x) => {
        if (x < pos.runPosition) {
          processInNextStep.put(pos.threadIndex, pos.runPosition)
          alreadySeen.put(pos.threadIndex, pos.runPosition)
        }
      }
    }
  }

}
