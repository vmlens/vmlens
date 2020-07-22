package com.anarsoft.race.detection.process.aggregate

import java.util.Comparator


class ComparatorEventAbstractAggregate[ID_PER_OBJECT,EVENT <: EventAbstractAggregate[ID_PER_OBJECT]] extends Comparator[EVENT]  {
   def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
          {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
          }
          else
        {
          0;
        }
    }  
}