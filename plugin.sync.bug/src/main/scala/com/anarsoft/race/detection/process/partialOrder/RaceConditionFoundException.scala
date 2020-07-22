package com.anarsoft.race.detection.process.partialOrder

import  com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.gen._
import com.anarsoft.race.detection.process.detectRaceConditions._

class RaceConditionFoundException(val read : EventWrapperDetectRaceConditions , val write : EventWrapperDetectRaceConditions) extends Exception{
 
  
  override def toString() = read + " \n" + write;
  
 
  def getName(fieldAndArrayFacade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) = fieldAndArrayFacade.getQualifiedFieldName(read.getLocationInClass(),stackTraceGraph)
  
}