package com.anarsoft.race.detection.process.detectRaceConditions



import com.anarsoft.race.detection.model.WithStatementPosition;
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.process.setMonitorInfo._;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.vmlens.api.MemoryAccessType
import com.anarsoft.race.detection.process.monitorRelation.MonitorIdBlockIdStackTraceOrdinal;
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo

trait EventWrapperDetectRaceConditions extends WithStatementPosition with WithLocationInClass with EventSetMonitorInfo {
  

  
  
   
   def operation : Int;
   
   
   def parallizeId : Option[Int]
   def stackTraceOrdinal: Int;
   
   
//   def isParallized : Boolean;
   def methodCounter : Int;
   var isRace = false;
   
   
   var raceTaken = false;
   
   // nur gesetzt wenn race condition
   var stacktraceOrdinal2MonitorId : HashMap[Int,Int] = null;
      var underMonitorArray : Array[MonitorIdBlockIdStackTraceOrdinal] =  null ;
      
      
   def needsMonitorInfo = isRace ;
  
  
  def setMonitorInfo(  monitorInfo : Option[MonitorInfo] , context : ContextSetMonitorInfo )
  {
    
    if(isRace)
    {
      stacktraceOrdinal2MonitorId = new HashMap[Int,Int]
    
    monitorInfo match
    {
      case Some(x) =>
        {
          
          
          val iter = x.monitorInfoList.iterator();
          while(iter.hasNext)
          {
            val current = iter.next()
            stacktraceOrdinal2MonitorId.put(current.stackTraceOrdinal , current.monitorId);
          }
          
          
        }
      
      case None =>
        {
          
        }
      
      
    }
    }
    
    
    
    
  }
  
  
  
  
   def foreach( f :  EventWrapperDetectRaceConditions => Unit )
     {
       f(this);
     }
   
   
   def isWrite() = MemoryAccessType.containsWrite(operation)
   def isRead()  = MemoryAccessType.containsRead(operation);
  
  
  
     def getReadEvent() = this;
       
       
       
       
   def getWriteEvent() = this;
   
   
  

   
   
}