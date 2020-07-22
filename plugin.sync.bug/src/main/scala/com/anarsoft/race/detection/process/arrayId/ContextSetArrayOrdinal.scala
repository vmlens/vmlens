package com.anarsoft.race.detection.process.arrayId

import java.util.ArrayList
import com.anarsoft.race.detection.process.field.FieldIdMap
  
trait ContextSetArrayOrdinal[ EVENT <: ArrayIdEvent] {
  
  def arrayIdEventList : ArrayList[EVENT] 
    def  classIdMap  : FieldIdMap ;
  
}