package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.util.WithPosition

trait PartialOrderBuilder {

  def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit;

}
