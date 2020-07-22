package com.anarsoft.race.detection.process.monitorRelation



trait Event4MonitorRelationEnter extends Event4MonitorRelation  {
  
  def monitorId : Int;
  def stackTraceOrdinal : Int;
  def position() : Int;
  
  
  
    def onMonitorExit( f :  Event4MonitorRelationExit => Unit  )
    {
     
    }
  
     def onMonitorEnter( f :  Event4MonitorRelationEnter => Unit  )
     {
        f(this);
     }
  
  
}