package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.util.WithPosition

trait PartialOrder {

  def isLeftBeforeRight(left: WithPosition, right: WithPosition): Boolean;

}
