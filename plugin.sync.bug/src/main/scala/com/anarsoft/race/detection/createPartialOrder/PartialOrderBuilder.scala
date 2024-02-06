package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.util.WithPosition

trait PartialOrderBuilder {

  def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit;

}
