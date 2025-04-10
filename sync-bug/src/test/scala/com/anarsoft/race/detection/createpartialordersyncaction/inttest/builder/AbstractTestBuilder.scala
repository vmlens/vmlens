package com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder

import com.anarsoft.race.detection.partialorder.WithPositionImpl
import com.anarsoft.race.detection.util.WithPosition

abstract class AbstractTestBuilder {

  val methodCounter = 5;
  val methodId = 9;
  val loopId = 4;
  val runId = 2;
  val bytecodePosition = 99;
  val atomicMethodId = 15;
  
  var positionInRun = 0;
  
  def nextPosition(threadIndex : Int): WithPosition = {
    val temp = positionInRun;
    positionInRun = positionInRun + 1;
    new WithPositionImpl(positionInRun,threadIndex);
  }
  

}
