package com.anarsoft.race.detection.process.setMonitorInfo


import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.process.monitorRelation.MonitorMap


import com.anarsoft.race.detection.process.volatileField.ContextVolatileField
import  com.anarsoft.race.detection.process.nonVolatileField._

trait ContextSetMonitorInfo extends ContextNonVolatileFields  with ContextVolatileField  {
  
  def monitorMap : MonitorMap;
//  def statementList : ArrayList[Statement];
  
  //def events4Statements :  NonVolatileListCollection[ArrayAccessEvent,NonVolatileFieldAccessEvent,NonVolatileFieldAccessEventStatic];
 
}