package com.anarsoft.race.detection.process.aggregate

import java.util.Comparator

class Comparator4StackTraceAggregate[ID_PER_OBJECT,EVENT <: EventStackTraceOrdinalAggregate[ID_PER_OBJECT]] extends Comparator[EVENT]  {
   def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
          {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
          }
         
          else
          {
             java.lang.Integer.compare( o1.stackTraceOrdinal , o2.stackTraceOrdinal)
          }
    }  
}