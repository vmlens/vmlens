package com.anarsoft.race.detection.process.syncAction

import java.util.Comparator
import com.anarsoft.race.detection.process.partialOrder.EventWithOrder;


class SortBasedOnOrder[ID_PER_OBJECT,EVENT <: EventWithOrder[ID_PER_OBJECT]] extends Comparator[EVENT] 
{
  
  def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
          {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
          }
          else
          {
             java.lang.Integer.compare( o1.order , o2.order  )
          }
    }
  
  
}