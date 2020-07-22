package com.anarsoft.race.detection.model.result

import com.vmlens.api.MemoryAccessType

class MemoryAccessAggregate(val location : LocationInClass,var operation : Int) {
  
  
  def isReadOnly() = ! MemoryAccessType.containsWrite(operation);
  
  
}