package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.util.WithPosition

trait BuildPartialOrderContext {

  def addLeftBeforeRight(left: WithPosition, right: WithPosition): Unit;
  def lastPosition(index: Int): Int;

}
