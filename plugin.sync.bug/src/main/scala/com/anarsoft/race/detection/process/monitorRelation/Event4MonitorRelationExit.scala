package com.anarsoft.race.detection.process.monitorRelation

trait Event4MonitorRelationExit extends Event4MonitorRelation {
  
   
    def monitorId : Int;
  
    def onMonitorExit( f :  Event4MonitorRelationExit => Unit  )
    {
      f(this);
    }
  
     def onMonitorEnter( f :  Event4MonitorRelationEnter => Unit  )
     {
       
     }
    
}