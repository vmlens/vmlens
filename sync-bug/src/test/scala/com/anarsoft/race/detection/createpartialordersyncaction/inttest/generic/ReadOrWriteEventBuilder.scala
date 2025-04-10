package com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType

trait ReadOrWriteEventBuilder[EVENT <: SyncActionEventWithCompareType[EVENT]] {

  def read(threadIndex : Int) : EVENT;
  
  def write(threadIndex : Int) : EVENT;

  
}
