package com.anarsoft.race.detection.process.aggregate

import java.util.Comparator


class Comparator4MethodOrdinalAggregate[ID_PER_OBJECT,EVENT <: EventMethodOrdinalAggregate[ID_PER_OBJECT]] extends Comparator[EVENT]  {
   def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
          {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
          }
          else if( o1.methodId  != o2.methodId)
        {
          java.lang.Integer.compare( o1.methodId , o2.methodId)
        }
          else
          {
             java.lang.Integer.compare( o1.idPerMethod , o2.idPerMethod)
          }
    }  
}