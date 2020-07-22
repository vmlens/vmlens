package com.anarsoft.race.detection.process.nonVolatileField

import java.util.Comparator
import  com.anarsoft.race.detection.process.detectRaceConditions.EventDetectRaceConditions

class Comparator4Filter[ID_PER_OBJECT,EVENT  <:  EventDetectRaceConditions[ID_PER_OBJECT]]    extends Comparator[EVENT] {
  
  def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
        {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
        }
          else 
        {
          java.lang.Long.compare( o1.threadId  , o2.threadId );
        }
         
        
        
    }  
  
  
}