package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.util.WithPosition

class BuildPartialOrderContextImpl(val partialOrderContainer : PartialOrderContainer,
                                   val lastThreadPositionMap : LastThreadPositionMap)  extends BuildPartialOrderContext {

  override def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit = partialOrderContainer.addLeftBeforeRight(left, right)

  override def lastPosition(index: Int): Int = lastThreadPositionMap.lastPosition(index)
}
