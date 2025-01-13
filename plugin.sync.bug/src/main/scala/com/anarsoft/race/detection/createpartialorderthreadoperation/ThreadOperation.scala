package com.anarsoft.race.detection.createpartialorderthreadoperation

import com.anarsoft.race.detection.partialorder.PartialOrderBuilder

trait ThreadOperation {

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit;

}
