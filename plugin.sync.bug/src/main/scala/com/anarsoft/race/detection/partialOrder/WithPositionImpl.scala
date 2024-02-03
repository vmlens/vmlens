package com.anarsoft.race.detection.partialOrder

import com.anarsoft.race.detection.util.WithPosition

case class WithPositionImpl(positionInRun: Int, threadId: Long) extends WithPosition {

}

object WithPositionImpl {

  def pos(positionInRun: Int, threadId: Long): WithPosition =
    new WithPositionImpl(positionInRun, threadId);
}