package com.anarsoft.race.detection.createpartialorderthreadoperation

import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext

trait ThreadOperation {

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit;

}
