package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.gen._;



trait ApplyFieldEventVisitor {
  
  def accept(visitor : FieldVisitor)
  
  var slidingWindowId : Int;
  
  
}