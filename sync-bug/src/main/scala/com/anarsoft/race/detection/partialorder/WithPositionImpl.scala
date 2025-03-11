package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.util.WithPosition

case class WithPositionImpl(runPosition: Int, threadIndex: Int) extends WithPosition {

}

object WithPositionImpl {

  def pos(positionInRun: Int, threadIndex: Int): WithPosition =
    new WithPositionImpl(positionInRun, threadIndex);
}