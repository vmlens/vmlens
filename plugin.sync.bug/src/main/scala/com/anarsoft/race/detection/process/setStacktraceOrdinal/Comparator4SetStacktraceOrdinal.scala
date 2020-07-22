package com.anarsoft.race.detection.process.setStacktraceOrdinal


import java.util.Comparator

class Comparator4SetStacktraceOrdinal[EVENT <: EventSetStacktraceOrdinal] extends Comparator[EVENT] {
  
  
    def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.methodCounter != o2.methodCounter )
          {
             java.lang.Integer.compare( o1.methodCounter , o2.methodCounter  )
          }
        else
        {
          0;
        }
    }  
  
}