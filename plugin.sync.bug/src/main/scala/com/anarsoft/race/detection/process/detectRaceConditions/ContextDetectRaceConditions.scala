package com.anarsoft.race.detection.process.detectRaceConditions

import com.anarsoft.race.detection.process.interleave.InterleaveEventList
import com.anarsoft.race.detection.process.partialOrder.PartialOrder
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;
import  com.anarsoft.race.detection.model.result.RaceCondition;
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.anarsoft.race.detection.model.result.LocationInClass

trait ContextDetectRaceConditions  extends ContextNonVolatileFields {
  
  

  
   
   def interleaveEventList : InterleaveEventList ;
   def  partialOrder : PartialOrder;
  var raceExceptionSet : HashSet[RaceConditionFoundException] = null; 
  
  
  var raceLocation2Count : HashMap[LocationInClass,Int] = null;
  
  
  def initializeContextDetectRaceConditions()
  {
    raceExceptionSet = new HashSet[RaceConditionFoundException]
    raceLocation2Count = new HashMap[LocationInClass,Int]();
  }
  
  
}