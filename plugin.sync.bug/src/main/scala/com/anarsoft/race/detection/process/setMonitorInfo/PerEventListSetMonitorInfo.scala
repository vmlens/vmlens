package com.anarsoft.race.detection.process.setMonitorInfo

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import java.util.ArrayList
import com.anarsoft.race.detection.process.monitorRelation.MonitorMapPerThreadTuple

class PerEventListSetMonitorInfo(val getCurrentReadFieldsF: ContextSetMonitorInfo => ArrayList[_ <: EventSetMonitorInfo]) extends PerEventListAbstract[EventSetMonitorInfo, ContextSetMonitorInfo] {

  def getCurrentReadFields(context: ContextSetMonitorInfo) = getCurrentReadFieldsF(context);
  val comparator = new ComparatorByThreadId();

  def processEventList(list: ArrayList[_ <: EventSetMonitorInfo], context: ContextSetMonitorInfo) {
    var currentThreadId = -1L;

    var monitorMapPerThread: Option[MonitorMapPerThreadTuple] = None;
    val iter = list.iterator();

    while (iter.hasNext()) {
      val current = iter.next();

      if (currentThreadId != current.threadId) {
        monitorMapPerThread = context.monitorMap.getMonitorMapPerThreadTuple(current.threadId)
        currentThreadId = current.threadId;

      }
        
     
        if (current.needsMonitorInfo) {

          
          monitorMapPerThread match {

            case None =>
              {
            
                
                current.setMonitorInfo(None, context);
              }
            case Some(map) =>
              {
               
                
                current.setMonitorInfo(map.getMonitorInfo(current.programCounter), context)
              }

          }

        }
      

    }
  }

}

object PerEventListSetMonitorInfo
{
  
  def apply(  
      getCurrentReadFields : ContextSetMonitorInfo => ArrayList[_ <: EventSetMonitorInfo]  ) =
  {
    new PerEventListSetMonitorInfo( getCurrentReadFields  );
  }
  
  
}