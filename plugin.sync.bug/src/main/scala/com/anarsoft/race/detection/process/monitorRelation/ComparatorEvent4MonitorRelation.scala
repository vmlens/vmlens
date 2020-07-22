package com.anarsoft.race.detection.process.monitorRelation

import java.util.Comparator

class ComparatorEvent4MonitorRelation[EVENT <: Event4MonitorRelation] extends Comparator[EVENT] {
  
   def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.programCounter != o2.programCounter )
          {
             java.lang.Integer.compare( o1.programCounter , o2.programCounter  )
          }
        else
        {
          0;
        }
    }  
  
  
}