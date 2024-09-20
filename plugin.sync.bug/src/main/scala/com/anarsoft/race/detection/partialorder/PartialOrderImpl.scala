package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.util.WithPosition

class PartialOrderImpl(private val partialOrderContainer: PartialOrderContainer) extends PartialOrder {


  def isLeftBeforeRight(left: WithPosition, right: WithPosition): Boolean =
    new FindTransitiveLeftBeforeRightAlgorithm(partialOrderContainer).isLeftBeforeRight(left, right)

}
