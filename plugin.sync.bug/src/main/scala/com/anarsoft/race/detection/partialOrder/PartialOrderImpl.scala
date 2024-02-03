package com.anarsoft.race.detection.partialOrder

import com.anarsoft.race.detection.partialOrder.WithPositionImpl.pos
import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.HashMap

class PartialOrderImpl(private val partialOrderContainer: PartialOrderContainer) {


  def isLeftBeforeRight(left: WithPosition, right: WithPosition) =
    new FindTransitiveLeftBeforeRightAlgorithm(partialOrderContainer).isLeftBeforeRight(left, right)

}
