package com.anarsoft.race.detection.process.monitorRelation

class BlockIdAndEvent4MonitorRelationEnter( val blockId : Long, val event4MonitorRelationEnter : Event4MonitorRelationEnter ) {
  
  
  
  def createMonitorIdBlockIdStackTraceOrdinal() =
  {
    new MonitorIdBlockIdStackTraceOrdinal( event4MonitorRelationEnter.monitorId , blockId , event4MonitorRelationEnter.stackTraceOrdinal );
  }
  
}