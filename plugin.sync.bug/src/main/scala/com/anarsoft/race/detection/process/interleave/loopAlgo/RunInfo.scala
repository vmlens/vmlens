package com.anarsoft.race.detection.process.interleave.loopAlgo

import com.anarsoft.race.detection.process.interleave._;
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import com.anarsoft.race.detection.process.monitor.MonitorEnterEventInterleave
import scala.collection.mutable.HashSet
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import  com.anarsoft.race.detection.model.result.StackTraceOrdinal

class RunInfo(val id : Int) {
  
  var state : RunState =  Open();
  var hasDeadlock = false;
  var startRaceList : Option[RaceListElement] = None;
  var eventCount = 0;
  
  override def toString() = id + " " + state;
  
  def addRace(read : InterleaveEventNonVolatileAccess)
  {
    startRaceList match
    {
      case None =>
        {
              startRaceList = Some(new RaceListElement( read ));
        }
        
      case Some(start) =>
        {
           var current = start;
           
           while( current.next != None )
           {
             current = current.next.get;
             
             
           }
          
           current.next = Some(new RaceListElement( read ));
          
          
        }
      
      
    }
    
    
    
    
    eventCount = eventCount + 2;
  }
  
  
  

   def addMonitorEvent(event : InterleaveEventStatement,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] )
  {
      if( event.isInstanceOf[MonitorEnterEventInterleave] )
    {
      val monitorEnter = event.asInstanceOf[MonitorEnterEventInterleave];
     
      if(deadlockFilter.contains(new StackTraceOrdinalAndMonitor(new StackTraceOrdinal(monitorEnter.stackTraceOrdinal) , monitorEnter.position, monitorEnter.monitorId))  )
      {
        monitorEnter.hasDeadlock = true;
    
        
        
        hasDeadlock = true;
      }
      
    }
    
    
     eventCount = eventCount + 1;
    
  }
   
   
  def add(event : InterleaveEventStatement )
  {
      
    
    
     eventCount = eventCount + 1;
    
  }
  
  
  def endEventReceived()
  {
    state = Closed();
  }
  
}