package com.anarsoft.race.detection.process.interleave.loopAlgo

import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess

class RaceListElement(val readEvent : InterleaveEventNonVolatileAccess) {
  
  var next : Option[RaceListElement] = None;
  
  
  
}